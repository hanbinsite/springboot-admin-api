package com.example.server.service;

import com.example.server.common.page.model.PageConfig;
import com.example.server.common.page.model.PageResult;

public interface GroupService {

    /**
     * 获得用户组分页
     * @param pageConfig 分页参数
     * @param status 状态
     * @param name 分组名称
     * @return PageResult
     */
    PageResult getGroupPage(PageConfig pageConfig, Integer status, String name);
}
