package com.github.liyibo1110.jxc.common.constant;

/**
 * 配货单状态枚举。
 * @author liyibo
 * @date 2026-07-30 10:38
 */
public enum DistributionStatusEnum {

    PENDING("pending", "待发货"),
    SHIPPED("shipped", "已发货"),
    COMPLETED("completed", "已完成");

    private final String code;
    private final String desc;

    DistributionStatusEnum(String code, String desc) {
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
