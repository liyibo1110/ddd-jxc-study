package com.github.liyibo1110.jxc.common.util;

import org.slf4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 结构化日志工具类，将日志内容组织为JSON格式的键值对输出，方便日志平台解析和检索。
 * @author liyibo
 * @date 2026-07-29 11:02
 */
public class StructuredLog {

    private final Logger log;
    private String message;
    private Throwable exception;
    private final Map<String, Object> fields = new LinkedHashMap<>();
    private final Level level;

    private enum Level { INFO, WARN, ERROR, DEBUG }

    private StructuredLog(Logger log, Level level) {
        this.log = log;
        this.level = level;
    }

    public static StructuredLog info(Logger log) {
        return new StructuredLog(log, Level.INFO);
    }

    public static StructuredLog warn(Logger log) {
        return new StructuredLog(log, Level.WARN);
    }

    public static StructuredLog error(Logger log) {
        return new StructuredLog(log, Level.ERROR);
    }

    public static StructuredLog debug(Logger log) {
        return new StructuredLog(log, Level.DEBUG);
    }

    public StructuredLog message(String message) {
        this.message = message;
        return this;
    }

    public StructuredLog put(String key, Object value) {
        this.fields.put(key, value);
        return this;
    }

    public StructuredLog exception(Throwable e) {
        this.exception = e;
        return this;
    }

    public void log() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"message\":\"").append(message).append("\"");

        for (Map.Entry<String, Object> entry : fields.entrySet())
            sb.append(",\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");

        sb.append("}");

        String logContent = sb.toString();
        switch (level) {
            case INFO -> {
                if (exception != null)
                    log.info(logContent, exception);
                else
                    log.info(logContent);
            }
            case WARN -> {
                if (exception != null)
                    log.warn(logContent, exception);
                else
                    log.warn(logContent);
            }
            case ERROR -> {
                if (exception != null)
                    log.error(logContent, exception);
                else
                    log.error(logContent);
            }
            case DEBUG -> {
                if (exception != null)
                    log.debug(logContent, exception);
                else
                    log.debug(logContent);
            }
        }
    }
}
