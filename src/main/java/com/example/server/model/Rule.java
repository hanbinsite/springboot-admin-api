package com.example.server.model;

import lombok.Data;

@Data
public class Rule {

    private Integer id;

    private Integer parentId;

    private String name;

    private String path;

    private String component;

    private Integer hidden;

    private String redirect;

    private String title;

    private String icon;

    private Integer affix;

    private Integer sort;

    private String createdAt;

    private String updatedAt;
}
