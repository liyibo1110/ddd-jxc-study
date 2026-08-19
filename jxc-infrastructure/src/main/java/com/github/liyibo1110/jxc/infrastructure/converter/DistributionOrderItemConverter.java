package com.github.liyibo1110.jxc.infrastructure.converter;

import com.github.liyibo1110.jxc.core.domain.aggregate.distribution.entity.DistributionOrderItem;
import com.github.liyibo1110.jxc.infrastructure.persistence.po.DistributionOrderItemPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 配货单明细 Entity <-> PO 转换器。
 * @author liyibo
 * @date 2026-08-18 10:57
 */
@Mapper
public interface DistributionOrderItemConverter {

    DistributionOrderItemConverter INSTANCE = Mappers.getMapper(DistributionOrderItemConverter.class);

    DistributionOrderItemPO entityToPo(DistributionOrderItem entity);

    DistributionOrderItem poToEntity(DistributionOrderItemPO po);

    List<DistributionOrderItem> poToEntityList(List<DistributionOrderItemPO> poList);

    List<DistributionOrderItemPO> entityToPoList(List<DistributionOrderItem> entityList);
}
