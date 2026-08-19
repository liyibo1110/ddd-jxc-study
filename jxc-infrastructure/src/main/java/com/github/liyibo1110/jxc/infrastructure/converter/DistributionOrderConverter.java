package com.github.liyibo1110.jxc.infrastructure.converter;

import com.github.liyibo1110.jxc.core.domain.aggregate.distribution.entity.DistributionOrderAggregateRoot;
import com.github.liyibo1110.jxc.infrastructure.persistence.po.DistributionOrderPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 配货单主表 Entity <-> PO 转换器。
 * @author liyibo
 * @date 2026-08-18 10:57
 */
@Mapper
public interface DistributionOrderConverter {

    DistributionOrderConverter INSTANCE = Mappers.getMapper(DistributionOrderConverter.class);

    DistributionOrderPO entityToPo(DistributionOrderAggregateRoot entity);

    DistributionOrderAggregateRoot poToEntity(DistributionOrderPO po);

    List<DistributionOrderAggregateRoot> poToEntityList(List<DistributionOrderPO> poList);

    List<DistributionOrderPO> entityToPoList(List<DistributionOrderAggregateRoot> entityList);
}
