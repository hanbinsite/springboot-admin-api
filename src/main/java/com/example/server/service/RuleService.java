package com.example.server.service;

import com.example.server.entity.GroupRuleEntity;

import java.util.List;

public interface RuleService {

    /**
     * 获得所有菜单， 按照子级父级关系，无分页
     * @return List
     */
    List<GroupRuleEntity> allRule();
}
