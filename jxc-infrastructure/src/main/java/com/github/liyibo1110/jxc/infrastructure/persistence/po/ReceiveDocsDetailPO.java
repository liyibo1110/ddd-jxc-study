package com.github.liyibo1110.jxc.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 收货单明细PO
 * @author liyibo
 * @date 2026-08-14 10:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("receive_docs_detail")
public class ReceiveDocsDetailPO extends BasePO {

    private String distributionOrderNo;
    private String receiveNo;
    private Long materialId;
    private String materialCode;
    private String materialName;
    private String receivingNum;
    private String orderNum;
    private String shippingNum;
    private LocalDateTime receiveTime;
}
