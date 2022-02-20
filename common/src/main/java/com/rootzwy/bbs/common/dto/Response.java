package com.rootzwy.bbs.common.dto;

import com.rootzwy.bbs.common.constants.StatusCode;
import com.rootzwy.bbs.common.messagecode.ErrorCode;

/**
 * @author zwy
 */
public class Response extends DTO {

    private String code;
    private String message;

    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public static Response buildFailure(String errorCode, String errorMessage) {
        Response response = new Response();
        response.setCode(errorCode);
        response.setMessage(errorMessage);
        return response;
    }

    public static Response buildFailure(ErrorCode errorCode) {
        Response response = new Response();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getDescription());
        return response;
    }

    public static Response buildSuccess(){
        Response response = new Response();
        response.setCode(StatusCode.SUCCESS);
        return response;
    }

}
