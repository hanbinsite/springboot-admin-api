package com.example.server.model;

import lombok.Data;

/**
 * @author hanbin
 */
@Data
public class User {

    private Integer id;

    private String username;

    private String nickname;

    private String password;

    private String salt;

    private String mobile;

    private Integer status;

    private String createdAt;

    private String updatedAt;
}
