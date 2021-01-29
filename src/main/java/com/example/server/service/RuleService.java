package com.example.server.service;

import com.example.server.entity.GroupRuleEntity;
import com.example.server.model.Rule;
import com.example.server.verify.rule.RuleVo;

import java.util.List;

/**
 * @author hanbin
 */
public interface RuleService {

    /**
     * 获得所有菜单， 按照子级父级关系，无分页
     * @param parentId 上级id
     * @return List
     */
    List<GroupRuleEntity> allRule(Integer parentId);

    /**
     * 新增权限
     * @param ruleVo 权限
     */
    void addRule(RuleVo ruleVo);

    /**
     * 删除权限
     * @param id id
     */
    void delRule(Integer id);

    /**
     * 获得权限详情
     * @param id 权限id
     * @return rule
     */
    Rule infoRule(Integer id);

    /**
     * 编辑权限
     * @param id 权限id
     * @param ruleVo 权限
     */
    void editRule(Integer id, RuleVo ruleVo);
}
