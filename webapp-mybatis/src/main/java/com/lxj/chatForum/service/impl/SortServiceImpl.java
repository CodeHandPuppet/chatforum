package com.lxj.chatForum.service.impl;


import com.lxj.chatForum.mapper.SortMapper;
import com.lxj.chatForum.pojo.SortPojo;
import com.lxj.chatForum.service.SortService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortServiceImpl implements SortService {


    @Resource
    SortMapper sortMapper;

    @Override
    public List<SortPojo> getAll() {

        List<SortPojo> list = sortMapper.getAllSort();


        return list;
    }

    @Override
    public void toEditSort(SortPojo sortPojo) {

        int update = sortMapper.update(sortPojo);

    }

    @Override
    public void toAddSort(SortPojo sortPojo) {

        int update = sortMapper.insert(sortPojo);

    }

    @Override
    public void toDeleteSort(String id) {


        int count = sortMapper.deleteById(id);

    }
}
