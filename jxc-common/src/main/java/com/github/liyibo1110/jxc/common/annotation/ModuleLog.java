package com.github.liyibo1110.jxc.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模块日志注解，用于控制器方法，配合ModuleLogAspect实现统一日志记录。
 * @author liyibo
 * @date 2026-07-30 10:39
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModuleLog {

    /** 操作描述 */
    String value() default "";

    /** 模块代码 */
    String module() default "";
}
