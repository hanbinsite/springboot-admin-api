package com.example.server.controller;

import com.example.server.entity.GroupRuleEntity;
import com.example.server.service.impl.RuleServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rule")
public class RuleController {

    private final RuleServiceImpl ruleService;

    public RuleController(RuleServiceImpl ruleService) {
        this.ruleService = ruleService;
    }


    @RequestMapping("/all/list")
    public Object allRule() {
        return ruleService.allRule();
    }
}
