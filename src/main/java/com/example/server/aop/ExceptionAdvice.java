package com.example.server.aop;

import com.example.server.common.result.utils.ResultUtils;
import com.example.server.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Object exceptionHandler(BaseException exception) {
//        Map<String,Object> map  = new HashMap<String,Object>();
//        map.put("code",exception.getCode());
//        map.put("msg",exception.getMessage());
        //打印日志信息
        return ResultUtils.error(exception.getCode(), exception.getMsg());
    }
}
