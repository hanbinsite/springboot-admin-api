package com.example.server.service.impl;

import com.example.server.entity.GroupRuleEntity;
import com.example.server.mapper.GroupRuleMapper;
import com.example.server.model.Group;
import com.example.server.model.Rule;
import com.example.server.service.GroupRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupRuleServiceImpl implements GroupRuleService {

    private final GroupRuleMapper groupRuleMapper;

    public GroupRuleServiceImpl(GroupRuleMapper groupRuleMapper) {
        this.groupRuleMapper = groupRuleMapper;
    }

    /**
     * 根据管理员所在分组id与父级获得下级所有菜单
     * @param parentId 父级id
     * @param groupId  分组id
     * @return List<Rule>
     */
    @Override
    public List<GroupRuleEntity> getRuleByParentIdAndGroupId(Integer parentId, Integer groupId) {
        List<GroupRuleEntity> list = groupRuleMapper.getRuleByParentIdAndGroupId(parentId, groupId);
        list.forEach(item-> {
            List<GroupRuleEntity> child = getRuleByParentIdAndGroupId(item.getId(), groupId);
            item.setGroupId(groupId);
            item.setChildren(child);
        });
        return list;
    }

    /**
     * 检测用户对某个接口是否有权限
     *
     * @param api     当前访问地址
     * @param groupId 管理员分组
     * @return GroupRuleEntity
     */
    @Override
    public Boolean getRuleByApiAndGroupId(String api, Integer groupId) {
        GroupRuleEntity groupRuleEntity = groupRuleMapper.getRuleByApiAndGroupId(api, groupId);
        return groupRuleEntity != null;
    }
}
