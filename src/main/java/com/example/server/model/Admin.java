package com.example.server.model;

import com.example.server.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @author hanbin
 */
@Data
public class Admin {

    private Integer id;

    private Integer isSuper;

    private Integer groupId;

    private String username;

    private String nickname;

    private String password;

    private String salt;

    private String mobile;

    private String mail;

    private Integer status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String updatedAt;

    public Admin(Integer isSuper, Integer groupId, String username, String nickname, String password, String salt, String mobile, String mail, Integer status) {
        this.isSuper = isSuper;
        this.groupId = groupId;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.salt = salt;
        this.mobile = mobile;
        this.mail = mail;
        this.status = status;
        this.createdAt = DateUtils.getNowTime();
        this.updatedAt = DateUtils.getNowTime();
    }
    public Admin(Integer id, Integer isSuper, Integer groupId, String username, String nickname, String mobile, String mail, Integer status) {
        this.id = id;
        this.isSuper = isSuper;
        this.groupId = groupId;
        this.username = username;
        this.nickname = nickname;
        this.mobile = mobile;
        this.mail = mail;
        this.status = status;
        this.updatedAt = DateUtils.getNowTime();
    }
}
