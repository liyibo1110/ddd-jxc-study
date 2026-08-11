package com.github.liyibo1110.jxc.gateway;

import com.github.liyibo1110.jxc.core.domain.aggregate.shop.entity.ShopInfoEntity;
import com.github.liyibo1110.jxc.gateway.dto.ShopInfo;

import java.util.List;

/**
 * 门店信息网关（防腐层接口）。
 * 隔离门店系统的具体数据结构，领域层只依赖此接口。
 * @author liyibo
 * @date 2026-08-10 12:23
 */
public interface ShopInfoGateway {

    /**
     * 获取单个门店信息
     */
    ShopInfo getShopInfo(Integer shopId);

    /**
     * 根据用户id获取有权限的门店列表
     */
    List<ShopInfoEntity> getShopsByUserId(String userId);
}
