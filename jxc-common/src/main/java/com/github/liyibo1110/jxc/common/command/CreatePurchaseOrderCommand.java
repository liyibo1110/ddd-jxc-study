package com.github.liyibo1110.jxc.common.command;

import lombok.Data;

import java.util.List;

/**
 * 创建订货单命令
 * @author liyibo
 * @date 2026-07-31 13:56
 */
@Data
public class CreatePurchaseOrderCommand {

    /** 门店id */
    private Integer shopId;

    /** 门店名称 */
    private String shopName;

    /** 提交人 */
    private String userName;

    /** 创建人 */
    private String createBy;

    /** 订货明细列表 */
    private List<ItemInfo> items;

    @Data
    public static class ItemInfo {

        /** 物料id */
        private Long materialId;

        /** 物料编码 */
        private String materialCode;

        /** 物料名称 */
        private String materialName;

        /** 订货数量 */
        private String count;
    }
}
