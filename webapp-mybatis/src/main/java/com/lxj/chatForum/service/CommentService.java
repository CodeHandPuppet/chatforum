package com.lxj.chatForum.service;

import com.lxj.chatForum.pojo.CommentPojo;

import java.util.List;
import java.util.Map;

public interface CommentService {

    List<CommentPojo> getComments(String forumId);


    Map<String,Integer> getSave(CommentPojo comment);


    void toDelete(CommentPojo comment);
}
