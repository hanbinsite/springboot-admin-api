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

    /**
     * 检测用户对某个接口是否有权限
     * @param api 当前访问地址
     * @param groupId 管理员分组
     * @return GroupRuleEntity
     */
    Boolean getRuleByApiAndGroupId(String api, Integer groupId);

    /**
     * 根据管理员所在分组id与父级获得下级所有菜单
     * @param token 管理员token
     * @return List<Rule>
     */
    List<GroupRuleEntity> getMyRule(String token);
}
