package com.github.liyibo1110.jxc.common.util;

import java.util.UUID;

/**
 * ID生成工具。
 * @author liyibo
 * @date 2026-07-29 11:07
 */
public final class IdGenerator {

    private IdGenerator() {}

    /**
     * 生成UUID（去掉横线）
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
