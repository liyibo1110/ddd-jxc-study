package com.github.liyibo1110.jxc.core.domain.aggregate.distribution.repository;

import com.github.liyibo1110.jxc.common.query.QueryDistributionOrderListQuery;
import com.github.liyibo1110.jxc.common.result.Page;
import com.github.liyibo1110.jxc.core.domain.aggregate.distribution.entity.DistributionOrderAggregateRoot;

import java.util.List;

/**
 * 配货单仓储接口。
 * @author liyibo
 * @date 2026-08-04 13:30
 */
public interface DistributionOrderRepository {

    /**
     * 批量保存配货单（主表+明细），返回生成的id列表
     */
    List<Long> save(List<DistributionOrderAggregateRoot> orderList);

    /**
     * 按id列表查询配货单（含明细）
     */
    List<DistributionOrderAggregateRoot> getByIdList(List<Long> idList);

    /**
     * 按id查询配货单（含明细）
     */
    DistributionOrderAggregateRoot getById(Long id);

    /**
     * 按id列表查询配货单基础信息（不含明细）
     */
    List<DistributionOrderAggregateRoot> getBaseByIdList(List<Long> idList);

    /**
     * 更新配货单（主表+明细）
     */
    void update(DistributionOrderAggregateRoot order);

    /**
     * 分页查询配货单列表
     */
    Page<DistributionOrderAggregateRoot> queryList(QueryDistributionOrderListQuery query);
}
