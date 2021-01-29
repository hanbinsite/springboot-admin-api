package com.example.server.model;

import com.example.server.utils.DateUtils;
import lombok.Data;

/**
 * @author hanbin
 */
@Data
public class Rule {

    private Integer id;

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

    private String createdAt;

    private String updatedAt;

    public Rule() {
    }

    public Rule(Integer parentId, String name, String path, String component, Integer hidden, String redirect, String api, String title, String icon, Integer affix, Integer noCache, String activeMenu, Integer sort, Integer status) {
        this.parentId = parentId;
        this.name = name;
        this.path = path;
        this.component = component;
        this.hidden = hidden;
        this.redirect = redirect;
        this.api = api;
        this.title = title;
        this.icon = icon;
        this.affix = affix;
        this.noCache = noCache;
        this.activeMenu = activeMenu;
        this.sort = sort;
        this.status = status;
        this.createdAt = DateUtils.getNowTime();
        this.updatedAt = DateUtils.getNowTime();
    }



    public Rule(Integer id, Integer parentId, String name, String path, String component, Integer hidden, String redirect, String api, String title, String icon, Integer affix, Integer noCache, String activeMenu, Integer sort, Integer status) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.path = path;
        this.component = component;
        this.hidden = hidden;
        this.redirect = redirect;
        this.api = api;
        this.title = title;
        this.icon = icon;
        this.affix = affix;
        this.noCache = noCache;
        this.activeMenu = activeMenu;
        this.sort = sort;
        this.status = status;
        this.createdAt = DateUtils.getNowTime();
        this.updatedAt = DateUtils.getNowTime();
    }
}
