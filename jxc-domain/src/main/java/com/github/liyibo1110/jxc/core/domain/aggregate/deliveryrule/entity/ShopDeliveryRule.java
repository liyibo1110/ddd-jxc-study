package com.github.liyibo1110.jxc.core.domain.aggregate.deliveryrule.entity;

import com.github.liyibo1110.jxc.ddd.Entity;
import lombok.Builder;
import lombok.Getter;

/**
 * 门店配送规则实体。
 * 决定某个门店的某个物料走直配还是统配，以及对应的仓库。
 * @author liyibo
 * @date 2026-08-05 11:10
 */
@Getter
@Builder
public class ShopDeliveryRule implements Entity<Long> {

    private Long id;
    private Integer shopId;
    private Long materialId;
    private String materialCode;

    /** 配送类型：direct_delivery直配，unified_delivery统配 */
    private String deliveryType;

    /** 供应商编码 */
    private String supplierCode;

    /** 供应商名称 */
    private String supplierName;

    @Override
    public Long getUniqueId() {
        return id;
    }

    /**
     * 是否直配
     */
    public boolean isDirectDelivery() {
        return "direct_delivery".equals(deliveryType);
    }

    /**
     * 是否统配
     */
    public boolean isUnifiedDelivery() {
        return "unified_delivery".equals(deliveryType);
    }
}
