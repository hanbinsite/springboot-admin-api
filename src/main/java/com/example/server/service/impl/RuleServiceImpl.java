package com.example.server.service.impl;

import com.example.server.common.result.enums.ResultEnum;
import com.example.server.entity.GroupRuleEntity;
import com.example.server.exception.ApiException;
import com.example.server.mapper.RuleMapper;
import com.example.server.model.Rule;
import com.example.server.service.RuleService;
import com.example.server.utils.DateUtils;
import com.example.server.verify.rule.RuleVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hanbin
 */
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

    /**
     * 新增权限
     *
     * @param ruleVo 权限
     */
    @Override
    public void addRule(RuleVo ruleVo) {
        Rule rule = new Rule(ruleVo.getParentId(), ruleVo.getName(), ruleVo.getPath(), ruleVo.getComponent(),
                ruleVo.getHidden(), ruleVo.getRedirect(), ruleVo.getApi(), ruleVo.getTitle(), ruleVo.getIcon(),
                ruleVo.getAffix(), ruleVo.getNoCache(), ruleVo.getActiveMenu(), ruleVo.getSort(), ruleVo.getStatus());
        Integer bool = ruleMapper.addRule(rule);
        if (bool == 0) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "权限新增失败，请稍后重试");
        }
    }

    /**
     * 删除权限
     *
     * @param id id
     */
    @Override
    public void delRule(Integer id) {
        Integer bool = ruleMapper.delRule(id);
        if (bool == 0) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "权限删除失败，请稍后重试");
        }
    }

    /**
     * 获得权限详情
     *
     * @param id 权限id
     * @return rule
     */
    @Override
    public Rule infoRule(Integer id) {
        Rule rule = ruleMapper.infoRule(id);
        if (rule == null) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "菜单不存在");
        }
        return rule;
    }

    /**
     * 编辑权限
     *
     * @param id     权限id
     * @param ruleVo 权限
     */
    @Override
    public void editRule(Integer id, RuleVo ruleVo) {
        Rule rule = new Rule(id, ruleVo.getParentId(), ruleVo.getName(), ruleVo.getPath(), ruleVo.getComponent(),
                ruleVo.getHidden(), ruleVo.getRedirect(), ruleVo.getApi(), ruleVo.getTitle(), ruleVo.getIcon(),
                ruleVo.getAffix(), ruleVo.getNoCache(), ruleVo.getActiveMenu(), ruleVo.getSort(), ruleVo.getStatus());
        Integer bool = ruleMapper.editRule(rule);
        if (bool == 0) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "菜单编辑失败");
        }
    }
}
