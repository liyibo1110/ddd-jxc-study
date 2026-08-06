package com.github.liyibo1110.jxc.core.domain.aggregate.deliveryrule.repository;

import com.github.liyibo1110.jxc.core.domain.aggregate.deliveryrule.entity.ShopDeliveryRule;

import java.util.List;

/**
 * 配送规则仓储接口。
 * @author liyibo
 * @date 2026-08-05 11:12
 */
public interface ShopDeliveryRuleRepository {

    /**
     * 根据门店id和物料id列表查询配送规则。
     */
    List<ShopDeliveryRule> getByShopIdAndMaterialIds(Integer shopId, List<Long> materialIds);
}
