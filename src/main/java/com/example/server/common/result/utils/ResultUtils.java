package com.example.server.common.result.utils;

import com.example.server.common.result.enums.ResultEnum;
import com.example.server.common.result.model.Result;
import com.example.server.utils.DateUtils;

public class ResultUtils {

    /**
     * 成功时生成result的方法,有返回数据
     */
    public static <T> Result<T> success(T t){
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(t);
        result.setTime(DateUtils.getNowTime());
        return result;
    }

    /**
     * 成功时生成result的方法,无返回数据
     */
    public static <T> Result<T> success(){
        return success(null);
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
}
