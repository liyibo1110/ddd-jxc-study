package com.github.liyibo1110.jxc.core.domain.aggregate.purchase.repository;

import com.github.liyibo1110.jxc.common.query.QueryPurchaseOrderListQuery;
import com.github.liyibo1110.jxc.common.result.Page;
import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.entity.PurchaseOrderAggregateRoot;

/**
 * 订货单仓储接口。
 * @author liyibo
 * @date 2026-08-06 16:19
 */
public interface PurchaseOrderRepository {

    /**
     * 保存订货单（主表+明细），返回自增id
     */
    Long save(PurchaseOrderAggregateRoot purchaseOrder);

    /**
     * 按id查询订货单（含明细）
     */
    PurchaseOrderAggregateRoot getById(Long id);

    /**
     * 按docsNo查询订货单（含明细）
     */
    PurchaseOrderAggregateRoot getByDocsNo(String docsNo);

    /**
     * 分页查询订货单列表
     */
    Page<PurchaseOrderAggregateRoot> queryList(QueryPurchaseOrderListQuery query);

    /**
     * 更新订货单状态
     */
    void updateStatus(Long orderId, String status);
}
