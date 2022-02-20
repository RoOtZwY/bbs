package com.rootzwy.bbs.common.dto;

import com.rootzwy.bbs.common.constants.StatusCode;
import com.rootzwy.bbs.common.messagecode.ErrorCode;

/**
 * @param <T> 传输数据数据类型
 * @author zwy
 */
public class SingleResponse<T> extends Response {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> SingleResponse<T> of(T data) {
        SingleResponse<T> singleResponse = new SingleResponse<>();
        singleResponse.setCode(StatusCode.SUCCESS);
        singleResponse.setData(data);
        return singleResponse;
    }

    public static SingleResponse<?> buildFailure(String errorCode, String errorMessage) {
        SingleResponse<?> response = new SingleResponse<>();
        response.setCode(errorCode);
        response.setMessage(errorMessage);
        return response;
    }

    public static SingleResponse<?> buildFailure(ErrorCode errorCode) {
        SingleResponse<?> response = new SingleResponse<>();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getDescription());
        return response;
    }

    public static SingleResponse<?> buildSuccess() {
        SingleResponse<?> response = new SingleResponse<>();
        response.setCode(StatusCode.SUCCESS);
        return response;
    }

}
