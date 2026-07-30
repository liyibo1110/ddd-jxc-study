package com.github.liyibo1110.jxc.common.result;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * 通用分页结果。
 * @author liyibo
 * @date 2026-07-29 11:13
 */
@Data
public class Page<T> {

    /** 当前页数据 */
    private List<T> list;

    /** 总条数 */
    private long total;

    /** 当前页码 */
    private int pageNum;

    /** 每页条数 */
    private int pageSize;

    public PageInfo getPageInfo() {
        PageInfo info = new PageInfo();
        info.setPageNum(pageNum);
        info.setPageSize(pageSize);
        info.setTotal(total);
        return info;
    }

    public static <T> Page<T> empty(int pageNum, int pageSize) {
        Page<T> page = new Page<>();
        page.setList(Collections.emptyList());
        page.setTotal(0L);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        return page;
    }

    public static <T> Page<T> of(List<T> list, long total, int pageNum, int pageSize) {
        Page<T> page = new Page<>();
        page.setList(list);
        page.setTotal(total);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        return page;
    }

    public static <T> Page<T> of(List<T> list, long total, PageInfo pageInfo) {
        return of(list, total, pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
