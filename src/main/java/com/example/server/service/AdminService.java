package com.example.server.service;

import com.example.server.common.page.model.PageConfig;
import com.example.server.common.page.model.PageResult;
import com.example.server.entity.AdminEntity;
import com.example.server.model.Admin;
import com.example.server.verify.admin.AdminVo;

import java.util.Map;

/**
 * @author hanbin
 */
public interface AdminService {

    /**
     * 获得管理员是否存在
     * @param username 管理员用户名或者手机号
     * @return admin
     */
    Admin getAdmin(String username);

    /**
     * 管理员登录
     * @param username 管理员手机号或者用户名
     * @param password 密码
     * @return admin
     */
    Admin login(String username, String password);

    /**
     * 获得管理员详情
     * @param id 管理员id
     * @return admin
     */
    Admin getAdminById(Integer id);

    /**
     * 获得管理员详细信息
     * @param token token
     * @return Map
     */
    Map<String, String> getAdminInfo(String token);

    /**
     * 新增管理员
     * @param adminVo 管理员内容
     */
    void addAdmin(AdminVo adminVo);

    /**
     * 删除管理员
     * @param id 管理员id
     */
    void delAdmin(Integer id);

    /**
     * 编辑管理员
     * @param id 管理员id
     * @param adminVo 管理员信息
     */
    void editAdmin(Integer id, AdminVo adminVo);

    /**
     * 编辑管理员状态
     * @param id 管理员id
     * @param status 状态
     */
    void editAdminStatus(Integer id, Integer status);

    /**
     * 编辑管理员密码
     * @param id 管理员id
     * @param password 密码
     */
    void editAdminPassword(Integer id, String password);

    /**
     * 获得管理员分页
     * @param pageConfig 分页配置
     * @param groupId 分组id
     * @param status  状态
     * @param search  检索内容
     * @return PageResult
     */
    PageResult getAdminPage(PageConfig pageConfig, Integer groupId, Integer status, String search);

    /**
     * 根据管理员id获得详情
     * @param id 管理员id
     * @return AdminEntity
     */
    AdminEntity getInfoById(Integer id);
}
