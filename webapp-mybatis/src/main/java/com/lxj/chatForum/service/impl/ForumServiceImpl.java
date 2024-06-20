package com.lxj.chatForum.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lxj.chatForum.dao.ForumDao;
import com.lxj.chatForum.dao.impl.ForumDaoImpl;
import com.lxj.chatForum.mapper.ForumMapper;
import com.lxj.chatForum.pojo.ForumPojo;
import com.lxj.chatForum.service.ForumService;
import com.lxj.chatForum.utils.ReturnData;
import com.lxj.chatForum.utils.SqlSessionUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ForumServiceImpl implements ForumService {

//    ForumDao forumMapper=new ForumDaoImpl();
    @Resource
    ForumMapper forumMapper;
    @Override
    public String getAllArticle() {
        Gson gson=new GsonBuilder().create();
        List<ForumPojo> forumPojoList=forumMapper.getAllForum();
        if(forumPojoList == null){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success(forumPojoList));
    }

    @Override
    public String getArticleBySortId(String json) {
        Gson gson=new GsonBuilder().create();
        Type type = new TypeToken<HashMap<String,String>>() {}.getType();
        Map<String,String> jsonMap = gson.fromJson(json, type);
        String sortId = jsonMap.get("sortId");

        List<ForumPojo> forumPojoList=forumMapper.getForumBySortId(sortId);
        if(forumPojoList == null){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success(forumPojoList));
    }

    @Override
    public String getArticleByUid(String json) {
        Gson gson=new GsonBuilder().create();
        Type typeC = new TypeToken<HashMap<String,String>>() {}.getType();
        Map<String,String> jsonMap = gson.fromJson(json, typeC);
        String uid = jsonMap.get("uid");
        String type=jsonMap.get("type");
        List<ForumPojo> forumPojoList=null;
        if(type.equals("All")){
                forumPojoList=forumMapper.getAllForumByUid(uid);
        }
        else if(type.equals("like")){
            forumPojoList=forumMapper.getLikeForumByUid(uid);
        }
        else  if(type.equals("collect")){
            forumPojoList=forumMapper.getCollectForum(uid);
        }

        if(forumPojoList == null){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success(forumPojoList));
    }

    @Override
    public String getOneArticle(String json) {
        Gson gson=new GsonBuilder().create();
        Type type = new TypeToken<HashMap<String,String>>() {}.getType();
        Map<String,String> jsonMap = gson.fromJson(json, type);
        String forumId = jsonMap.get("forumId");

        ForumPojo forumPojoList=forumMapper.getForumById(forumId);

        if(forumPojoList == null){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success(forumPojoList));
    }

    @Override
    public String toSend(String json) {
        Gson gson=new GsonBuilder().create();
        ForumPojo forum = gson.fromJson(json, ForumPojo.class);
        int count=forumMapper.addForum(forum);

        if(count == 0){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String toEdit(String json) {
        Gson gson=new GsonBuilder().create();
        ForumPojo forum = gson.fromJson(json, ForumPojo.class);
        System.out.println(forum.getTitle());
        int count=forumMapper.updateForum(forum);

        if(count == 0){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String toDelete(String json) {
        Gson gson=new GsonBuilder().create();
        Type typeC = new TypeToken<HashMap<String,String>>() {}.getType();
        Map<String,String> jsonMap = gson.fromJson(json, typeC);
        String id=jsonMap.get("id");
        int count=forumMapper.deleteForumById(id);

        if(count == -1){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String toGetForumsByLikesAndCollect() {

        Gson gson=new GsonBuilder().create();
        List<ForumPojo> forumPojoList=forumMapper.getForumsByLikesAndCollect();
        if(forumPojoList == null){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success(forumPojoList));

    }
}
