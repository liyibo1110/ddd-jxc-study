package com.github.liyibo1110.jxc.core.domain.aggregate.purchase.entity;

import com.github.liyibo1110.jxc.common.command.CreatePurchaseOrderCommand;
import com.github.liyibo1110.jxc.common.exception.BizException;
import com.github.liyibo1110.jxc.ddd.AggregateRoot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 订货单聚合根。
 * @author liyibo
 * @date 2026-08-06 16:14
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseOrderAggregateRoot implements AggregateRoot<Long> {

    private Long id;
    private String docsNo;
    private String status;
    private Integer shopId;
    private String shopName;
    private String userName;
    private String createBy;
    private LocalDateTime createTime;

    /** 聚合根持有子实体列表 */
    private List<PurchaseOrderItem> items;

    @Override
    public Long getUniqueId() {
        return this.id;
    }

    /**
     * 是否审核中
     */
    public boolean isApproving() {
        return "approving".equals(this.status);
    }

    /**
     * 是否审核成功
     */
    public boolean isApproveSuccess() {
        return "approve_success".equals(this.status);
    }

    /**
     * 是否作废
     */
    public boolean isCancelled() {
        return "cancel".equals(this.status);
    }

    /**
     * 从Command构建订货单聚合根
     */
    public static PurchaseOrderAggregateRoot createWith(CreatePurchaseOrderCommand command) {
        PurchaseOrderAggregateRoot order = new PurchaseOrderAggregateRoot();
        order.status = "approving";
        order.shopId = command.getShopId();
        order.shopName = command.getShopName();
        order.userName = command.getUserName();
        order.createBy = command.getCreateBy();
        order.createTime = LocalDateTime.now();
        order.items = new ArrayList<>();
        return order;
    }

    /**
     * 添加订货明细
     */
    public PurchaseOrderAggregateRoot addItem(PurchaseOrderItem item) {
        if (this.items == null)
            this.items = new ArrayList<>();

        this.items.add(item);
        return this;
    }

    /**
     * 审核通过
     */
    public void approveSuccess() {
        if (!isApproving())
            throw new BizException("订货单不在审核中状态");

        this.status = "approve_success";
    }

    /**
     * 审核拒绝
     */
    public void approveFail() {
        if (!isApproving())
            throw new BizException("订货单不在审核中状态");

        this.status = "approve_fail";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDocsNo(String docsNo) {
        this.docsNo = docsNo;
    }

    public void setItems(List<PurchaseOrderItem> items) {
        this.items = items;
    }
}
