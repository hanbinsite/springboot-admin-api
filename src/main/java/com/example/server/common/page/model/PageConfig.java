package com.example.server.common.page.model;

import lombok.Data;

@Data
public class PageConfig {
    private Integer pageNum;

    private Integer pageSize;

    public PageConfig(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
