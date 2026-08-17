package com.github.liyibo1110.jxc.infrastructure.gateway;

import com.github.liyibo1110.jxc.common.util.StructuredLog;
import com.github.liyibo1110.jxc.gateway.InventoryGateway;
import com.github.liyibo1110.jxc.gateway.dto.SyncOrderCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 库存网关实现。
 * 生产环境：通过Dubbo调用库存中心RPC接口，把领域层的SyncOrderCommand，转成库存中心需要的入参，处理RPC异常，做字段映射。
 * @author liyibo
 * @date 2026-08-17 10:48
 */
@Slf4j
@Component
public class InventoryGatewayImpl implements InventoryGateway {

    @Override
    public void syncOrder(SyncOrderCommand command) {
        try {
            // 生产实现：
            // 1. 把领域层SyncOrderCommand转成库存中心的CreateInventoryOrderCommand
            // 2. 调 InventoryFacade.createInventoryOrder(invCmd)
            // 3. 处理RPC异常，转换为业务异常
            // 4. 库存中心接口升级时，只改这里，领域服务代码零修改
            StructuredLog.info(log)
                    .message("同步配货单到库存系统")
                    .put("docsNo", command.getDocsNo())
                    .put("supplierCode", command.getSupplierCode())
                    .log();
        } catch (Exception e) {
            StructuredLog.error(log)
                    .message("同步配货单到库存系统失败")
                    .put("docsNo", command.getDocsNo())
                    .exception(e)
                    .log();
            throw e;
        }
    }
}
