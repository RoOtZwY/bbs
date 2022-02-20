package com.rootzwy.bbs.common.exception;


import com.rootzwy.bbs.common.messagecode.BusinessErrorCode;
import com.rootzwy.bbs.common.messagecode.ErrorCode;

/**
 * 默认项目异常，可继承此类创建新的异常类型。
 * @author zwy
 */
public class BusinessException extends BaseException {

    public BusinessException() {
        super(BusinessErrorCode.DEFAULT.getCode(), BusinessErrorCode.DEFAULT.getDescription());
    }

    public BusinessException(String errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getDescription());
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getCode(), errorCode.getDescription(), cause);
    }
}
