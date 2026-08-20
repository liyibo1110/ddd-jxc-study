package com.github.liyibo1110.jxc.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.liyibo1110.jxc.common.query.QueryPurchaseOrderListQuery;
import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.entity.PurchaseOrderAggregateRoot;
import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.entity.PurchaseOrderItem;
import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.repository.PurchaseOrderRepository;
import com.github.liyibo1110.jxc.infrastructure.converter.PurchaseOrderConverter;
import com.github.liyibo1110.jxc.infrastructure.persistence.mapper.PurchaseOrderItemMapper;
import com.github.liyibo1110.jxc.infrastructure.persistence.mapper.PurchaseOrderMapper;
import com.github.liyibo1110.jxc.infrastructure.persistence.po.PurchaseOrderItemPO;
import com.github.liyibo1110.jxc.infrastructure.persistence.po.PurchaseOrderPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 订货单仓储实现。
 * @author liyibo
 * @date 2026-08-20 10:28
 */
@Repository
@RequiredArgsConstructor
public class PurchaseOrderRepositoryImpl implements PurchaseOrderRepository {

    private final PurchaseOrderMapper purchaseOrderMapper;
    private final PurchaseOrderItemMapper purchaseOrderItemMapper;
    private final TransactionTemplate transactionTemplate;

    @Override
    public Long save(PurchaseOrderAggregateRoot purchaseOrder) {
        Long[] idHolder = new Long[1];
        transactionTemplate.executeWithoutResult(status -> {
            // Entity 转 PO，写入主表
            PurchaseOrderPO orderPO = PurchaseOrderConverter.INSTANCE.entityToPo(purchaseOrder);
            purchaseOrderMapper.insert(orderPO);
            Long orderId = orderPO.getId();
            idHolder[0] = orderId;

            // 批量写入明细
            if (purchaseOrder.getItems() != null) {
                for (PurchaseOrderItem item : purchaseOrder.getItems()) {
                    PurchaseOrderItemPO itemPO = PurchaseOrderConverter.INSTANCE.itemEntityToPo(item);
                    itemPO.setOrderId(orderId);
                    purchaseOrderItemMapper.insert(itemPO);
                }
            }
        });
        return idHolder[0];
    }

    @Override
    public PurchaseOrderAggregateRoot getById(Long id) {
        PurchaseOrderPO orderPO = purchaseOrderMapper.selectById(id);
        if (orderPO == null) {
            return null;
        }
        PurchaseOrderAggregateRoot order = PurchaseOrderConverter.INSTANCE.poToEntity(orderPO);
        attachItems(order);
        return order;
    }

    @Override
    public PurchaseOrderAggregateRoot getByDocsNo(String docsNo) {
        LambdaQueryWrapper<PurchaseOrderPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PurchaseOrderPO::getDocsNo, docsNo)
               .eq(PurchaseOrderPO::getIsDeleted, 0);
        PurchaseOrderPO orderPO = purchaseOrderMapper.selectOne(wrapper);
        if (orderPO == null) {
            return null;
        }
        PurchaseOrderAggregateRoot order = PurchaseOrderConverter.INSTANCE.poToEntity(orderPO);
        attachItems(order);
        return order;
    }

    @Override
    public com.github.liyibo1110.jxc.common.result.Page<PurchaseOrderAggregateRoot> queryList(QueryPurchaseOrderListQuery query) {
        LambdaQueryWrapper<PurchaseOrderPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PurchaseOrderPO::getIsDeleted, 0);
        if (StringUtils.hasText(query.getShopId())) {
            wrapper.eq(PurchaseOrderPO::getShopId, query.getShopId());
        }
        if (StringUtils.hasText(query.getStatus())) {
            wrapper.eq(PurchaseOrderPO::getStatus, query.getStatus());
        }
        if (query.getShopIds() != null && !query.getShopIds().isEmpty()) {
            wrapper.in(PurchaseOrderPO::getShopId, query.getShopIds());
        }
        wrapper.orderByDesc(PurchaseOrderPO::getCreateTime);

        Page<PurchaseOrderPO> page = new Page<>(query.getPageNum(), query.getPageSize());
        Page<PurchaseOrderPO> result = purchaseOrderMapper.selectPage(page, wrapper);

        List<PurchaseOrderAggregateRoot> orders = PurchaseOrderConverter.INSTANCE.poToEntityList(result.getRecords());
        return com.github.liyibo1110.jxc.common.result.Page.of(
                orders, result.getTotal(), query.getPageNum(), query.getPageSize());
    }

    @Override
    public void updateStatus(Long orderId, String status) {
        PurchaseOrderPO po = new PurchaseOrderPO();
        po.setId(orderId);
        po.setStatus(status);
        purchaseOrderMapper.updateById(po);
    }

    /**
     * 给聚合根填充明细列表
     */
    private void attachItems(PurchaseOrderAggregateRoot order) {
        List<PurchaseOrderItemPO> itemPOList = purchaseOrderItemMapper.selectByOrderIdList(List.of(order.getUniqueId()));
        List<PurchaseOrderItem> items = PurchaseOrderConverter.INSTANCE.itemPoToEntityList(itemPOList);
        order.setItems(items);
    }
}
