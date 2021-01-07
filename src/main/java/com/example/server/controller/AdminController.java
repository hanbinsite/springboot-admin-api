package com.example.server.controller;

import com.example.server.verify.admin.AdminLoginVo;
import com.example.server.model.Admin;
import com.example.server.service.impl.AdminServiceImpl;
import com.example.server.utils.JwtUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/logout")
    public Object logout() {
        return "退出登录成功";
    }

    @RequestMapping("/refresh_token")
    public Object refreshToken() {
        return "刷新token";
    }
}
