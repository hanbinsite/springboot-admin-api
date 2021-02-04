package com.example.server.entity;

import lombok.Data;

import java.util.List;

/**
 * @author hanbin
 * 管理员分组权限返回数据格式
 */
@Data
public class GroupRuleEntity {

    private Integer id;

    private Integer groupId;

    private Integer parentId;

    private String name;

    private String path;

    private String component;

    private Integer hidden;

    private String redirect;

    private String api;

    private String title;

    private String icon;

    private Integer affix;

    private Integer noCache;

    private String activeMenu;

    private Integer sort;

    private Integer status;

    private List<GroupRuleEntity> children;

}
