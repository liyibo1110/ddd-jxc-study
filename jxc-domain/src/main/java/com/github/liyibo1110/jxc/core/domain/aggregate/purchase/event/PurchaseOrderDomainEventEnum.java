package com.github.liyibo1110.jxc.core.domain.aggregate.purchase.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订货单领域事件枚举。
 * @author liyibo
 * @date 2026-08-07 10:29
 */
@AllArgsConstructor
@Getter
public enum PurchaseOrderDomainEventEnum {

    /** 订货单已创建 */
    ORDER_CREATED("PurchaseOrder", "Created");

    private final String subject;
    private final String tag;
}
