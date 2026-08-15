package com.github.liyibo1110.jxc.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 配货单明细PO。
 * @author liyibo
 * @date 2026-08-14 10:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("distribution_order_item")
public class DistributionOrderItemPO extends BasePO {

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
}
