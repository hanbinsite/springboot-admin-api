package com.example.server.controller;

import com.example.server.entity.GroupRuleEntity;
import com.example.server.service.impl.RuleServiceImpl;
import com.example.server.verify.rule.RuleVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hanbin
 */
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

    @RequestMapping("/add")
    public Object addRule(@RequestBody @Validated RuleVo ruleVo) {
        ruleService.addRule(ruleVo);
        return "新增成功";
    }
}
