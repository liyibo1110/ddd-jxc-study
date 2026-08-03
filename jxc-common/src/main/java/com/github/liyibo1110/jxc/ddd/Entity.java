package com.github.liyibo1110.jxc.ddd;

/**
 * 每个实体都拥有一个唯一的标识符。
 * 可以对一个实体进行多次修改，修改后的数据和原来可能会不同，但它们依然是同一个实体，因为唯一标识没变。
 * @author liyibo
 * @date 2026-08-03 12:02
 */
public interface Entity<ID> {

    /**
     * 获取实体唯一标识
     */
    ID getUniqueId();
}
