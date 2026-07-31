package com.github.liyibo1110.jxc.common.constant;

/**
 * 订货单状态枚举。
 * @author liyibo
 * @date 2026-07-30 10:39
 */
public enum PurchaseOrderStatusEnum {

    APPROVING("approving", "审核中"),
    APPROVE_SUCCESS("approve_success", "审核成功"),
    APPROVE_FAIL("approve_fail", "审核失败"),
    CANCEL("cancel", "作废");

    private final String code;
    private final String desc;

    PurchaseOrderStatusEnum(String code, String desc) {
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
