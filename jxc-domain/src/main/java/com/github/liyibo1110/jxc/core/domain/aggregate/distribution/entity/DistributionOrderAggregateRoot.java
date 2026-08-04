package com.github.liyibo1110.jxc.core.domain.aggregate.distribution.entity;

import com.github.liyibo1110.jxc.common.command.CreateDistributionOrderCommand;
import com.github.liyibo1110.jxc.common.exception.BizException;
import com.github.liyibo1110.jxc.ddd.AggregateRoot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 配货单聚合根。
 * @author liyibo
 * @date 2026-08-03 14:56
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DistributionOrderAggregateRoot implements AggregateRoot<Long> {

    /** 供Repository在读取时设置id */
    @Setter
    private Long id;
    /** 供Repository在读取时设置docsNo */
    @Setter
    private String docsNo;

    private String orderNo;
    private String status;
    private String syncToStockStatus;
    private String deliveryMethod;
    private Integer shopId;
    private String shopName;
    private String supplierCode;
    private String supplierName;
    private LocalDateTime orderTime;
    private LocalDateTime shippingTime;
    private String arrivalTime;
    private String receivingDate;
    private String createBy;

    /** 聚合根持有子实体列表 */
    @Setter
    private List<DistributionOrderItem> items;

    @Override
    public Long getUniqueId() {
        return id;
    }

    /**
     * 是否待发货。
     */
    public boolean isPending() {
        return "pending".equals(status);
    }

    /**
     * 是否已发货。
     */
    public boolean isShipped() {
        return "shipped".equals(status);
    }

    /**
     * 是否已完成。
     */
    public boolean isCompleted() {
        return "completed".equals(status);
    }

    /**
     * 从Command构建配货单聚合根。
     */
    public static DistributionOrderAggregateRoot createWith(CreateDistributionOrderCommand command) {
        DistributionOrderAggregateRoot order = new DistributionOrderAggregateRoot();
        order.orderNo = command.getOrderNo();
        order.status = "pending";
        order.deliveryMethod = command.getDeliveryMethod();
        order.shopId = command.getShopId();
        order.shopName = command.getShopName();
        order.supplierCode = command.getSupplierCode();
        order.supplierName = command.getSupplierName();
        order.orderTime = LocalDateTime.now();
        order.createBy = command.getCreateBy();
        order.items = new ArrayList<>();
        return order;
    }

    /**
     * 添加明细。
     */
    public DistributionOrderAggregateRoot addItem(DistributionOrderItem item) {
        if (items == null)
            items = new ArrayList<>();

        items.add(item);
        return this;
    }

    /**
     * 通过聚合根更新某条明细的发货数量。
     */
    public void updateItemShippingNum(Long itemId, String shippingNum) {
        DistributionOrderItem target = items.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new BizException("配货单明细不存在：" + itemId));
        target.updateShippingNum(shippingNum);
    }

    /**
     * 更新收货数量后标记完成
     */
    public void complete() {
        if (!isShipped())
            throw new BizException("只有已发货的配货单才能完成收货");

        status = "completed";
    }
}
