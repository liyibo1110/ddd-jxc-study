package com.github.liyibo1110.jxc.core.domain.aggregate.distribution.service;

import com.github.liyibo1110.jxc.common.command.CreateDistributionOrderCommand;
import com.github.liyibo1110.jxc.common.command.UpdateShippingCommand;

import java.util.List;

/**
 * 配货领域服务。
 * @author liyibo
 * @date 2026-08-04 13:38
 */
public interface DistributionDomainService {

    /**
     * 批量创建配货单并同步到库存系统
     */
    List<Long> createDistributionOrders(List<CreateDistributionOrderCommand> commands);

    /**
     * 更新发货数量（供应商发货操作）
     */
    void updateShippingNum(UpdateShippingCommand command);
}
