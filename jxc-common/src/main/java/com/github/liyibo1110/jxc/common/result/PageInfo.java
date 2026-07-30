package com.github.liyibo1110.jxc.common.result;

import lombok.Data;

/**
 * 通用分页信息。
 * @author liyibo
 * @date 2026-07-29 11:12
 */
@Data
public class PageInfo {

    /** 当前页码，从1开始 */
    private int pageNum = 1;

    /** 每页条数 */
    private int pageSize = 20;

    /** 总条数 */
    private long total;

    public PageInfo() {}

    public PageInfo(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
