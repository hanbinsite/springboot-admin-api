package com.example.server.service;

import com.example.server.common.page.model.PageConfig;
import com.example.server.common.page.model.PageResult;
import com.example.server.verify.group.GroupVo;

public interface GroupService {

    /**
     * 获得用户组分页
     * @param pageConfig 分页参数
     * @param status 状态
     * @param name 分组名称
     * @return PageResult
     */
    PageResult getGroupPage(PageConfig pageConfig, Integer status, String name);

    /**
     * 新增分组
     * @param groupVo 参数
     * @return bool
     */
    Boolean addGroup(GroupVo groupVo);

    /**
     * 删除分组
     * @param id 分组id
     * @return bool
     */
    Boolean delGroup(Integer id);

    /**
     * 编辑分组
     * @param id 分组id
     * @param groupVo 提交内容
     * @return Boolean
     */
    Boolean editGroup(Integer id, GroupVo groupVo);
}
