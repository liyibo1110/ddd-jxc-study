package com.github.liyibo1110.jxc.core.domain.aggregate.purchase.service;

import com.github.liyibo1110.jxc.common.command.CreatePurchaseOrderCommand;
import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.entity.PurchaseOrderAggregateRoot;

/**
 * 订货领域服务。
 * @author liyibo
 * @date 2026-08-12 10:34
 */
public interface PurchaseOrderDomainService {

    /**
     * 创建订货单
     */
    PurchaseOrderAggregateRoot create(CreatePurchaseOrderCommand command);

    /**
     * 获取订货单详情（跨聚合调用时使用）
     */
    PurchaseOrderAggregateRoot getOrderDetail(Long orderId);
}
