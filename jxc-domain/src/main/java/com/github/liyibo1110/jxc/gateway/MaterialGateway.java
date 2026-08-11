package com.github.liyibo1110.jxc.gateway;

import com.github.liyibo1110.jxc.gateway.dto.MaterialInfo;

import java.util.List;

/**
 * 物料信息网关（防腐层接口）。
 * 隔离物料中心的具体数据结构，领域层只依赖此接口。
 * @author liyibo
 * @date 2026-08-10 12:23
 */
public interface MaterialGateway {

    /**
     * 按物料id列表批量获取物料信息（含供应商信息）
     */
    List<MaterialInfo> getByIds(List<Long> materialIds);

    /**
     * 按物料编码列表批量获取物料信息
     */
    List<MaterialInfo> getByCodes(List<String> materialCodes);
}
