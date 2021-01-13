package com.example.server.service.impl;

import com.example.server.entity.GroupRuleEntity;
import com.example.server.mapper.AdminMapper;
import com.example.server.mapper.GroupRuleMapper;
import com.example.server.model.Admin;
import com.example.server.service.GroupRuleService;
import com.example.server.utils.RedisUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupRuleServiceImpl implements GroupRuleService {

    private final GroupRuleMapper groupRuleMapper;

    private final AdminMapper adminMapper;

    private final RedisUtils redisUtils;

    public GroupRuleServiceImpl(GroupRuleMapper groupRuleMapper, AdminMapper adminMapper, RedisUtils redisUtils) {
        this.groupRuleMapper = groupRuleMapper;
        this.adminMapper = adminMapper;
        this.redisUtils = redisUtils;
    }

    /**
     * 根据管理员所在分组id与父级获得下级所有菜单
     * @param parentId 父级id
     * @param groupId  分组id
     * @return List<Rule>
     */
    @Override
    public List<GroupRuleEntity> getRuleByParentIdAndGroupId(Integer parentId, Integer groupId) {
        List<GroupRuleEntity> list = groupRuleMapper.getRuleByParentIdAndGroupId(parentId, groupId);
        list.forEach(item-> {
            List<GroupRuleEntity> child = getRuleByParentIdAndGroupId(item.getId(), groupId);
            item.setChildren(child);
            item.setGroupId(groupId);
        });
        return list;
    }

    /**
     * 检测用户对某个接口是否有权限
     *
     * @param api     当前访问地址
     * @param groupId 管理员分组
     * @return GroupRuleEntity
     */
    @Override
    public Boolean getRuleByApiAndGroupId(String api, Integer groupId) {
        GroupRuleEntity groupRuleEntity = groupRuleMapper.getRuleByApiAndGroupId(api, groupId);
        return groupRuleEntity != null;
    }

    /**
     * 根据管理员所在分组id与父级获得下级所有菜单
     *
     * @param token 管理员token
     * @return List<Rule>
     */
    @Override
    public List<GroupRuleEntity> getMyRule(String token) {
        Object cache = redisUtils.get("admin:token:" + token);
        String id = cache.toString();
        Admin admin = adminMapper.getAdminById(Integer.parseInt(id));
        return getRuleByParentIdAndGroupId(0, admin.getGroupId());
    }
}
