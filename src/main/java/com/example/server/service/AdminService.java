package com.example.server.service;

import com.example.server.model.Admin;

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
}
