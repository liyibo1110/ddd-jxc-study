package com.github.liyibo1110.jxc.statistics.domain.aggregate.orderitem.service.impl;

import com.github.liyibo1110.jxc.common.exception.BizException;
import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.entity.PurchaseOrderAggregateRoot;
import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.entity.PurchaseOrderItem;
import com.github.liyibo1110.jxc.statistics.domain.aggregate.orderitem.entity.StatisticsOrderDocsItem;
import com.github.liyibo1110.jxc.statistics.domain.aggregate.orderitem.repository.StatisticsOrderDocsItemRepository;
import com.github.liyibo1110.jxc.statistics.domain.aggregate.orderitem.service.StatisticsOrderDocsItemDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 订货统计领域服务实现（statistics上下文）。
 * @author liyibo
 * @date 2026-08-11 10:50
 */
@Service
@RequiredArgsConstructor
public class StatisticsOrderDocsItemDomainServiceImpl implements StatisticsOrderDocsItemDomainService {

    private final StatisticsOrderDocsItemRepository statisticsOrderDocsItemRepository;

    @Override
    public void createFromOrder(PurchaseOrderAggregateRoot order) {
        if (order == null || order.getItems() == null || order.getItems().isEmpty())
            throw new BizException("订货单数据不完整，无法生成统计记录");

        List<StatisticsOrderDocsItem> statItems = new ArrayList<>();
        for (PurchaseOrderItem item : order.getItems()) {
            StatisticsOrderDocsItem statItem = StatisticsOrderDocsItem.builder()
                    .orderItemId(item.getUniqueId())
                    .orderId(order.getUniqueId())
                    .materialCode(item.getMaterialCode())
                    .materialName(item.getMaterialName())
                    .orderCount(item.getCount())
                    .shopId(Long.valueOf(order.getShopId()))
                    .shopName(order.getShopName())
                    .orderDate(LocalDate.now())
                    .orderTime(order.getCreateTime())
                    .build();
            statItems.add(statItem);
        }

        statisticsOrderDocsItemRepository.saveBatch(statItems);
    }
}
