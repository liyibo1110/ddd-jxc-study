package com.github.liyibo1110.jxc.ddd;

/**
 * 聚合根。
 * 聚合是一组相关对象的集合，每个聚合有一个根和边界。
 * 聚合根是这个聚合的根节点，它也是一个实体。
 * 聚合内部的对象可以相互引用，对外通过聚合根进行交互。
 * @author liyibo
 * @date 2026-08-03 12:03
 */
public interface AggregateRoot<ID> extends Entity<ID> {

}
