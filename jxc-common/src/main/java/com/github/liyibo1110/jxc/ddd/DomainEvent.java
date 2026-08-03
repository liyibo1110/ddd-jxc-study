package com.github.liyibo1110.jxc.ddd;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 领域事件
 * @author liyibo
 * @date 2026-08-03 13:37
 */
@Getter
@Setter
public class DomainEvent<T> {

    /** 事件唯一ID，UUID */
    private String eventId;

    /** 事件创建时间 */
    private LocalDateTime createTime;

    /** 事件体，泛型，承载具体的业务数据 */
    private T data;

    /** 主题，相当于MQ的Topic */
    private String subject;

    /** 标签，相当于MQ的Tag */
    private String tag;

    /** 事件类型（同步/异步/顺序消息） */
    private String type;

    /** 扩展字段 */
    private Map<String, Object> extensions;
}
