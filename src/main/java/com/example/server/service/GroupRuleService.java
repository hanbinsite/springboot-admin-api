package com.example.server.service;

import com.example.server.entity.GroupRuleEntity;
import com.example.server.model.Rule;

import java.util.List;

public interface GroupRuleService {

    /**
     * 根据管理员所在分组id与父级获得下级所有菜单
     * @param parentId 父级id
     * @param groupId  分组id
     * @return List<Rule>
     */
    List<GroupRuleEntity> getRuleByParentIdAndGroupId(Integer parentId, Integer groupId);
}
