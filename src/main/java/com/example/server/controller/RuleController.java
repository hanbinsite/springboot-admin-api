package com.example.server.controller;

import com.example.server.common.result.utils.ResultUtils;
import com.example.server.entity.GroupRuleEntity;
import com.example.server.service.impl.RuleServiceImpl;
import com.example.server.verify.rule.RuleVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return ruleService.allRule(0);
    }

    @RequestMapping("/add")
    public Object addRule(@RequestBody @Validated RuleVo ruleVo) {
        ruleService.addRule(ruleVo);
        return ResultUtils.successOnlyMsg("新增成功");
    }

    @RequestMapping("/info/{id}")
    public Object info(@PathVariable Integer id) {
        return ruleService.infoRule(id);
    }

    @RequestMapping("/edit/{id}")
    public Object edit(@PathVariable Integer id, @RequestBody @Validated RuleVo ruleVo) {
        ruleService.editRule(id, ruleVo);
        return ResultUtils.successOnlyMsg("编辑成功");
    }

    @RequestMapping("/del/{id}")
    public Object del(@PathVariable Integer id) {
        ruleService.delRule(id);
        return ResultUtils.successOnlyMsg("删除成功");
    }
}
