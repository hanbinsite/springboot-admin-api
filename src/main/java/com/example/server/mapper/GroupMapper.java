package com.example.server.mapper;

import com.example.server.model.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface GroupMapper {

    /**
     * 获得用户分组分页
     * @param status 状态
     * @param name 分组名称模糊检索
     * @return List<Group>
     */
    @Select("<script>SELECT * FROM groups WHERE 1 = 1  <if test=\"status != -1 \"> AND `status` = #{status} </if>" +
            "<if test=\"name != '' \"> AND `name` like concat('%', #{name}, '%') </if></script>")
    @Results({
            @Result(property = "isSuper", column = "is_super"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<Group> getGroupPage(Integer status, String name);
}
