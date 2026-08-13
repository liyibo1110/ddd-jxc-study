package com.github.liyibo1110.jxc.core.domain.aggregate.shop.service.impl;

import com.github.liyibo1110.jxc.core.domain.aggregate.shop.entity.ShopInfoEntity;
import com.github.liyibo1110.jxc.core.domain.aggregate.shop.service.ShopInfoDomainService;
import com.github.liyibo1110.jxc.gateway.ShopInfoGateway;
import com.github.liyibo1110.jxc.gateway.dto.ShopInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 门店信息领域服务实现。
 * @author liyibo
 * @date 2026-08-07 10:32
 */
@Service
@RequiredArgsConstructor
public class ShopInfoDomainServiceImpl implements ShopInfoDomainService {

    private final ShopInfoGateway shopInfoGateway;

    @Override
    public ShopInfo getShopInfo(Integer shopId) {
        return shopInfoGateway.getShopInfo(shopId);
    }

    @Override
    public List<ShopInfoEntity> getShopsByUserId(String userId) {
        return shopInfoGateway.getShopsByUserId(userId);
    }
}
