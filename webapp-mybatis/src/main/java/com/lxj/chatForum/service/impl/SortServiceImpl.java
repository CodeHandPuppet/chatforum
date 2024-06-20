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
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SortServiceImpl implements SortService {
//    SortDao sortMapper=new SortDaoImpl();
    @Resource
    SortMapper sortMapper;
    @Override
    public String getAll() {
        Gson gson=new GsonBuilder().create();
        List<SortPojo> list=sortMapper.getAllSort();
        if(list == null){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success(list));
    }

    @Override
    public String toEditSort(String json) {
        Gson gson=new GsonBuilder().create();
        SortPojo sortPojo = gson.fromJson(json, SortPojo.class);
        int update = sortMapper.update(sortPojo);
        if(update==0){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String toAddSort(String json) {
        Gson gson=new GsonBuilder().create();
        SortPojo sortPojo = gson.fromJson(json, SortPojo.class);
        int update = sortMapper.insert(sortPojo);
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
        int count = sortMapper.deleteById(id);
        if(count==0){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }
}
