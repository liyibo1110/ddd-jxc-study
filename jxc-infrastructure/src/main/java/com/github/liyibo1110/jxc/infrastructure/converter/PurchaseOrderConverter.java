package com.github.liyibo1110.jxc.infrastructure.converter;

import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.entity.PurchaseOrderAggregateRoot;
import com.github.liyibo1110.jxc.core.domain.aggregate.purchase.entity.PurchaseOrderItem;
import com.github.liyibo1110.jxc.infrastructure.persistence.po.PurchaseOrderItemPO;
import com.github.liyibo1110.jxc.infrastructure.persistence.po.PurchaseOrderPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 订货单 Entity <-> PO 转换器。
 * @author liyibo
 * @date 2026-08-18 10:58
 */
@Mapper
public interface PurchaseOrderConverter {

    PurchaseOrderConverter INSTANCE = Mappers.getMapper(PurchaseOrderConverter.class);

    PurchaseOrderPO entityToPo(PurchaseOrderAggregateRoot entity);

    PurchaseOrderAggregateRoot poToEntity(PurchaseOrderPO po);

    List<PurchaseOrderAggregateRoot> poToEntityList(List<PurchaseOrderPO> poList);

    PurchaseOrderItemPO itemEntityToPo(PurchaseOrderItem entity);

    PurchaseOrderItem itemPoToEntity(PurchaseOrderItemPO po);

    List<PurchaseOrderItem> itemPoToEntityList(List<PurchaseOrderItemPO> poList);
}
