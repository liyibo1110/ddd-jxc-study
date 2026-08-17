package com.github.liyibo1110.jxc.infrastructure.gateway;

import com.github.liyibo1110.jxc.gateway.AuthGateway;
import org.springframework.stereotype.Component;

/**
 * 认证网关实现。
 * 生产环境：调Auth中心RPC接口或本地解析JWT token。
 * @author liyibo
 * @date 2026-08-17 10:47
 */
@Component
public class AuthGatewayImpl implements AuthGateway {

    @Override
    public String validateToken(String token) {
        // 生产实现：解析JWT或调Auth中心接口，返回userId
        return "mock-user-id";
    }

    @Override
    public boolean hasShopPermission(String userId, Integer shopId) {
        // 生产实现：调权限中心接口校验用户是否有门店操作权限
        return true;
    }
}
