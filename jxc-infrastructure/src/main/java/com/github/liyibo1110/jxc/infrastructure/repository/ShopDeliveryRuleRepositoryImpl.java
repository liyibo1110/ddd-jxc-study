package com.github.liyibo1110.jxc.infrastructure.repository;

import com.github.liyibo1110.jxc.core.domain.aggregate.deliveryrule.entity.ShopDeliveryRule;
import com.github.liyibo1110.jxc.core.domain.aggregate.deliveryrule.repository.ShopDeliveryRuleRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 配送规则仓储实现。
 * 生产环境：查shop_delivery_rule表，返回门店物料的配送规则。
 * @author liyibo
 * @date 2026-08-20 10:31
 */
@Repository
public class ShopDeliveryRuleRepositoryImpl implements ShopDeliveryRuleRepository {

    @Override
    public List<ShopDeliveryRule> getByShopIdAndMaterialIds(Integer shopId, List<Long> materialIds) {
        // 专栏项目：这里直接构造
        List<ShopDeliveryRule> rules = new ArrayList<>();
        for (Long materialId : materialIds) {
            ShopDeliveryRule rule = ShopDeliveryRule.builder()
                    .shopId(shopId)
                    .materialId(materialId)
                    .materialCode("MAT-" + materialId)
                    .deliveryType("direct_delivery")
                    .supplierCode("SUP-001")
                    .supplierName("测试供应商")
                    .build();
            rules.add(rule);
        }
        return rules;
    }
}
