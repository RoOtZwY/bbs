package com.rootzwy.bbs.common.exception;

import com.rootzwy.bbs.common.constants.StatusCode;
import com.rootzwy.bbs.common.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zwy
 */
@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    /**
     * 项目接口默认异常处理器
     *
     * @param businessException 自定义项目异常对象
     * @return 返回响应
     */
    @ExceptionHandler(BusinessException.class)
    public Response handleBusinessException(BusinessException businessException) {
        log.error("错误状态码: " + businessException.getErrorCode() + "错误详情: ", businessException);
        return Response.buildFailure(businessException.getErrorCode(), businessException.getMessage());
    }

    /**
     * 系统未定义异常处理器
     *
     * @param exception 系统未定义的异常
     * @return 返回响应
     */
    @ExceptionHandler(Exception.class)
    public Response handleOtherException(Exception exception) {
        log.error("未知异常，详情: ", exception);
        return Response.buildFailure(StatusCode.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

}
