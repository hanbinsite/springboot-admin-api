package com.example.server.controller;

import com.example.server.common.page.model.PageConfig;
import com.example.server.common.result.utils.ResultUtils;
import com.example.server.service.impl.GroupServiceImpl;
import com.example.server.verify.group.GroupVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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


    /**
     * 新增分组
     * @param groupVo groupVo
     * @return ResultUtils
     */
    @PostMapping("/add")
    public Object add(@RequestBody @Validated GroupVo groupVo) {
        Boolean bool = groupService.addGroup(groupVo);
        if (bool) {
            return ResultUtils.successOnlyMsg("新增成功");
        } else {
            return ResultUtils.error("新增失败，请稍后重试");
        }
    }

    /**
     * 删除分组
     * @param id 分组id
     * @return object
     */
    @RequestMapping("/del/{id}")
    public Object del(@PathVariable Integer id) {
        Boolean del = groupService.delGroup(id);
        if (del) {
            return ResultUtils.successOnlyMsg("删除成功");
        } else {
            return ResultUtils.error("删除失败,请稍后重试");
        }
    }

    @RequestMapping("/edit/{id}")
    public Object edit(@PathVariable Integer id, @RequestBody @Validated GroupVo groupVo) {
        Boolean bool = groupService.editGroup(id, groupVo);
        if (bool) {
            return ResultUtils.successOnlyMsg("保存成功");
        } else {
            return ResultUtils.error("保存失败,请稍后重试");
        }
    }
}
