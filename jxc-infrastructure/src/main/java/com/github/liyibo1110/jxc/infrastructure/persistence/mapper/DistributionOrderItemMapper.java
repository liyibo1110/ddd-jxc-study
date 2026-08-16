package com.github.liyibo1110.jxc.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.liyibo1110.jxc.infrastructure.persistence.po.DistributionOrderItemPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 配货单明细Mapper
 * @author liyibo
 * @date 2026-08-15 14:15
 */
public interface DistributionOrderItemMapper extends BaseMapper<DistributionOrderItemPO> {

    /**
     * 按配货单id列表批量查询明细
     */
    List<DistributionOrderItemPO> selectByOrderIdList(@Param("orderIdList") List<Long> orderIdList);
}
