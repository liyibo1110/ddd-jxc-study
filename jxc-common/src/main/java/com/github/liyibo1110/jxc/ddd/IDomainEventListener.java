package com.github.liyibo1110.jxc.ddd;

/**
 * 领域事件监听器接口。
 * @author liyibo
 * @date 2026-08-03 13:39
 */
public interface IDomainEventListener<T> {

    void onListened(DomainEvent<T> event);
}
