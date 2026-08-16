package com.github.liyibo1110.jxc.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.liyibo1110.jxc.infrastructure.persistence.po.PurchaseOrderItemPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订货单明细Mapper。
 * @author liyibo
 * @date 2026-08-15 14:16
 */
@Mapper
public interface PurchaseOrderItemMapper extends BaseMapper<PurchaseOrderItemPO> {

    /**
     * 按订货单id列表批量查询明细
     */
    List<PurchaseOrderItemPO> selectByOrderIdList(@Param("orderIdList") List<Long> orderIdList);
}
