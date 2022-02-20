package com.rootzwy.bbs.common.exception;

/**
 * @author zwy
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    public BaseException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errCode) {
        this.errorCode = errCode;
    }

}
