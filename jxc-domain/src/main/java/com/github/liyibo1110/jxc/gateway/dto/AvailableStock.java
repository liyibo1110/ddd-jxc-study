package com.github.liyibo1110.jxc.gateway.dto;

import lombok.Data;

/**
 * 库存信息（Gateway返回的领域层DTO）。
 * @author liyibo
 * @date 2026-08-10 12:16
 */
@Data
public class AvailableStock {

    private Long materialId;
    private String materialCode;

    /** 可用库存数量 */
    private String availableCount;
    private String warehouseCode;
}
