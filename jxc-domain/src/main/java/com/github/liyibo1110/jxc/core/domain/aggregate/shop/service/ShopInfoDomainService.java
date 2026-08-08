package com.github.liyibo1110.jxc.core.domain.aggregate.shop.service;

import java.util.List;

/**
 * 门店信息领域服务。
 * @author liyibo
 * @date 2026-08-07 10:30
 */
public interface ShopInfoDomainService {

    /**
     * 获取门店信息
     */
    ShopInfo getShopInfo(Integer shopId);

    /**
     * 根据用户id获取有权限的门店列表
     */
    List<ShopInfoEntity> getShopsByUserId(String userId);
}
