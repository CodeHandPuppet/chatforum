package com.lxj.chatForum.service.impl;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.lxj.chatForum.dao.CommentDao;
import com.lxj.chatForum.dao.impl.CommentDaoImpl;
import com.lxj.chatForum.mapper.CommentMapper;
import com.lxj.chatForum.pojo.CommentPojo;
import com.lxj.chatForum.service.CommentService;
import com.lxj.chatForum.utils.ReturnData;
import com.lxj.chatForum.utils.SqlSessionUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CommentServiceImpl implements CommentService {

//    CommentDao commentMapper=new CommentDaoImpl();
    @Resource
    CommentMapper commentMapper;

    @Override
    public String getComments(String json) {
        Gson gson=new GsonBuilder().create();
        Type type = new TypeToken<HashMap<String,String>>() {}.getType();
        Map<String,String> jsonMap = gson.fromJson(json, type);
        String forumId = jsonMap.get("forumId");
        List<CommentPojo> list=commentMapper.getCommentsByForumId(forumId);
        if(list==null){
            return gson.toJson(ReturnData.error(null)) ;
        }
        return gson.toJson(ReturnData.success(list));
    }

    @Override
    public String getSave(String json) {
        Gson gson=new GsonBuilder().create();
        CommentPojo comment = gson.fromJson(json,CommentPojo.class);
//        String forumId = jsonMap.get("forumId");
        int id=commentMapper.insertReturnId(comment);
        if(id==-1){
            return gson.toJson(ReturnData.error(null)) ;
        }
        HashMap<String,Integer> map =new HashMap<>();
        map.put("id",id);
        return gson.toJson(ReturnData.success(map));
    }

    @Override
    public String toDelete(String json) {
        Gson gson=new GsonBuilder().create();
        Type type = new TypeToken<HashMap<String,String>>() {}.getType();
        Map<String,String> jsonMap = gson.fromJson(json, type);
        String id= jsonMap.get("id");
        String parentId=jsonMap.get("parentId");
        System.out.println(parentId);
         int count=commentMapper.deleteById(id,parentId);

        if(count==0){
            return gson.toJson(ReturnData.error(null)) ;
        }
        return gson.toJson(ReturnData.success("ok"));
    }
}
