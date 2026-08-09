package com.github.liyibo1110.jxc.core.domain.aggregate.purchase.event;

import lombok.Data;

/**
 * @author liyibo
 * @date 2026-08-07 10:29
 */
@Data
public class PurchaseOrderCreatedEvent {

    /** 订货单id */
    private Long orderId;
}
