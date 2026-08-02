package com.github.liyibo1110.jxc.common.query;

import lombok.Data;

import java.util.List;

/**
 * 查询订货单列表请求
 * @author liyibo
 * @date 2026-07-31 14:00
 */
@Data
public class QueryPurchaseOrderListQuery {

    /** 门店id */
    private String shopId;

    /** 状态 */
    private String status;

    /** 门店id列表（权限过滤用） */
    private List<String> shopIds;

    /** 当前页码，从1开始 */
    private int pageNum = 1;

    /** 每页条数 */
    private int pageSize = 20;
}
