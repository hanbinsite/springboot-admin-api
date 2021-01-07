package com.example.server.exception;

public class ApiException extends BaseException{
    public ApiException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ApiException() {
    }
}
