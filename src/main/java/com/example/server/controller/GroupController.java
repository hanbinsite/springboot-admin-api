package com.example.server.controller;

import com.example.server.common.page.model.PageConfig;
import com.example.server.service.impl.GroupServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupController {

    private final GroupServiceImpl groupService;

    public GroupController(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    @RequestMapping("page")
    public Object groupPage(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status,
                            @RequestParam(value = "name", required = false, defaultValue = "") String name) {
        PageConfig pageConfig = new PageConfig(pageNum, pageSize);
        return groupService.getGroupPage(pageConfig, status, name);
    }
}
