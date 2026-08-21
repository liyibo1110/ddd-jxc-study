package com.github.liyibo1110.jxc.infrastructure.repository;

import com.github.liyibo1110.jxc.common.query.QueryPurchaseOrderListQuery;
import com.github.liyibo1110.jxc.common.result.PageInfo;
import com.github.liyibo1110.jxc.infrastructure.persistence.mapper.StatisticsOrderDocsItemMapper;
import com.github.liyibo1110.jxc.infrastructure.persistence.po.StatisticsOrderDocsItemPO;
import com.github.liyibo1110.jxc.statistics.domain.aggregate.orderitem.entity.StatisticsOrderDocsItem;
import com.github.liyibo1110.jxc.statistics.domain.aggregate.orderitem.repository.StatisticsOrderDocsItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订货统计仓储实现。
 * @author liyibo
 * @date 2026-08-20 10:32
 */
@Repository
@RequiredArgsConstructor
public class StatisticsOrderDocsItemRepositoryImpl implements StatisticsOrderDocsItemRepository {

    private final StatisticsOrderDocsItemMapper statisticsOrderDocsItemMapper;

    @Override
    public void saveBatch(List<StatisticsOrderDocsItem> items) {
        if (items == null || items.isEmpty()) {
            return;
        }
        for (StatisticsOrderDocsItem item : items) {
            StatisticsOrderDocsItemPO po = toPO(item);
            statisticsOrderDocsItemMapper.insert(po);
        }
    }

    @Override
    public List<StatisticsOrderDocsItem> getByOrderId(Long orderId) {
        com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<StatisticsOrderDocsItemPO> wrapper =
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<>();
        wrapper.eq(StatisticsOrderDocsItemPO::getOrderId, orderId)
                .eq(StatisticsOrderDocsItemPO::getIsDeleted, 0);
        List<StatisticsOrderDocsItemPO> poList = statisticsOrderDocsItemMapper.selectList(wrapper);
        return poList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public com.github.liyibo1110.jxc.common.result.Page<StatisticsOrderDocsItem> queryStatisticsOrderList(
            QueryPurchaseOrderListQuery query, PageInfo pageInfo) {
        com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<StatisticsOrderDocsItemPO> wrapper =
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<>();
        wrapper.eq(StatisticsOrderDocsItemPO::getIsDeleted, 0);
        if (query.getShopIds() != null && !query.getShopIds().isEmpty()) {
            wrapper.in(StatisticsOrderDocsItemPO::getShopId, query.getShopIds());
        }

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<StatisticsOrderDocsItemPO> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
                        pageInfo.getPageNum(), pageInfo.getPageSize());
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<StatisticsOrderDocsItemPO> result =
                statisticsOrderDocsItemMapper.selectPage(page, wrapper);

        List<StatisticsOrderDocsItem> items = result.getRecords().stream().map(this::toEntity).collect(Collectors.toList());
        return com.github.liyibo1110.jxc.common.result.Page.of(items, result.getTotal(), pageInfo);
    }

    private StatisticsOrderDocsItemPO toPO(StatisticsOrderDocsItem entity) {
        StatisticsOrderDocsItemPO po = new StatisticsOrderDocsItemPO();
        po.setOrderItemId(entity.getOrderItemId());
        po.setOrderId(entity.getOrderId());
        po.setMaterialCode(entity.getMaterialCode());
        po.setMaterialName(entity.getMaterialName());
        po.setOrderCount(entity.getOrderCount());
        po.setUnit(entity.getUnit());
        po.setShopId(entity.getShopId());
        po.setShopName(entity.getShopName());
        po.setOrderDate(entity.getOrderDate());
        po.setOrderTime(entity.getOrderTime());
        po.setMaterialCategoryId(entity.getMaterialCategoryId());
        po.setIsDeleted(0);
        return po;
    }

    private StatisticsOrderDocsItem toEntity(StatisticsOrderDocsItemPO po) {
        return StatisticsOrderDocsItem.builder()
                .orderItemId(po.getOrderItemId())
                .orderId(po.getOrderId())
                .materialCode(po.getMaterialCode())
                .materialName(po.getMaterialName())
                .orderCount(po.getOrderCount())
                .unit(po.getUnit())
                .shopId(po.getShopId())
                .shopName(po.getShopName())
                .orderDate(po.getOrderDate())
                .orderTime(po.getOrderTime())
                .materialCategoryId(po.getMaterialCategoryId())
                .build();
    }
}
