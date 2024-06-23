package com.lxj.chatForum.service.impl;


import com.lxj.chatForum.mapper.ForumLikeOrCollectMapper;
import com.lxj.chatForum.service.ForumLikeOrCollectService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ForumLikeOrCollectServiceImpl implements ForumLikeOrCollectService {

    @Resource
    ForumLikeOrCollectMapper forumLikeOrCollectMapper;



    @Override
    public void allTypeMethods(Map<String,String> jsonMap, String type) {


        String uid=jsonMap.get("uid");
        String forumId=jsonMap.get("forumId");
        int count=0;
       if(type.equals("likeAdd")){
           count= forumLikeOrCollectMapper.insertLike(uid,forumId);
       }
       else if(type.equals("likeRemove")){
           count= forumLikeOrCollectMapper.deleteLike(uid,forumId);
       }
       else if(type.equals("collectAdd")){
           count= forumLikeOrCollectMapper.insertCollect(uid,forumId);
       }
       else if(type.equals("collectRemove")){
           count=forumLikeOrCollectMapper.deleteCollect(uid,forumId);
       }


    }

    @Override
    public  HashMap<String,List<Integer>> allArrTypeMethods(Map<String,String> jsonMap, String type) {

        String uid=jsonMap.get("uid");
        List<Integer> list=null;
        if(type.equals("likeArr")){
            list=forumLikeOrCollectMapper.getAllLikeForumId(uid);
        }
        else if(type.equals("collectArr")){
            list=forumLikeOrCollectMapper.getAllCollectForumId(uid);
        }


        HashMap<String,List<Integer>> map=new HashMap<>();
        map.put("Ids",list);
        return map;
    }
}
