package com.lxj.chatForum.service;

import com.lxj.chatForum.pojo.CommentPojo;

import java.util.List;

public interface CommentService {
    List<CommentPojo> getComments(String forumId);

    int getSave(CommentPojo comment);

    void toDelete(CommentPojo comment);
}
