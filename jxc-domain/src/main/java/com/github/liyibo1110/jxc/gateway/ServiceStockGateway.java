package com.github.liyibo1110.jxc.gateway;

import com.github.liyibo1110.jxc.gateway.dto.AvailableStock;

import java.util.List;

/**
 * 库存查询网关（防腐层接口）。
 * @author liyibo
 * @date 2026-08-10 12:24
 */
public interface ServiceStockGateway {

    /**
     * 查询指定仓库下物料的可用库存
     */
    List<AvailableStock> getAvailableStock(String warehouseCode, List<Long> materialIds);
}
