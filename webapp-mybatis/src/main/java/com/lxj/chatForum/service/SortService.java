package com.lxj.chatForum.service;

import com.lxj.chatForum.pojo.SortPojo;

import java.util.List;

public interface SortService {

    List<SortPojo> getAll();

    void toEditSort(SortPojo sortPojo);

    void toAddSort(SortPojo sortPojo);

    void toDeleteSort(String id);

}
