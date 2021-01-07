package com.example.server.mapper;

import com.example.server.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AdminMapper {

    @Select("SELECT * FROM admin WHERE `username` = #{username}")
    @Results({
            @Result(property = "isSuper", column = "is_super"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    Admin getAdminByUsername(String username);

    @Select("SELECT * FROM admin WHERE `mobile` = #{mobile}")
    @Results({
            @Result(property = "isSuper", column = "is_super"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    Admin getAdminByMobile(String mobile);

    @Select("SELECT * FROM admin WHERE `username` = #{username} OR  `mobile` = #{mobile}")
    @Results({
            @Result(property = "isSuper", column = "is_super"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    Admin getAdminByUsernameOrMobile(String username, String mobile);

    /**
     * 根据管理员id获得详情
     * @param id 管理员id
     * @return admin
     */
    @Select("SELECT * FROM admin WHERE id = #{id}")
    @Results({
            @Result(property = "isSuper", column = "is_super"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    Admin getAdminById(Integer id);
}
