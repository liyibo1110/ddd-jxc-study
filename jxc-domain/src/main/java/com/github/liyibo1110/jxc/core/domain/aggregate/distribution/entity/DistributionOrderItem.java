package com.github.liyibo1110.jxc.core.domain.aggregate.distribution.entity;

import com.github.liyibo1110.jxc.common.command.CreateDistributionOrderCommand;
import com.github.liyibo1110.jxc.ddd.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 配货单明细实体。
 * DDD推荐充血模型。
 * @author liyibo
 * @date 2026-08-03 14:13
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DistributionOrderItem implements Entity<Long> {

    private Long id;
    private Long distributionOrderId;
    private String orderNo;
    private Long materialId;
    private String materialCode;
    private String materialName;
    private String price;
    private String receivingNum;
    private String orderNum;
    private String shippingNum;
    private String supplierCode;
    private String supplierName;

    @Override
    public Long getUniqueId() {
        return this.id;
    }

    /**
     * 从Command中的ItemInfo构建明细实体
     */
    public static DistributionOrderItem createWith(CreateDistributionOrderCommand.ItemInfo itemInfo) {
        DistributionOrderItem item = new DistributionOrderItem();
        item.orderNo = itemInfo.getOrderNo();
        item.materialId = itemInfo.getMaterialId();
        item.materialCode = itemInfo.getMaterialCode();
        item.materialName = itemInfo.getMaterialName();
        item.price = itemInfo.getPrice();
        item.orderNum = itemInfo.getOrderNum();
        item.supplierCode = itemInfo.getSupplierCode();
        item.supplierName = itemInfo.getSupplierName();
        item.shippingNum = "0";
        item.receivingNum = "0";
        return item;
    }

    /**
     * 更新发货数量
     */
    public void updateShippingNum(String shippingNum) {
        this.shippingNum = shippingNum;
    }

    /**
     * 更新收货数量
     */
    public void updateReceivingNum(String receivingNum) {
        this.receivingNum = receivingNum;
    }

    /**
     * 供Repository组装时设置id。
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 供Repository组装时设置distributionOrderId。
     */
    public void setDistributionOrderId(Long distributionOrderId) {
        this.distributionOrderId = distributionOrderId;
    }
}
