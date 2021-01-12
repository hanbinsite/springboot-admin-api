package com.example.server.mapper;

import com.example.server.entity.GroupRuleEntity;
import com.example.server.model.Rule;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface GroupRuleMapper {

    /**
     * 根据管理员所在分组id与父级获得下级所有菜单
     * @param parentId 父级id
     * @param groupId 分组id
     * @return List<GroupRuleEntity>
     */
    @Select("SELECT b.* FROM `group_rules` AS `a` INNER JOIN `rules` AS `b` ON `a`.`rule_id` = `b`.`id` WHERE `b`.`parent_id` = #{parentId} AND `a`.`group_id` = #{groupId} AND `b`.`status` = 1 ORDER BY `b`.`sort` ASC")
    @Results({
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "noCache", column = "no_cache"),
            @Result(property = "activeMenu", column = "active_menu")
    })
    List<GroupRuleEntity> getRuleByParentIdAndGroupId(Integer parentId, Integer groupId);

    /**
     * 检测用户对某个接口是否有权限
     * @param api 当前访问地址
     * @param groupId 管理员分组
     * @return GroupRuleEntity
     */
    @Select("SELECT b.* FROM `group_rules` AS `a` INNER JOIN `rules` AS `b` ON `a`.`rule_id` = `b`.`id` WHERE `b`.`api` = #{api} AND `a`.`group_id` = #{groupId} AND `b`.`status` = 1 ORDER BY `b`.`sort` ASC")
    @Results({
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "noCache", column = "no_cache"),
            @Result(property = "activeMenu", column = "active_menu")
    })
    GroupRuleEntity getRuleByApiAndGroupId(String api, Integer groupId);


    /**
     * 新增分组-权限记录
     * @param groupId 分组id
     * @param routes 权限id
     * @return Integer
     */
    @Insert("<script> " +
            "INSERT INTO `group_rules` (`group_id`, `rule_id`) VALUES" +
            "  <foreach collection =\"routes\" item=\"item\" index= \"index\" separator =\",\"> " +
            "(" +
            "  #{groupId}, #{item}   " +
            ")" +
            " </foreach >  " +
//            "ON DUPLICATE KEY UPDATE  id = id " +
            "</script>")
    Integer addGroupRules(Integer groupId, List<String> routes);

}
