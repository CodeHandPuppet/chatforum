package com.lxj.chatForum.dao;

import com.lxj.chatForum.pojo.SortPojo;

import java.util.List;

public interface SortDao {
    List<SortPojo> getAllSort();
    int insert(SortPojo sort);
    int update(SortPojo sort);
    int deleteById(String id);
}
