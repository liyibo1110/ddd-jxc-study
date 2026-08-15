package com.github.liyibo1110.jxc.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订货单明细PO。
 * @author liyibo
 * @date 2026-08-14 10:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("purchase_order_item")
public class PurchaseOrderItemPO extends BasePO {

    private Long orderId;
    private Long materialId;
    private String materialCode;
    private String materialName;
    private String count;
}
