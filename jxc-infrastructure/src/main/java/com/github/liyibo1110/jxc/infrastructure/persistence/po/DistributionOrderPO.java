package com.github.liyibo1110.jxc.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 配货单主表PO。
 * @author liyibo
 * @date 2026-08-14 10:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("distribution_order")
public class DistributionOrderPO extends BasePO {

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
}
