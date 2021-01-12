package com.example.server.common.result.utils;

import com.example.server.common.result.enums.ResultEnum;
import com.example.server.common.result.model.Result;
import com.example.server.utils.DateUtils;

public class ResultUtils {

    /**
     * 成功时生成result的方法,有返回数据
     */
    public static <T> Result<T> success(Integer code, String msg, T t){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(t);
        result.setTime(DateUtils.getNowTime());
        return result;
    }

    /**
     * 成功时生成result的方法,无返回数据
     */
    public static <T> Result<T> success(){
        return success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), null);
    }

    /**
     * 成功时生成result的方法,无返回数据
     */
    public static <T> Result<T> successOnlyMsg(String msg){
        return success(ResultEnum.SUCCESS.getCode(), msg, null);
    }

    /**
     * 成功时生成result的方法,无返回数据
     */
    public static <T> Result<T> success(T t){
        return success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), t);
    }

    /**
     * 失败时生成result的方法
     */
    public static <T> Result<T> error(int status, String msg){
        Result<T> result = new Result<>();
        result.setCode(status);
        result.setMsg(msg);
        result.setTime(DateUtils.getNowTime());
        return result;
    }

    /**
     * 失败时生成result的方法
     */
    public static <T> Result<T> error(String msg){
        return error(ResultEnum.FAIL.getCode(), msg);
    }
}
