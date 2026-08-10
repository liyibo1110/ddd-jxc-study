package com.github.liyibo1110.jxc.gateway.dto;

import lombok.Data;

/**
 * 物料信息（Gateway返回的领域层DTO）。
 * @author liyibo
 * @date 2026-08-10 12:15
 */
@Data
public class MaterialInfo {

    private Long materialId;
    private String materialCode;
    private String materialName;

    /** 供应商编码 */
    private String supplierCode;

    /** 供应商名称 */
    private String supplierName;

    /** 物料分类id */
    private Long categoryId;
}
