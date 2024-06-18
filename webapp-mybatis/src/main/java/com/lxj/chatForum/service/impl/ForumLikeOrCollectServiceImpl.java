package com.lxj.chatForum.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lxj.chatForum.dao.ForumLikeOrCollectDao;
import com.lxj.chatForum.dao.impl.ForumLikeOrCollectDaoImpl;
import com.lxj.chatForum.mapper.ForumLikeOrCollectMapper;
import com.lxj.chatForum.service.ForumLikeOrCollectService;
import com.lxj.chatForum.utils.ReturnData;
import com.lxj.chatForum.utils.SqlSessionUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForumLikeOrCollectServiceImpl implements ForumLikeOrCollectService {
//    ForumLikeOrCollectDao forumLikeOrCollectDao=new ForumLikeOrCollectDaoImpl();
    ForumLikeOrCollectMapper forumLikeOrCollectDao= SqlSessionUtils.openSession().getMapper(ForumLikeOrCollectMapper.class);

    Gson gson=new GsonBuilder().create();
    Type typeC = new TypeToken<HashMap<String,String>>() {}.getType();
    @Override
    public String allTypeMethods(String json, String type) {

        Map<String,String> jsonMap = gson.fromJson(json, typeC);
        String uid=jsonMap.get("uid");
        String forumId=jsonMap.get("forumId");
        int count=0;
       if(type.equals("likeAdd")){
           count= forumLikeOrCollectDao.insertLike(uid,forumId);
       }
       else if(type.equals("likeRemove")){
           count= forumLikeOrCollectDao.deleteLike(uid,forumId);
       }
       else if(type.equals("collectAdd")){
           count= forumLikeOrCollectDao.insertCollect(uid,forumId);
       }
       else if(type.equals("collectRemove")){
           count=forumLikeOrCollectDao.deleteCollect(uid,forumId);
       }

        if(count==0){
            return gson.toJson(ReturnData.error(null)) ;
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String allArrTypeMethods(String json, String type) {
        Map<String,String> jsonMap = gson.fromJson(json, typeC);
        String uid=jsonMap.get("uid");
        List<Integer> list=null;
        if(type.equals("likeArr")){
            list=forumLikeOrCollectDao.getAllLikeForumId(uid);
        }
        else if(type.equals("collectArr")){
            list=forumLikeOrCollectDao.getAllCollectForumId(uid);
        }

        if(list==null){
            return gson.toJson(ReturnData.error(null)) ;
        }
        HashMap<String,List<Integer>> map=new HashMap<>();
        map.put("Ids",list);
        return gson.toJson(ReturnData.success(map));
    }
}
