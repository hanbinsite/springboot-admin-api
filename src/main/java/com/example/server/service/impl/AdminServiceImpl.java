package com.example.server.service.impl;

import com.example.server.common.result.enums.ResultEnum;
import com.example.server.exception.ApiException;
import com.example.server.exception.NotLoginException;
import com.example.server.mapper.AdminMapper;
import com.example.server.model.Admin;
import com.example.server.service.AdminService;
import com.example.server.utils.PasswordUtils;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    /**
     * 获得管理员是否存在
     *
     * @param username 管理员用户名或者手机号
     * @return admin
     */
    @Override
    public Admin getAdmin(String username) {
        return null;
    }

    /**
     * 管理员登录
     *
     * @param username 管理员手机号或者用户名
     * @param password 密码
     * @return admin
     */
    @Override
    public Admin login(String username, String password) {
        Admin admin = adminMapper.getAdminByUsernameOrMobile(username, username);
        if (admin == null) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "请确认账号密码是否正确");
        }
        Boolean bool = PasswordUtils.verify(password, admin.getSalt(), admin.getPassword());
        if (!bool) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "请确认账号密码是否正确");
        }
        return admin;
    }
}
