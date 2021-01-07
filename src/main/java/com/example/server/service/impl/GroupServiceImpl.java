package com.example.server.service.impl;

import com.example.server.common.page.model.PageConfig;
import com.example.server.common.page.model.PageResult;
import com.example.server.common.page.utils.PageUtils;
import com.example.server.mapper.GroupMapper;
import com.example.server.model.Group;
import com.example.server.service.GroupService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupMapper groupMapper;

    public GroupServiceImpl(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    /**
     * 获得用户组分页
     *
     * @param pageConfig 分页参数
     * @return PageResult
     */
    @Override
    public PageResult getGroupPage(PageConfig pageConfig, Integer status, String name) {
        return PageUtils.getPageResult(pageConfig, getPageInfo(pageConfig, status, name));
    }

    /**
     * 调用分页插件完成分页
     * @param pageConfig PageConfig
     * @return PageInfo
     */
    private PageInfo<Group> getPageInfo(PageConfig pageConfig, Integer status, String name) {
        int pageNum = pageConfig.getPageNum();
        int pageSize = pageConfig.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Group> groups = groupMapper.getGroupPage(status, name);
        return new PageInfo<Group>(groups);
    }
}
