package com.github.liyibo1110.jxc.infrastructure.gateway;

import com.github.liyibo1110.jxc.common.util.StructuredLog;
import com.github.liyibo1110.jxc.core.domain.aggregate.shop.entity.ShopInfoEntity;
import com.github.liyibo1110.jxc.gateway.ShopInfoGateway;
import com.github.liyibo1110.jxc.gateway.dto.ShopInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 门店信息网关实现。
 * 生产环境：通过Dubbo调用门店中心RPC接口。
 * 实际实现：返回Mock数据，便于本地运行和理解代码结构。
 * @author liyibo
 * @date 2026-08-17 10:18
 */
@Slf4j
@Component
public class ShopInfoGatewayImpl implements ShopInfoGateway {

    @Override
    public ShopInfo getShopInfo(Integer shopId) {
        try {
            // 生产实现：调Dubbo接口，做字段映射，处理异常降级
            // ShopFacade.getById(shopId) → 转换为领域层ShopInfo
            return buildMockShopInfo(shopId);
        } catch (Exception e) {
            StructuredLog.error(log)
                    .message("查询门店信息失败")
                    .put("shopId", shopId)
                    .exception(e)
                    .log();
            return null;
        }
    }

    @Override
    public List<ShopInfoEntity> getShopsByUserId(String userId) {
        try {
            // 生产实现：调权限中心接口获取用户有权限的门店列表
            return Collections.emptyList();
        } catch (Exception e) {
            StructuredLog.error(log)
                    .message("查询用户门店列表失败")
                    .put("userId", userId)
                    .exception(e)
                    .log();
            return Collections.emptyList();
        }
    }

    private ShopInfo buildMockShopInfo(Integer shopId) {
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShopId(shopId);
        shopInfo.setShopName("测试门店-" + shopId);
        shopInfo.setOperable(true);
        shopInfo.setAutoDistributionEnabled(true);
        return shopInfo;
    }
}
