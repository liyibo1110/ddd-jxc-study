package com.github.liyibo1110.jxc.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.liyibo1110.jxc.common.query.QueryDistributionOrderListQuery;
import com.github.liyibo1110.jxc.core.domain.aggregate.distribution.entity.DistributionOrderAggregateRoot;
import com.github.liyibo1110.jxc.core.domain.aggregate.distribution.entity.DistributionOrderItem;
import com.github.liyibo1110.jxc.core.domain.aggregate.distribution.repository.DistributionOrderRepository;
import com.github.liyibo1110.jxc.infrastructure.converter.DistributionOrderConverter;
import com.github.liyibo1110.jxc.infrastructure.converter.DistributionOrderItemConverter;
import com.github.liyibo1110.jxc.infrastructure.persistence.mapper.DistributionOrderItemMapper;
import com.github.liyibo1110.jxc.infrastructure.persistence.mapper.DistributionOrderMapper;
import com.github.liyibo1110.jxc.infrastructure.persistence.po.DistributionOrderItemPO;
import com.github.liyibo1110.jxc.infrastructure.persistence.po.DistributionOrderPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 配货单仓储实现。
 * @author liyibo
 * @date 2026-08-20 10:18
 */
@Repository
@RequiredArgsConstructor
public class DistributionOrderRepositoryImpl implements DistributionOrderRepository {

    private final DistributionOrderMapper distributionOrderMapper;
    private final DistributionOrderItemMapper distributionOrderItemMapper;
    private final TransactionTemplate transactionTemplate;

    @Override
    public List<Long> save(List<DistributionOrderAggregateRoot> orderList) {
        List<Long> orderIdList = new ArrayList<>();
        transactionTemplate.executeWithoutResult(status -> {
            for (DistributionOrderAggregateRoot order : orderList) {
                // Entity 转 PO，写入主表
                DistributionOrderPO orderPO = DistributionOrderConverter.INSTANCE.entityToPo(order);
                distributionOrderMapper.insert(orderPO);

                // MyBatis Plus insert 后，自增id会回写到PO
                Long orderId = orderPO.getId();
                orderIdList.add(orderId);

                // 逐条写入明细，补填外键
                List<DistributionOrderItem> items = order.getItems();
                if (items != null) {
                    for (DistributionOrderItem item : items) {
                        DistributionOrderItemPO itemPO = DistributionOrderItemConverter.INSTANCE.entityToPo(item);
                        // 明细的外键在Entity构建阶段还没有，插入主表后才有
                        itemPO.setDistributionOrderId(orderId);
                        distributionOrderItemMapper.insert(itemPO);
                    }
                }
            }
        });
        return orderIdList;
    }

    @Override
    public List<DistributionOrderAggregateRoot> getByIdList(List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            return Collections.emptyList();
        }

        // 查主表
        List<DistributionOrderPO> orderPOList = distributionOrderMapper.selectBatchIds(idList);
        if (orderPOList.isEmpty()) {
            return Collections.emptyList();
        }

        // 主表 PO 转 Entity
        List<DistributionOrderAggregateRoot> orderList = DistributionOrderConverter.INSTANCE.poToEntityList(orderPOList);

        // 批量查明细（一次SQL，不在循环里单独查）
        List<Long> orderIdList = orderList.stream()
                .map(DistributionOrderAggregateRoot::getId)
                .collect(Collectors.toList());
        List<DistributionOrderItemPO> itemPOList = distributionOrderItemMapper.selectByOrderIdList(orderIdList);

        // 明细按 distributionOrderId 分组
        List<DistributionOrderItem> itemList = DistributionOrderItemConverter.INSTANCE.poToEntityList(itemPOList);
        Map<Long, List<DistributionOrderItem>> itemMap = itemList.stream().collect(Collectors.groupingBy(DistributionOrderItem::getDistributionOrderId));

        // 给每个聚合根设置对应的明细列表
        for (DistributionOrderAggregateRoot order : orderList) {
            order.setItems(itemMap.getOrDefault(order.getId(), Collections.emptyList()));
        }

        return orderList;
    }

    @Override
    public DistributionOrderAggregateRoot getById(Long id) {
        List<DistributionOrderAggregateRoot> list = getByIdList(List.of(id));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<DistributionOrderAggregateRoot> getBaseByIdList(List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            return Collections.emptyList();
        }
        List<DistributionOrderPO> poList = distributionOrderMapper.selectBatchIds(idList);
        return DistributionOrderConverter.INSTANCE.poToEntityList(poList);
    }

    @Override
    public void update(DistributionOrderAggregateRoot order) {
        transactionTemplate.executeWithoutResult(status -> {
            // 更新主表
            DistributionOrderPO orderPO = DistributionOrderConverter.INSTANCE.entityToPo(order);
            distributionOrderMapper.updateById(orderPO);

            // 更新明细（先删后插）
            if (order.getItems() != null && !order.getItems().isEmpty()) {
                LambdaQueryWrapper<DistributionOrderItemPO> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(DistributionOrderItemPO::getDistributionOrderId, order.getId());
                distributionOrderItemMapper.delete(wrapper);

                for (DistributionOrderItem item : order.getItems()) {
                    DistributionOrderItemPO itemPO = DistributionOrderItemConverter.INSTANCE.entityToPo(item);
                    itemPO.setDistributionOrderId(order.getId());
                    distributionOrderItemMapper.insert(itemPO);
                }
            }
        });
    }

    @Override
    public com.github.liyibo1110.jxc.common.result.Page<DistributionOrderAggregateRoot> queryList(
            QueryDistributionOrderListQuery query) {
        LambdaQueryWrapper<DistributionOrderPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DistributionOrderPO::getIsDeleted, 0);
        if (StringUtils.hasText(query.getShopId())) {
            wrapper.eq(DistributionOrderPO::getShopId, query.getShopId());
        }
        if (StringUtils.hasText(query.getSupplierCode())) {
            wrapper.eq(DistributionOrderPO::getSupplierCode, query.getSupplierCode());
        }
        if (StringUtils.hasText(query.getStatus())) {
            wrapper.eq(DistributionOrderPO::getStatus, query.getStatus());
        }
        if (StringUtils.hasText(query.getDeliveryMethod())) {
            wrapper.eq(DistributionOrderPO::getDeliveryMethod, query.getDeliveryMethod());
        }
        if (query.getShopIds() != null && !query.getShopIds().isEmpty()) {
            wrapper.in(DistributionOrderPO::getShopId, query.getShopIds());
        }
        wrapper.orderByDesc(DistributionOrderPO::getCreateTime);

        Page<DistributionOrderPO> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<DistributionOrderPO> result = distributionOrderMapper.selectPage(page, wrapper);

        List<DistributionOrderAggregateRoot> orders = DistributionOrderConverter.INSTANCE.poToEntityList(result.getRecords());
        return com.github.liyibo1110.jxc.common.result.Page.of(orders, result.getTotal(), query.getPageNum(), query.getPageSize());
    }
}
