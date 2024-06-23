package com.lxj.chatForum.service.impl;


import com.lxj.chatForum.mapper.CommentMapper;
import com.lxj.chatForum.pojo.CommentPojo;
import com.lxj.chatForum.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {


    @Resource
    CommentMapper commentMapper;

    @Override
    public List<CommentPojo> getComments(String forumId) {

        List<CommentPojo> list=commentMapper.getCommentsByForumId(forumId);
        return list;
    }

    @Override
    public Map<String, Integer> getSave(CommentPojo comment) {


        commentMapper.insertReturnId(comment);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("id", comment.getId());

        return map;
    }

    @Override
    public void toDelete(CommentPojo comment) {

        String id = comment.getId().toString();
        String parentId = comment.getParentId();

        int count=commentMapper.deleteById(id,parentId);


    }
}
