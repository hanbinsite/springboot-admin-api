package com.example.server.mapper;

import com.example.server.entity.AdminEntity;
import com.example.server.model.Admin;
import com.example.server.model.Group;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hanbin
 */
@Component
@Mapper
public interface AdminMapper {

    /**
     * 根据用户名获得管理员详情
     * @param username 用户名
     * @return Admin
     */
    @Select("SELECT * FROM admin WHERE `username` = #{username}")
    @Results({
            @Result(property = "isSuper", column = "is_super"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    Admin getAdminByUsername(String username);

    /**
     * 根据用户名获得管理员详情
     * @param username 用户名
     * @param id 管理员
     * @return Admin
     */
    @Select("SELECT * FROM admin WHERE `username` = #{username} AND `id` != #{id}")
    @Results({
            @Result(property = "isSuper", column = "is_super"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    Admin getAdminByUsernameExit(String username, Integer id);

    /**
     * 根据手机号获得管理员详情
     * @param mobile 手机号
     * @return Admin
     */
    @Select("SELECT * FROM admin WHERE `mobile` = #{mobile}")
    @Results({
            @Result(property = "isSuper", column = "is_super"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    Admin getAdminByMobile(String mobile);

    /**
     * 根据手机号获得管理员详情
     * @param mobile 手机号
     * @param id 手机号
     * @return Admin
     */
    @Select("SELECT * FROM admin WHERE `mobile` = #{mobile} AND `id` != #{id}")
    @Results({
            @Result(property = "isSuper", column = "is_super"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    Admin getAdminByMobileExit(String mobile, Integer id);

    /**
     * 根据用户名、手机号获得管理员详情
     * @param username 用户名
     * @param mobile 手机号
     * @return Admin
     */
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

    /**
     * 新增管理员
     * @param admin 管理员信息
     * @return int
     */
    @Insert("INSERT INTO `admin` (`is_super`, `group_id`, `username`, `nickname`, `mobile`, `mail`, `password`," +
            " `salt`, `status`, `created_at`, `updated_at`) VALUE ( #{isSuper}, #{groupId}, #{username}, #{nickname}," +
            " #{mobile}, #{mail}, #{password}, #{salt}, #{status}, #{createdAt}, #{updatedAt} )")
    Integer addAdmin(Admin admin);

    /**
     * 删除管理员
     * @param id id
     * @return int
     */
    @Delete("DELETE FROM `admin` WHERE `id` = #{id}")
    Integer delAdmin(Integer id);

    /**
     * 编辑管理员
     * @param admin 管理员
     * @return int
     */
    @Update("UPDATE `admin` SET `is_super` = #{isSuper}, `group_id` = #{groupId}, `username` = #{username}," +
            " `nickname` = #{nickname}, `mobile` = #{mobile}, `mail` = #{mail}, `status` = #{status}," +
            " `updated_at` = #{updatedAt} WHERE `id` = #{id}")
    Integer editAdmin(Admin admin);

    /**
     * 修改管理员密码
     * @param id 用户id
     * @param password 密码
     * @param salt 加密盐
     * @param updatedAt 更新时间
     * @return int
     */
    @Update("UPDATE `admin` SET `password` = #{password}, `salt` = #{salt}, `updated_at` = #{updatedAt}" +
            " WHERE `id` = #{id}")
    Integer editAdminPassword(Integer id, String password, String salt, String updatedAt);

    /**
     * 编辑管理员状态
     * @param id 管理员id
     * @param status 状态
     * @param updatedAt 更新时间
     * @return  int
     */
    @Update("UPDATE `admin` SET `status` = #{status}, `updated_at` = #{updatedAt}  WHERE `id` = #{id}")
    Integer editAdminStatus(Integer id, Integer status, String updatedAt);

    /**
     * 获得用户分组分页
     * @param groupId 状态
     * @param search 分组名称模糊检索
     * @param status 分组名称模糊检索
     * @return List<Group>
     */
    @Select("<script>SELECT `a`.`id`, `a`.`is_super`, `a`.`group_id`, `a`.`username`, `a`.`nickname`, `a`.`mobile`," +
            " `a`.`mail`, `a`.`status`, `a`.`created_at`, `a`.`updated_at`, `b`.`name` as `group_name` FROM" +
            " admin as `a` INNER JOIN `groups` as `b` ON `a`.`group_id` = `b`.`id` WHERE 1 = 1  <if test=\"status != -1 \"> " +
            "AND `a`.`status` = #{status} </if>  <if test=\"search != '' \"> AND ( `a`.`username` like " +
            "concat('%', #{search}, '%') OR `a`.`nickname`  like concat('%', #{search}, '%') ) </if>" +
            " <if test=\"groupId != 0 \">  AND `a`.`group_id`= #{groupId}  </if> ORDER BY `a`.`created_at` DESC </script>")
    @Results({
            @Result(property = "isSuper", column = "is_super"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "groupName", column = "group_name"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<AdminEntity> getAdminPage(Integer groupId, String search, Integer status);

    /**
     * 根据管理员id获得详情
     * @param id 管理员id
     * @return admin
     */
    @Select("SELECT `id`, `is_super`, `group_id`, `username`, `nickname`, `mobile`, `mail`, `status`, `created_at`," +
            " `updated_at` FROM admin WHERE id = #{id}")
    @Results({
            @Result(property = "isSuper", column = "is_super"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    AdminEntity getInfoById(Integer id);
}
