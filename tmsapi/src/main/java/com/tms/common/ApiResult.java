package com.tms.common;

import java.io.Serializable;

public class ApiResult<T> implements Serializable {
    private int code;

    private String message;

    private T data;

    private ApiResult(int code){
        this.code = code;
    }

    public ApiResult(int code,String message) {
        this.code = code;
        this.message = message;
    }

    private ApiResult(int code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccuess() {
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> ApiResult<T> createSuccess(T t) {
        if( t == null){
            return new ApiResult<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage());
        }
        return new ApiResult<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage(),t);
    }

    public static <T> ApiResult<T> createFail() {
        return new ApiResult<T>(ResponseCode.FAIL.getCode(),ResponseCode.FAIL.getMessage());
    }

    public static <T> ApiResult<T> createApiResponse(int code,String msg,T t) {
        return new ApiResult<T>(code,msg,t);
    }
}
