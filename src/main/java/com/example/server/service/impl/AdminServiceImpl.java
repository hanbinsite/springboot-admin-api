package com.example.server.service.impl;

import com.example.server.common.result.enums.ResultEnum;
import com.example.server.exception.ApiException;
import com.example.server.mapper.AdminMapper;
import com.example.server.model.Admin;
import com.example.server.service.AdminService;
import com.example.server.utils.PasswordUtils;
import com.example.server.utils.RedisUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    private final RedisUtils redisUtils;

    public AdminServiceImpl(AdminMapper adminMapper, RedisUtils redisUtils) {
        this.adminMapper = adminMapper;
        this.redisUtils = redisUtils;
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

    /**
     * 获得管理员详情
     *
     * @param id 管理员id
     * @return admin
     */
    @Override
    public Admin getAdminById(Integer id) {
        return adminMapper.getAdminById(id);
    }

    /**
     * 获得管理员详细信息
     *
     * @param token token
     * @return Map
     */
    @Override
    public Map<String, String> getAdminInfo(String token) {
        Object cache = redisUtils.get("admin:token:" + token);
        String id = cache.toString();
        Admin admin = getAdminById(Integer.parseInt(id));
        Map<String, String> info = new HashMap<>(6);
        info.put("name", admin.getNickname());
        info.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        info.put("introduction", "这是一个简介");
        info.put("roles", "admin");
        return info;
    }
}
