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
