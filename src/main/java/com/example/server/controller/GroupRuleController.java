package com.example.server.controller;

import com.example.server.entity.GroupRuleEntity;
import com.example.server.service.impl.GroupRuleServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group_rule")
public class GroupRuleController {

    private final GroupRuleServiceImpl groupRuleService;

    public GroupRuleController(GroupRuleServiceImpl groupRuleService) {
        this.groupRuleService = groupRuleService;
    }

    @RequestMapping("rules")
    public Object getRuleByAdmin() {
        return groupRuleService.getRuleByParentIdAndGroupId(0, 1);
    }
}
