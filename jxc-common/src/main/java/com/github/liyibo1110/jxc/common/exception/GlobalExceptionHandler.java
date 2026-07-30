package com.github.liyibo1110.jxc.common.exception;

import com.github.liyibo1110.jxc.common.result.Result;
import com.github.liyibo1110.jxc.common.util.StructuredLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author liyibo
 * @date 2026-07-29 11:11
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public Result<Void> handleBizException(BizException e) {
        StructuredLog.warn(log)
                .message("biz exception handled")
                .put("code", e.getCode())
                .put("msg", e.getMessage())
                .log();
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        StructuredLog.error(log)
                .message("system exception handled")
                .exception(e)
                .log();
        return Result.fail("SYSTEM_ERROR", "系统异常，请稍后重试");
    }
}
