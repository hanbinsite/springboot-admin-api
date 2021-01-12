package com.example.server.service.impl;

import com.example.server.common.page.model.PageConfig;
import com.example.server.common.page.model.PageResult;
import com.example.server.common.page.utils.PageUtils;
import com.example.server.common.result.enums.ResultEnum;
import com.example.server.exception.ApiException;
import com.example.server.mapper.GroupMapper;
import com.example.server.mapper.GroupRuleMapper;
import com.example.server.model.Group;
import com.example.server.service.GroupService;
import com.example.server.utils.DateUtils;
import com.example.server.verify.group.GroupVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 韩彬
 */
@Service
@Slf4j
public class GroupServiceImpl implements GroupService {

    private final GroupMapper groupMapper;

    private final GroupRuleMapper groupRuleMapper;

    public GroupServiceImpl(GroupMapper groupMapper, GroupRuleMapper groupRuleMapper) {
        this.groupMapper = groupMapper;
        this.groupRuleMapper = groupRuleMapper;
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
     * 新增分组
     *
     * @param groupVo 参数
     * @return bool
     */
    @Override
    @Transactional
    public Boolean addGroup(GroupVo groupVo) {
        Group group = new Group();
        group.setName(groupVo.getName());
        group.setDescription(groupVo.getDescription());
        group.setStatus(1);
        group.setIsSuper(0);
        group.setCreatedAt(DateUtils.getNowTime());
        group.setUpdatedAt(DateUtils.getNowTime());
        Integer id = groupMapper.addGroup(group);
        if (id == null) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "分组新增失败,请稍后重试");
        }
        Integer has = groupRuleMapper.addGroupRules(group.getId(), groupVo.getRules());
        return true;
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
