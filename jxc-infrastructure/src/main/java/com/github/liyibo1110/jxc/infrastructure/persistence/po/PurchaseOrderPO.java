package com.github.liyibo1110.jxc.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订货单主表PO。
 * @author liyibo
 * @date 2026-08-14 10:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("purchase_order")
public class PurchaseOrderPO extends BasePO {

    private String docsNo;
    private String status;
    private Integer shopId;
    private String shopName;
    private String userName;
}
