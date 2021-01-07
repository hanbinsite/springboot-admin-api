package com.example.server.exception;

import com.example.server.common.result.enums.ResultEnum;

public class NotLoginException extends BaseException{


    public NotLoginException() {
        this.code = ResultEnum.NOT_LOGIN.getCode();
        this.msg = ResultEnum.NOT_LOGIN.getMsg();
    }
}
