package com.example.server.controller;

import com.example.server.service.impl.GroupRuleServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanbin
 */
@RestController
@RequestMapping("/group_rule")
public class GroupRuleController {

    private final GroupRuleServiceImpl groupRuleService;

    public GroupRuleController(GroupRuleServiceImpl groupRuleService) {
        this.groupRuleService = groupRuleService;
    }

    @RequestMapping("rules")
    public Object getRuleByAdmin(@RequestParam(value = "token") String token) {
        return groupRuleService.getMyRule(token);
    }

    @RequestMapping("/rules/group")
    public Object getRuleByGroup(@RequestParam(value = "groupId") Integer groupId) {
        return groupRuleService.getRuleByParentIdAndGroupId(0, groupId);
    }
}
