package com.example.server.verify.rule;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author hanbin
 */
@Data
public class RuleVo {

    private Integer parentId;

    @NotNull(message = "路由名称不能为空")
    @NotEmpty(message = "路由名称不能为空")
    private String name;

    private String path;

    @NotNull(message = "组件地址不能为空")
    @NotEmpty(message = "组件地址不能为空")
    private String component;

    @NotNull(message = "请选择是否隐藏")
    private Integer hidden;

    private String redirect;

    private String api;

    @NotNull(message = "路由标题不能为空")
    @NotEmpty(message = "路由标题不能为空")
    private String title;

    private String icon;

    @NotNull(message = "请选择是否固定")
    private Integer affix;

    @NotNull(message = "请选择是否缓存")
    private Integer noCache;

    private String activeMenu;

    @NotNull(message = "排序不能为空")
    private Integer sort;

    @NotNull(message = "状态不能为空")
    private Integer status;

}
