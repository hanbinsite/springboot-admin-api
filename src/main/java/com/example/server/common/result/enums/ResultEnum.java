package com.example.server.common.result.enums;

public enum ResultEnum {

    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(1, "成功"),
    FAIL(0, "请求失败"),
    NOT_LOGIN(400, "用户未登录"),
    TOKEN_EXPIRE(401, "token已失效"),
    NO_ACCESS_RIGHT(402, "无访问权限"),
    NOT_FOUND(404, "地址不存在"),
    BASIC_INFO_ID_IS_EMPTY(600, "基本信息中BasicInfoId为空"),
    BASIC_INFO_ADD_TO_DATABASE_FAILURE(601, "向数据库添加基本信息失败"),
    DETAILS_DATA_BASIC_INFO_ID_IS_EMPTY(602, "测试数据中BasicInfoId为空"),
    DETAILS_DATA_ADD_TO_DATABASE_FAILURE(603, "向数据库添加测试数据失败");

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;

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

    @Override
    public String toString() {
        return "ResultEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
