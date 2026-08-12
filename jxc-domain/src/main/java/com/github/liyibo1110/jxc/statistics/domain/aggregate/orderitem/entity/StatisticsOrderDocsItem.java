package com.github.liyibo1110.jxc.statistics.domain.aggregate.orderitem.entity;

import com.github.liyibo1110.jxc.ddd.AggregateRoot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订货明细统计聚合根（statistics上下文）。
 * 注意：这是统计上下文独立的聚合根，和core上下文的订货单是不同的领域概念。
 * @author liyibo
 * @date 2026-08-11 10:47
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StatisticsOrderDocsItem implements AggregateRoot<Long> {

    private Long id;
    private Long orderItemId;
    private Long orderId;
    private String materialCode;
    private String materialName;
    private String orderCount;
    private String unit;
    private Long shopId;
    private String shopName;
    private LocalDate orderDate;
    private LocalDateTime orderTime;
    private Long materialCategoryId;

    @Override
    public Long getUniqueId() {
        return this.id;
    }

    // 供Repository组装时使用
    public void setId(Long id) {
        this.id = id;
    }

}
