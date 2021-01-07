package com.example.server.mapper;

import com.example.server.model.Rule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

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
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<Rule> getRuleByParentId(Integer parentId);
}
