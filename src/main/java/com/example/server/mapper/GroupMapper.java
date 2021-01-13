package com.example.server.mapper;

import com.example.server.model.Group;
import org.apache.ibatis.annotations.*;
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

    /**
     * 新增分组菜单
     * @param group Group
     * @return Integer
     */
    @Insert("INSERT INTO `groups` (`name`, `description`, `is_super`, `status`, `created_at`, `updated_at`) VALUE " +
            "(#{name}, #{description}, #{isSuper}, #{status}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer addGroup(Group group);


    /**
     * 删除分组
     * @param groupId groupId
     * @return int
     */
    @Delete("DELETE FROM `groups` WHERE `ID` = #{groupId}")
    Integer delGroup(Integer groupId);


    /**
     * 根据id获得分组详情
     * @param id 分组id
     * @return group
     */
    @Select("SELECT * FROM `groups` WHERE `id` = #{id}")
    @Results({
            @Result(property = "isSuper", column = "is_super"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    Group getGroupById(Integer id);

    /**
     * 更新分组信息
     * @param group group
     * @return Integer
     */
    @Update("UPDATE `groups` SET `name` = #{name}, `description` = #{description}, `updated_at` = #{updatedAt} WHERE `id` = #{id}")
    Integer editGroup(Group group);
}
