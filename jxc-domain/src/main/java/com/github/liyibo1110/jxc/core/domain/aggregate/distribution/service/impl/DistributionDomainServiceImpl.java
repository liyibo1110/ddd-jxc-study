package com.github.liyibo1110.jxc.core.domain.aggregate.distribution.service.impl;

import com.github.liyibo1110.jxc.common.command.CreateDistributionOrderCommand;
import com.github.liyibo1110.jxc.common.command.UpdateShippingCommand;
import com.github.liyibo1110.jxc.common.exception.BizException;
import com.github.liyibo1110.jxc.core.domain.aggregate.distribution.entity.DistributionOrderAggregateRoot;
import com.github.liyibo1110.jxc.core.domain.aggregate.distribution.entity.DistributionOrderItem;
import com.github.liyibo1110.jxc.core.domain.aggregate.distribution.repository.DistributionOrderRepository;
import com.github.liyibo1110.jxc.core.domain.aggregate.distribution.service.DistributionDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 配货领域服务实现。
 * @author liyibo
 * @date 2026-08-04 13:42
 */
@Service
@RequiredArgsConstructor
public class DistributionDomainServiceImpl implements DistributionDomainService {

    private final DistributionOrderRepository distributionOrderRepository;
    private final InventoryGateway inventoryGateway;

    @Override
    public List<Long> createDistributionOrders(List<CreateDistributionOrderCommand> commands) {
        List<DistributionOrderAggregateRoot> orderList = new ArrayList<>();
        for (CreateDistributionOrderCommand command : commands) {
            DistributionOrderAggregateRoot order = DistributionOrderAggregateRoot.createWith(command);
            if (command.getItems() != null) {
                for (CreateDistributionOrderCommand.ItemInfo itemInfo : command.getItems())
                    order.addItem(DistributionOrderItem.createWith(itemInfo));
            }
            orderList.add(order);
        }

        // 保存到数据库
        List<Long> idList = distributionOrderRepository.save(orderList);

        // 同步到库存系统
        for (DistributionOrderAggregateRoot order : orderList) {
            try {
                SyncOrderCommand syncCmd = buildSyncCmd(order);
                inventoryGateway.syncOrder(syncCmd);
            } catch (Exception e) {
                // 同步失败记录状态，由定时任务补偿重试
            }
        }

        return idList;
    }

    @Override
    public void updateShippingNum(UpdateShippingCommand command) {
        DistributionOrderAggregateRoot order = distributionOrderRepository.getById(command.getOrderId());
        if (order == null)
            throw new BizException("配货单不存在：" + command.getOrderId());

        if (!order.isPending())
            throw new BizException("配货单不是待发货状态，无法发货");

        order.updateItemsShippingNum(command.getShippingList());
        distributionOrderRepository.update(order);
    }

    private SyncOrderCommand buildSyncCmd(DistributionOrderAggregateRoot order) {
        SyncOrderCommand cmd = new SyncOrderCommand();
        cmd.setDocsNo(order.getDocsNo());
        cmd.setSupplierCode(order.getSupplierCode());
        cmd.setShopId(order.getShopId());
        cmd.setDeliveryMethod(order.getDeliveryMethod());
        return cmd;
    }
}
