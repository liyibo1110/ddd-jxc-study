package com.github.liyibo1110.jxc.common.constant;

/**
 * 配送类型枚举。
 * @author liyibo
 * @date 2026-07-30 10:37
 */
public enum DeliveryMethodEnum {

    DIRECT_DELIVERY("direct_delivery", "直配"),
    UNIFIED_DELIVERY("unified_delivery", "统配");

    private final String code;
    private final String desc;

    DeliveryMethodEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
