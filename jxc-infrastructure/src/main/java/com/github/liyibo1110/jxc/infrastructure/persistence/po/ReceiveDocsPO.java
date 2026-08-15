package com.github.liyibo1110.jxc.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 收货单主表PO。
 * @author liyibo
 * @date 2026-08-14 10:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("receive_docs")
public class ReceiveDocsPO extends BasePO {

    private String distributionOrderNo;
    private String docsNo;
    private Integer status;
    private LocalDate receiveDate;
    private LocalDateTime receiveTime;
    private Integer shopId;
    private String shopName;
    private String supplierCode;
    private String supplierName;
    private String userName;
}
