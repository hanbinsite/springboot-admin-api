package com.example.server.mapper;

import com.example.server.entity.GroupRuleEntity;
import com.example.server.model.Rule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface GroupRuleMapper {

    /**
     * 根据管理员所在分组id与父级获得下级所有菜单
     * @param parentId 父级id
     * @param groupId 分组id
     * @return List<Rule>
     */
    @Select("SELECT b.* FROM `group_rules` AS `a` INNER JOIN `rules` AS `b` ON `a`.`rule_id` = `b`.`id` WHERE `b`.`parent_id` = #{parentId} AND `a`.`group_id` = #{groupId} AND `b`.`status` = 1 ORDER BY `b`.`sort` ASC")
    @Results({
            @Result(property = "parentId", column = "parent_id")
    })
    List<GroupRuleEntity> getRuleByParentIdAndGroupId(Integer parentId, Integer groupId);

}
