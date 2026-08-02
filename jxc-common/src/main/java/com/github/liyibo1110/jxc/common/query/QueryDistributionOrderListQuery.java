package com.github.liyibo1110.jxc.common.query;

import lombok.Data;

import java.util.List;

/**
 * 查询配货单列表请求
 * @author liyibo
 * @date 2026-07-31 14:01
 */
@Data
public class QueryDistributionOrderListQuery {

    /** 门店id */
    private String shopId;

    /** 供应商编码 */
    private String supplierCode;

    /** 状态 */
    private String status;

    /** 配送类型 */
    private String deliveryMethod;

    /** 门店id列表（权限过滤用） */
    private List<String> shopIds;

    /** 当前页码，从1开始 */
    private int pageNum = 1;

    /** 每页条数 */
    private int pageSize = 20;
}
