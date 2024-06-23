package com.lxj.chatForum.service.impl;


import com.lxj.chatForum.mapper.ForumMapper;
import com.lxj.chatForum.pojo.ForumPojo;
import com.lxj.chatForum.service.ForumService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class ForumServiceImpl implements ForumService {


    @Resource
    ForumMapper forumMapper;



    @Override
    public List<ForumPojo> getAllArticle() {

        List<ForumPojo> forumPojoList=forumMapper.getAllForum();
        return forumPojoList;
    }

    @Override
    public  List<ForumPojo> getArticleBySortId(String sortId) {


        List<ForumPojo> forumPojoList=forumMapper.getForumBySortId(sortId);
       return forumPojoList;
    }

    @Override
    public  List<ForumPojo> getArticleByUid(HashMap<String ,String> map) {

        String uid = map.get("uid");
        String type=map.get("type");
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

       return forumPojoList;
    }

    @Override
    public ForumPojo getOneArticle(String forumId) {



        ForumPojo forum=forumMapper.getForumById(forumId);

        return forum;

    }

    @Override
    public void toSend(ForumPojo forum) {
        int count=forumMapper.addForum(forum);

    }

    @Override
    public void toEdit(ForumPojo forum) {

        System.out.println(forum.getTitle());
        int count=forumMapper.updateForum(forum);

    }

    @Override
    public void toDelete(String id) {

        int count=forumMapper.deleteForumById(id);


    }

    @Override
    public List<ForumPojo> toGetForumsByLikesAndCollect() {

        List<ForumPojo> forumPojoList=forumMapper.getForumsByLikesAndCollect();

        return forumPojoList;

    }
}
