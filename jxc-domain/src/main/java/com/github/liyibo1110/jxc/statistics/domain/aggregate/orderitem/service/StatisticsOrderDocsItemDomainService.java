package com.github.liyibo1110.jxc.statistics.domain.aggregate.orderitem.service;

import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.entity.PurchaseOrderAggregateRoot;

/**
 * 订货统计领域服务（statistics上下文）。
 * @author liyibo
 * @date 2026-08-11 10:50
 */
public interface StatisticsOrderDocsItemDomainService {

    /**
     * 根据订货单创建统计记录
     * 注意：入参是core上下文的PurchaseOrder，统计服务从中提取需要的数据
     * 这是跨上下文引用，在应用层编排，不在领域层直接调用
     */
    void createFromOrder(PurchaseOrderAggregateRoot order);
}
