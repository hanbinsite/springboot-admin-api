package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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
}
