package com.github.liyibo1110.jxc.ddd;

import com.github.liyibo1110.jxc.common.result.Result;

/**
 * 领域事件发布接口。
 * @author liyibo
 * @date 2026-08-03 13:38
 */
public interface IDomainEventPublisher<T> {

    Result send(DomainEvent<T> event);
}
