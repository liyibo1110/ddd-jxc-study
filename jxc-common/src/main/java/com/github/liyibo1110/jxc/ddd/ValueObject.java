package com.github.liyibo1110.jxc.ddd;

/**
 * 值对象没有唯一标识，通过属性值判断相等性。
 * 值对象是不可变的，创建后不能被修改。
 * @author liyibo
 * @date 2026-08-03 13:36
 */
public interface ValueObject<V> {

    /**
     * 判断与另一个值对象是否值相等
     */
    boolean sameValueAs(V other);
}
