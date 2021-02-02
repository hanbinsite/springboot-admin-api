package com.example.server.verify.admin;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author hanbin
 */
@Data
public class AdminLoginVo {

    @NotNull(message = "登录账号不能为空")
    @NotEmpty(message = "登录账号不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
