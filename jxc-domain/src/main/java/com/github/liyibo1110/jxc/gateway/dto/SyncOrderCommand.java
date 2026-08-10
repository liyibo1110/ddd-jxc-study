package com.github.liyibo1110.jxc.gateway.dto;

import lombok.Data;

/**
 * 同步配货单到库存系统的命令（领域层定义，不暴露库存系统数据结构）。
 * @author liyibo
 * @date 2026-08-10 12:16
 */
@Data
public class SyncOrderCommand {

    private String docsNo;
    private String supplierCode;
    private Integer shopId;
    private String deliveryMethod;
}
