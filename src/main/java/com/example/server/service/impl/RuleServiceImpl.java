package com.example.server.service.impl;

import com.example.server.entity.GroupRuleEntity;
import com.example.server.mapper.RuleMapper;
import com.example.server.service.RuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {

    private final RuleMapper ruleMapper;

    public RuleServiceImpl(RuleMapper ruleMapper) {
        this.ruleMapper = ruleMapper;
    }

    /**
     * 获得所有菜单， 按照子级父级关系，无分页
     *
     * @return List
     */
    @Override
    public List<GroupRuleEntity> allRule() {
        List<GroupRuleEntity> list = ruleMapper.getRuleByParentId(0);
        list.forEach(item-> {
            List<GroupRuleEntity> child = ruleMapper.getRuleByParentId(item.getId());
            item.setChildren(child);
        });
        return list;
    }
}
