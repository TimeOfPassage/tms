package com.tms.common;

public enum ResponseCode {

    SUCCESS(200,"操作成功"),
    FAIL(-1,"操作失败");


    private int code;
    private String message;
    ResponseCode(int code, String message){
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
