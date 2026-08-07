package com.github.liyibo1110.jxc.core.domain.aggregate.purchase.entity;

import com.github.liyibo1110.jxc.common.command.CreatePurchaseOrderCommand;
import com.github.liyibo1110.jxc.ddd.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 订货单明细实体。
 * @author liyibo
 * @date 2026-08-06 16:18
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchaseOrderItem implements Entity<Long> {

    private Long id;
    private Long orderId;
    private Long materialId;
    private String materialCode;
    private String materialName;
    private String count;

    @Override
    public Long getUniqueId() {
        return this.id;
    }

    /**
     * 从Command中的ItemInfo构建明细实体
     */
    public static PurchaseOrderItem createWith(CreatePurchaseOrderCommand.ItemInfo itemInfo) {
        PurchaseOrderItem item = new PurchaseOrderItem();
        item.materialId = itemInfo.getMaterialId();
        item.materialCode = itemInfo.getMaterialCode();
        item.materialName = itemInfo.getMaterialName();
        item.count = itemInfo.getCount();
        return item;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
