package com.github.liyibo1110.jxc.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.liyibo1110.jxc.infrastructure.persistence.po.PurchaseOrderPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订货单主表Mapper
 * @author liyibo
 * @date 2026-08-15 14:16
 */
@Mapper
public interface PurchaseOrderMapper extends BaseMapper<PurchaseOrderPO> {

}
