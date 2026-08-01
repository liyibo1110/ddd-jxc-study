package com.github.liyibo1110.jxc.common.command;

import lombok.Data;

import java.util.List;

/**
 * 创建配货单命令
 * @author liyibo
 * @date 2026-07-31 13:58
 */
@Data
public class CreateDistributionOrderCommand {

    /** 关联的订货单单号 */
    private String orderNo;

    /** 配送类型：direct_delivery直配，unified_delivery统配 */
    private String deliveryMethod;

    /** 门店id */
    private Integer shopId;

    /** 门店名称 */
    private String shopName;

    /** 供应商编码 */
    private String supplierCode;

    /** 供应商名称 */
    private String supplierName;

    /** 订货时间（毫秒时间戳） */
    private Long orderTime;

    /** 创建人 */
    private String createBy;

    /** 明细列表 */
    private List<ItemInfo> items;

    @Data
    public static class ItemInfo {
        private String orderNo;
        private Long materialId;
        private String materialCode;
        private String materialName;
        private String price;
        private String orderNum;
        private String supplierCode;
        private String supplierName;
    }
}
