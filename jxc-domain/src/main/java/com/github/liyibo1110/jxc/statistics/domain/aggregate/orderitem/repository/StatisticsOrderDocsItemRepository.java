package com.github.liyibo1110.jxc.statistics.domain.aggregate.orderitem.repository;

import com.github.liyibo1110.jxc.common.query.QueryPurchaseOrderListQuery;
import com.github.liyibo1110.jxc.common.result.Page;
import com.github.liyibo1110.jxc.common.result.PageInfo;
import com.github.liyibo1110.jxc.statistics.domain.aggregate.orderitem.entity.StatisticsOrderDocsItem;

import java.util.List;

/**
 * 订货明细统计仓储接口（statistics上下文）。
 * @author liyibo
 * @date 2026-08-11 10:49
 */
public interface StatisticsOrderDocsItemRepository {

    /**
     * 批量保存统计记录
     */
    void saveBatch(List<StatisticsOrderDocsItem> items);

    /**
     * 按订货单id查询统计记录
     */
    List<StatisticsOrderDocsItem> getByOrderId(Long orderId);

    /**
     * 分页查询统计记录
     */
    Page<StatisticsOrderDocsItem> queryStatisticsOrderList(QueryPurchaseOrderListQuery query, PageInfo pageInfo);
}
