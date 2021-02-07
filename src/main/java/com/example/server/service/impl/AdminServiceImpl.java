package com.example.server.service.impl;

import com.example.server.common.page.model.PageConfig;
import com.example.server.common.page.model.PageResult;
import com.example.server.common.page.utils.PageUtils;
import com.example.server.common.result.enums.ResultEnum;
import com.example.server.entity.AdminEntity;
import com.example.server.exception.ApiException;
import com.example.server.mapper.AdminMapper;
import com.example.server.model.Admin;
import com.example.server.model.Group;
import com.example.server.service.AdminService;
import com.example.server.utils.DateUtils;
import com.example.server.utils.PasswordUtils;
import com.example.server.utils.RedisUtils;
import com.example.server.verify.admin.AdminVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author hanbin
 */
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

    /**
     * 新增管理员
     *
     * @param adminVo 管理员内容
     */
    @Override
    public void addAdmin(AdminVo adminVo) {
        Admin have = adminMapper.getAdminByUsername(adminVo.getUsername());
        if (have != null) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "用户名已存在");
        }
        have = adminMapper.getAdminByMobile(adminVo.getMobile());
        if (have != null) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "手机号已存在");
        }
        Map<String, String> password = PasswordUtils.generalPassword(adminVo.getPassword());
        Admin admin = new Admin(0, adminVo.getGroupId(), adminVo.getUsername(),
                adminVo.getNickname(), password.get("password"), password.get("salt"), adminVo.getMobile(),
                adminVo.getMail(), adminVo.getStatus());
        Integer bool = adminMapper.addAdmin(admin);
        if (bool <= 0) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "管理员新增失败，请检查后重试");
        }
    }

    /**
     * 删除管理员
     *
     * @param id 管理员id
     */
    @Override
    public void delAdmin(Integer id) {
        Integer bool = adminMapper.delAdmin(id);
        if (bool <= 0) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "管理员删除失败，请检查后重试");
        }
    }

    /**
     * 编辑管理员
     *
     * @param id      管理员id
     * @param adminVo 管理员信息
     */
    @Override
    public void editAdmin(Integer id, AdminVo adminVo) {
        Admin have = adminMapper.getAdminByUsernameExit(adminVo.getUsername(), id);
        if (have != null) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "用户名已存在");
        }
        have = adminMapper.getAdminByMobileExit(adminVo.getMobile(), id);
        if (have != null) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "手机号已存在");
        }
        Admin admin = new Admin(id, adminVo.getIsSuper(), adminVo.getGroupId(), adminVo.getUsername(),
                adminVo.getNickname(), adminVo.getMobile(), adminVo.getMail(), adminVo.getStatus());
        Integer bool = adminMapper.editAdmin(admin);
        if (bool <= 0) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "管理员编辑失败，请检查后重试");
        }
    }

    /**
     * 编辑管理员状态
     *
     * @param id     管理员id
     * @param status 状态
     */
    @Override
    public void editAdminStatus(Integer id, Integer status) {
        Integer bool = adminMapper.editAdminStatus(id, status, DateUtils.getNowTime());
        if (bool <= 0) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "状态编辑失败，请检查后重试");
        }
    }

    /**
     * 编辑管理员密码
     *
     * @param id       管理员id
     * @param password 密码
     */
    @Override
    public void editAdminPassword(Integer id, String password) {
        Map<String, String> newPassword = PasswordUtils.generalPassword(password);
        Integer bool = adminMapper.editAdminPassword(id, newPassword.get("password"), newPassword.get("salt"), DateUtils.getNowTime());
        if (bool <= 0) {
            throw new ApiException(ResultEnum.FAIL.getCode(), "密码修改失败，请检查后重试");
        }
    }

    /**
     * 获得用户分页
     *
     * @param pageConfig 分页参数
     * @return PageResult
     */
    @Override
    public PageResult getAdminPage(PageConfig pageConfig, Integer groupId, Integer status, String search) {
        return PageUtils.getPageResult(pageConfig, getPageInfo(pageConfig, status, search, groupId));
    }

    /**
     * 根据管理员id获得详情
     *
     * @param id 管理员id
     * @return AdminEntity
     */
    @Override
    public AdminEntity getInfoById(Integer id) {
        return adminMapper.getInfoById(id);
    }

    /**
     * 调用分页插件完成分页
     * @param pageConfig PageConfig
     * @return PageInfo
     */
    private PageInfo<AdminEntity> getPageInfo(PageConfig pageConfig, Integer status, String search, Integer groupId) {
        int pageNum = pageConfig.getPageNum();
        int pageSize = pageConfig.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<AdminEntity> adminEntityList = adminMapper.getAdminPage(groupId, search, status);
        return new PageInfo<AdminEntity>(adminEntityList);
    }
}
