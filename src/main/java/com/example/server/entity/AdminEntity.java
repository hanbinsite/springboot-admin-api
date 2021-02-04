package com.example.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @author hanbin
 */

@Data
public class AdminEntity {

    private Integer id;

    private Integer isSuper;

    private Integer groupId;

    private String username;

    private String nickname;

    private String mobile;

    private String mail;

    private Integer status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String updatedAt;
}
