package com.example.server.mapper;

import com.example.server.entity.GroupRuleEntity;
import com.example.server.model.Rule;
import com.example.server.verify.rule.RuleVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hanbin
 */
@Component
@Mapper
public interface RuleMapper {

    /**
     * 根据父级获得下级所有菜单
     * @param parentId 父级id
     * @return List<Rule>
     */
    @Select("SELECT * FROM `rules` WHERE `parent_id` = #{parentId} AND `status` = 1 ORDER BY sort ASC")
    @Results({
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "noCache", column = "no_cache"),
            @Result(property = "activeMenu", column = "active_menu")
    })
    List<GroupRuleEntity> getRuleByParentId(Integer parentId);

    /**
     * 新增权限
     * @param rule 权限
     * @return int
     */
    @Insert("INSERT INTO `rules` (`parent_id`, `name`, `path`, `component`, `hidden`, `redirect`, `api`, `title`," +
            " `icon`, `affix`, `no_cache`, `active_menu`, `sort`, `status`, `created_at`, `updated_at`) VALUE" +
            " (#{parentId}, #{name}, #{path}, #{component}, #{hidden}, #{redirect}, #{api}, #{title}, #{icon}," +
            " #{affix}, #{noCache}, #{activeMenu}, #{sort}, #{status}, #{createdAt}, #{updatedAt})")
    Integer addRule(Rule rule);

    /**
     * 删除菜单
     * @param id id
     * @return Integer
     */
    @Delete("DELETE FROM `rules` WHERE `id` = #{id}")
    Integer delRule(Integer id);

    /**
     * 获得权限详情
     * @param id 权限id
     * @return rule
     */
    @Select("SELECT * FROM `rules` WHERE `id` = #{id}")
    @Results({
            @Result(property = "parentId", column = "parent_id"),
            @Result(property = "noCache", column = "no_cache"),
            @Result(property = "activeMenu", column = "active_menu")
    })
    Rule infoRule(Integer id);

    /**
     * 编辑权限
     * @param rule rule
     * @return int
     */
    @Update("UPDATE `rules` SET `parent_id` = #{parentId}, `name` = #{name}, `path` = #{path}, `component` = " +
            "#{component}, `hidden` = #{hidden}, `redirect` = #{redirect}, `api` = #{api}, `title` = #{title}, " +
            "`icon` = #{icon}, `affix` = #{affix}, `no_cache` = #{noCache}, `active_menu` = #{activeMenu}, `sort` =" +
            " #{sort}, `status` = #{status}, `updated_at` = #{updatedAt} WHERE `id` = #{id}")
    Integer editRule(Rule rule);
}
