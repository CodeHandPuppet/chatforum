package com.lxj.chatForum.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lxj.chatForum.dao.SortDao;
import com.lxj.chatForum.dao.impl.SortDaoImpl;
import com.lxj.chatForum.mapper.SortMapper;
import com.lxj.chatForum.pojo.ForumPojo;
import com.lxj.chatForum.pojo.SortPojo;
import com.lxj.chatForum.service.SortService;
import com.lxj.chatForum.utils.ReturnData;
import com.lxj.chatForum.utils.SqlSessionUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortServiceImpl implements SortService {
//    SortDao sortDao=new SortDaoImpl();
    SortMapper sortDao= SqlSessionUtils.openSession().getMapper(SortMapper.class);
    @Override
    public String getAll() {
        Gson gson=new GsonBuilder().create();
        List<SortPojo> list=sortDao.getAllSort();
        if(list == null){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success(list));
    }

    @Override
    public String toEditSort(String json) {
        Gson gson=new GsonBuilder().create();
        SortPojo sortPojo = gson.fromJson(json, SortPojo.class);
        int update = sortDao.update(sortPojo);
        if(update==0){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String toAddSort(String json) {
        Gson gson=new GsonBuilder().create();
        SortPojo sortPojo = gson.fromJson(json, SortPojo.class);
        int update = sortDao.insert(sortPojo);
        if(update==0){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String toDeleteSort(String json) {
        Gson gson=new GsonBuilder().create();
        Type typeC = new TypeToken<HashMap<String,String>>() {}.getType();
        Map<String,String> jsonMap = gson.fromJson(json, typeC);
        String id = jsonMap.get("id");
        int count = sortDao.deleteById(id);
        if(count==0){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }
}
