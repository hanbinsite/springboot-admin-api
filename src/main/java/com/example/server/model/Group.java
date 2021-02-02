package com.example.server.model;

import lombok.Data;

/**
 * @author hanbin
 */
@Data
public class Group {

    private Integer id;

    private String name;

    private String description;

    private Integer isSuper;

    private Integer status;

    private String createdAt;

    private String updatedAt;
}
