package com.example.server.verify.group;


import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class GroupVo {

    private Integer id;

    @NotEmpty(message = "分组名称不能为空")
    @NotNull(message = "分组名称不能为空")
    private String name;

    @NotEmpty(message = "分组说明不能为空")
    @NotNull(message = "分组说明不能为空")
    private String description;

    @NotEmpty(message = "菜单不能为空")
    @NotNull(message = "菜单不能为空")
    private List<String> rules;

}
