package com.github.liyibo1110.jxc.gateway;

/**
 * 认证/鉴权网关（防腐层接口）。
 * @author liyibo
 * @date 2026-08-10 12:24
 */
public interface AuthGateway {

    /**
     * 校验token并返回用户id
     */
    String validateToken(String token);

    /**
     * 判断用户是否有指定门店的操作权限
     */
    boolean hasShopPermission(String userId, Integer shopId);
}
