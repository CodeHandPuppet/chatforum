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
    public List<CommentPojo> getComments(String forumId) {

        List<CommentPojo> list=commentMapper.getCommentsByForumId(forumId);
        return list;
    }

    @Override
    public int getSave(CommentPojo comment) {

//        int id=commentMapper.insertReturnId(comment);
        commentMapper.insertReturnId(comment);


       return comment.getId();
    }

    @Override
    public void toDelete(CommentPojo comment) {

        String id = comment.getId().toString();
        String parentId = comment.getParentId();

        int count=commentMapper.deleteById(id,parentId);


    }
}
