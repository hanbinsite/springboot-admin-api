package com.example.server.controller;

import com.example.server.verify.admin.AdminLoginVo;
import com.example.server.model.Admin;
import com.example.server.service.impl.AdminServiceImpl;
import com.example.server.utils.JwtUtils;
import com.example.server.verify.admin.AdminVo;
import org.springframework.data.relational.core.sql.In;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author hanbin
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminServiceImpl adminService;

    private final JwtUtils jwtUtils;

    public AdminController(AdminServiceImpl adminService, JwtUtils jwtUtils) {
        this.adminService = adminService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public Object login(@RequestBody @Validated AdminLoginVo adminLoginVo) {
        System.out.print(adminLoginVo.getUsername());
        Admin admin = adminService.login(adminLoginVo.getUsername(), adminLoginVo.getPassword());
        String token = jwtUtils.getToken(admin);
        String menu = "这里有可能返回该用户所有权限菜单";
        return token;
    }

    /**
     * 获得管理员用户名等详细信息
     * @param request  HttpServletRequest
     * @return getAdminInfo
     */
    @RequestMapping("info")
    public Object info(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return adminService.getAdminInfo(token);
    }

    @RequestMapping("/logout")
    public Object logout() {
        return "退出登录成功";
    }

    @RequestMapping("/refresh_token")
    public Object refreshToken() {
        return "刷新token";
    }

    @RequestMapping("/add")
    public Object addAdmin(@RequestBody @Validated AdminVo adminVo) {
        return "新增管理员";
    }

    /**
     * 删除管理员
     * @param id 管理员id
     * @return Object
     */
    @RequestMapping("/del/{id}")
    public Object delAdmin(@PathVariable Integer id) {
        return "删除管理员";
    }

    @RequestMapping("/edit/{id}")
    public Object editAdmin(@PathVariable Integer id, @RequestBody @Validated AdminVo adminVo) {
        return "编辑管理员";
    }

    @RequestMapping("/info/id/{id}")
    public Object getInfoAdmin(@PathVariable Integer id) {
        return "获得管理员详情";
    }
    @RequestMapping("/status")
    public Object editAdminStatus(@RequestParam(value = "id") Integer id, @RequestParam(value = "status") Integer status) {
        return "编辑管理员状态";
    }
}
