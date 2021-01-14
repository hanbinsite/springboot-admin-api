package com.example.server.service;

import com.example.server.entity.GroupRuleEntity;
import com.example.server.verify.rule.RuleVo;

import java.util.List;

/**
 * @author hanbin
 */
public interface RuleService {

    /**
     * 获得所有菜单， 按照子级父级关系，无分页
     * @return List
     */
    List<GroupRuleEntity> allRule();

    /**
     * 新增权限
     * @param ruleVo 权限
     */
    void addRule(RuleVo ruleVo);
}
