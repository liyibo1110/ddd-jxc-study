package com.github.liyibo1110.jxc.common.exception;

/**
 * 业务异常。
 * @author liyibo
 * @date 2026-07-29 11:10
 */
public class BizException extends RuntimeException {

    private final String code;

    public BizException(String message) {
        super(message);
        this.code = "BIZ_ERROR";
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
