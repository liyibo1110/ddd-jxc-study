package com.github.liyibo1110.jxc.core.domain.aggregate.shop.entity;

import com.github.liyibo1110.jxc.ddd.Entity;
import lombok.Builder;
import lombok.Getter;

/**
 * 门店实体（从Gateway获取，只读）。
 * @author liyibo
 * @date 2026-08-07 10:31
 */
@Getter
@Builder
public class ShopInfoEntity implements Entity<Long> {

    private Long id;
    private String shopName;
    private String shopCode;
    private String address;
    private Boolean operable;
    private Boolean autoDistributionEnabled;

    @Override
    public Long getUniqueId() {
        return this.id;
    }

    /**
     * 门店是否可以正常操作
     */
    public boolean isOperable() {
        return Boolean.TRUE.equals(this.operable);
    }

    /**
     * 是否开启自动配货
     */
    public boolean isAutoDistributionEnabled() {
        return Boolean.TRUE.equals(this.autoDistributionEnabled);
    }
}
