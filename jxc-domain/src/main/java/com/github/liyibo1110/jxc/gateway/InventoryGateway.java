package com.github.liyibo1110.jxc.gateway;

import com.github.liyibo1110.jxc.gateway.dto.SyncOrderCommand;

/**
 * 库存网关（防腐层接口）。
 * @author liyibo
 * @date 2026-08-10 12:24
 */
public interface InventoryGateway {

    /**
     * 同步配货单到库存系统
     */
    void syncOrder(SyncOrderCommand command);
}
