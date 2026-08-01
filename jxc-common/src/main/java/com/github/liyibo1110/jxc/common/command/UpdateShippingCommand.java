package com.github.liyibo1110.jxc.common.command;

import lombok.Data;

import java.util.List;

/**
 * 更新发货数量命令
 * @author liyibo
 * @date 2026-07-31 13:59
 */
@Data
public class UpdateShippingCommand {

    /** 配货单id */
    private Long orderId;

    /** 发货明细列表 */
    private List<ShippingItem> shippingList;

    @Data
    public static class ShippingItem {

        /** 明细id */
        private Long itemId;

        /** 发货数量 */
        private String shippingNum;
    }
}
