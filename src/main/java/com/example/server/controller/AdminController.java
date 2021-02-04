package com.example.server.controller;

import com.example.server.common.page.model.PageConfig;
import com.example.server.common.result.utils.ResultUtils;
import com.example.server.entity.AdminEntity;
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
        adminService.addAdmin(adminVo);
        return ResultUtils.successOnlyMsg("新增成功");
    }

    /**
     * 删除管理员
     * @param id 管理员id
     * @return Object
     */
    @RequestMapping("/del/{id}")
    public Object delAdmin(@PathVariable Integer id) {
        adminService.delAdmin(id);
        return ResultUtils.successOnlyMsg("删除成功");
    }

    @RequestMapping("/edit/{id}")
    public Object editAdmin(@PathVariable Integer id, @RequestBody @Validated AdminVo adminVo) {
        adminService.editAdmin(id, adminVo);
        return ResultUtils.successOnlyMsg("编辑成功");
    }

    @RequestMapping("/info/id/{id}")
    public Object getInfoAdmin(@PathVariable Integer id) {
        AdminEntity admin = adminService.getInfoById(id);
        if (admin == null) {
            return ResultUtils.error("管理员不存在，请检查后重试");
        }
        return admin;
    }
    @RequestMapping("/edit/status")
    public Object editAdminStatus(@RequestParam(value = "id") Integer id, @RequestParam(value = "status") Integer status) {
        adminService.editAdminStatus(id, status);
        return ResultUtils.successOnlyMsg("状态编辑成功");
    }

    @RequestMapping("/edit/password")
    public Object editAdminPassword(@RequestParam(value = "id") Integer id, @RequestParam(value = "status") String password) {
        adminService.editAdminPassword(id, password);
        return ResultUtils.successOnlyMsg("密码编辑成功");
    }

    @RequestMapping("/page")
    public Object getAdminPage(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                               @RequestParam(value = "search", required = false, defaultValue = "") String search,
                               @RequestParam(value = "groupId", required = false, defaultValue = "0") Integer groupId,
                               @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status) {
        PageConfig pageConfig = new PageConfig(pageNum, pageSize);
        return adminService.getAdminPage(pageConfig, groupId, status, search);
    }
}
