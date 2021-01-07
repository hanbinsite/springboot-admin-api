package com.example.server.common.page.utils;

import com.example.server.common.page.model.PageConfig;
import com.example.server.common.page.model.PageResult;
import com.github.pagehelper.PageInfo;

public class PageUtils {
    /**
     * 将分页信息封装到统一的接口
     * @param pageRequest PageConfig
     * @param pageInfo PageInfo
     * @return PageResult
     */
    public static PageResult getPageResult(PageConfig pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setList(pageInfo.getList());
        return pageResult;
    }
}
