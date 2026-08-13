package com.github.liyibo1110.jxc.core.domain.aggregate.purchase.service.impl;

import com.github.liyibo1110.jxc.common.command.CreatePurchaseOrderCommand;
import com.github.liyibo1110.jxc.common.exception.BizException;
import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.entity.PurchaseOrderAggregateRoot;
import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.entity.PurchaseOrderItem;
import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.repository.PurchaseOrderRepository;
import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.service.PurchaseOrderDomainService;
import com.github.liyibo1110.jxc.gateway.MaterialGateway;
import com.github.liyibo1110.jxc.gateway.dto.MaterialInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订货领域服务实现。
 * @author liyibo
 * @date 2026-08-12 10:34
 */
@Service
@RequiredArgsConstructor
public class PurchaseOrderDomainServiceImpl implements PurchaseOrderDomainService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final MaterialGateway materialGateway;

    @Override
    public PurchaseOrderAggregateRoot create(CreatePurchaseOrderCommand command) {
        // 校验物料信息
        if (command.getItems() == null || command.getItems().isEmpty())
            throw new BizException("订货明细不能为空");

        // 构建订货单聚合根
        PurchaseOrderAggregateRoot order = PurchaseOrderAggregateRoot.createWith(command);

        // 从物料中心获取物料名称（确保名称与物料中心一致）
        List<Long> materialIds = command.getItems().stream()
                .map(CreatePurchaseOrderCommand.ItemInfo::getMaterialId)
                .toList();
        List<MaterialInfo> materialInfoList = materialGateway.getByIds(materialIds);

        // 添加明细
        for (CreatePurchaseOrderCommand.ItemInfo itemInfo : command.getItems()) {
            // 用物料中心的名称覆盖前端传入的名称
            materialInfoList.stream()
                    .filter(m -> m.getMaterialId().equals(itemInfo.getMaterialId()))
                    .findFirst()
                    .ifPresent(m -> itemInfo.setMaterialName(m.getMaterialName()));
            order.addItem(PurchaseOrderItem.createWith(itemInfo));
        }

        // 持久化
        purchaseOrderRepository.save(order);
        return order;
    }

    @Override
    public PurchaseOrderAggregateRoot getOrderDetail(Long orderId) {
        PurchaseOrderAggregateRoot order = purchaseOrderRepository.getById(orderId);
        if (order == null)
            throw new BizException("订货单不存在：" + orderId);

        return order;
    }
}
