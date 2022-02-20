package com.rootzwy.bbs.common.dto;

import com.rootzwy.bbs.common.constants.StatusCode;
import com.rootzwy.bbs.common.messagecode.ErrorCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @param <T> 传输数据数据类型
 * @author zwy
 */
public class MultiResponse<T> extends Response {

    private int total;

    private Collection<T> data;

    public int getTotal() {
        return total;
    }


    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return null == data ? new ArrayList<>() : new ArrayList<>(data);
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public static <T> MultiResponse<T> of(Collection<T> data, int total) {
        MultiResponse<T> multiResponse = new MultiResponse<>();
        multiResponse.setCode(StatusCode.SUCCESS);
        multiResponse.setData(data);
        multiResponse.setTotal(total);
        return multiResponse;
    }

    public static <T> MultiResponse<T> ofWithCollectionSize(Collection<T> data) {
        return of(data, data.size());
    }

    public static MultiResponse<?> buildFailure(String errorCode, String errorMessage) {
        MultiResponse<?> response = new MultiResponse<>();
        response.setCode(errorCode);
        response.setMessage(errorMessage);
        return response;
    }

    public static MultiResponse<?> buildFailure(ErrorCode errorCode) {
        MultiResponse<?> response = new MultiResponse<>();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getDescription());
        return response;
    }

    public static MultiResponse<?> buildSuccess(){
        MultiResponse<?> response = new MultiResponse<>();
        response.setCode(StatusCode.SUCCESS);
        return response;
    }

}
