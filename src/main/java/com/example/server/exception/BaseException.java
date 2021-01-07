package com.example.server.exception;

import com.example.server.common.result.model.Result;

public class BaseException extends RuntimeException {

    public int code;  //异常状态码
    public String msg;  //异常信息
    /**
     * @param code 状态
     * @param msg 信息
     */
    public BaseException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public BaseException() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
