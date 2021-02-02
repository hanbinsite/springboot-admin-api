package com.example.server.verify.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author hanbin
 */

@Data
public class AdminVo {
    private Integer isSuper;

    @NotNull(message = "管理员所属分组不能为空")
    @NotEmpty(message = "管理员所属分组不能为空")
    private Integer groupId;

    @NotNull(message = "管理员账号不能为空")
    @NotEmpty(message = "管理员账号不能为空")
    private String username;

    @NotNull(message = "管理员昵称不能为空")
    @NotEmpty(message = "管理员昵称不能为空")
    private String nickname;

    @NotNull(message = "管理员密码不能为空")
    @NotEmpty(message = "管理员密码不能为空")
    private String password;

    @NotNull(message = "管理员手机号不能为空")
    @NotEmpty(message = "管理员手机号不能为空")
    private String mobile;

    @NotNull(message = "管理员邮箱不能为空")
    @NotEmpty(message = "管理员邮箱不能为空")
    private String mail;

    private Integer status;
}
