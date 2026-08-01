package com.github.liyibo1110.jxc.common.command;

import lombok.Data;

/**
 * 手动创建配货单命令
 * @author liyibo
 * @date 2026-07-31 13:59
 */
@Data
public class ManualCreateDistributionCommand {

    /** 订货单id */
    private Long purchaseOrderId;

    /** 创建人 */
    private String createBy;
}
