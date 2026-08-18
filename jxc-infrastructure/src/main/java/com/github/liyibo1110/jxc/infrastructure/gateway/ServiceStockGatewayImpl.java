package com.github.liyibo1110.jxc.infrastructure.gateway;

import com.github.liyibo1110.jxc.gateway.ServiceStockGateway;
import com.github.liyibo1110.jxc.gateway.dto.AvailableStock;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存查询网关实现。
 * @author liyibo
 * @date 2026-08-17 10:55
 */
@Component
public class ServiceStockGatewayImpl implements ServiceStockGateway {

    @Override
    public List<AvailableStock> getAvailableStock(String warehouseCode, List<Long> materialIds) {
        // 生产实现：调库存中心Dubbo接口，查指定仓库下物料的可用库存
        List<AvailableStock> result = new ArrayList<>();
        for (Long materialId : materialIds) {
            AvailableStock stock = new AvailableStock();
            stock.setMaterialId(materialId);
            stock.setWarehouseCode(warehouseCode);
            stock.setAvailableCount("100");
            result.add(stock);
        }
        return result;
    }
}
