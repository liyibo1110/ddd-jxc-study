package com.github.liyibo1110.jxc.infrastructure.gateway;

import com.github.liyibo1110.jxc.common.util.StructuredLog;
import com.github.liyibo1110.jxc.gateway.MaterialGateway;
import com.github.liyibo1110.jxc.gateway.dto.MaterialInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 物料信息网关实现。
 * 生产环境：通过Dubbo调用物料中心RPC接口，做字段映射，屏蔽外部系统数据结构。
 * @author liyibo
 * @date 2026-08-17 10:54
 */
@Slf4j
@Component
public class MaterialGatewayImpl implements MaterialGateway {

    @Override
    public List<MaterialInfo> getByIds(List<Long> materialIds) {
        if (materialIds == null || materialIds.isEmpty()) {
            return Collections.emptyList();
        }
        try {
            // 生产实现：调物料中心Dubbo接口
            // MaterialFacade.getByIds(materialIds) → 转换为领域层MaterialInfo列表
            return buildMockMaterialInfoList(materialIds);
        } catch (Exception e) {
            StructuredLog.error(log)
                    .message("批量查询物料信息失败")
                    .put("materialIds", materialIds.toString())
                    .exception(e)
                    .log();
            return Collections.emptyList();
        }
    }

    @Override
    public List<MaterialInfo> getByCodes(List<String> materialCodes) {
        if (materialCodes == null || materialCodes.isEmpty()) {
            return Collections.emptyList();
        }
        try {
            return Collections.emptyList();
        } catch (Exception e) {
            StructuredLog.error(log)
                    .message("按编码查询物料信息失败")
                    .put("materialCodes", materialCodes.toString())
                    .exception(e)
                    .log();
            return Collections.emptyList();
        }
    }

    private List<MaterialInfo> buildMockMaterialInfoList(List<Long> materialIds) {
        List<MaterialInfo> result = new ArrayList<>();
        for (Long materialId : materialIds) {
            MaterialInfo info = new MaterialInfo();
            info.setMaterialId(materialId);
            info.setMaterialCode("MAT-" + materialId);
            info.setMaterialName("测试物料-" + materialId);
            info.setSupplierCode("SUP-001");
            info.setSupplierName("测试供应商");
            result.add(info);
        }
        return result;
    }
}
