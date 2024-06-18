package com.lxj.chatForum.mapper;

import com.lxj.chatForum.pojo.SortPojo;

import java.util.List;

public interface SortMapper {
    //    获取全部分类
    List<SortPojo> getAllSort();

    //插入某个分类
    int insert(SortPojo sort);

    //更新某个分类
    int update(SortPojo sort);

    //删除某个分类
    int deleteById(String id);

    //根据id获取分类
    SortPojo getSortById(String id);
}
