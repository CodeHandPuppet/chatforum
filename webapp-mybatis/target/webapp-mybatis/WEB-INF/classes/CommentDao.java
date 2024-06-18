package com.lxj.chatForum.dao;

import com.lxj.chatForum.pojo.CommentPojo;

import java.util.List;

public interface CommentDao {
    List<CommentPojo> getCommentsByForumId(String forumId);
}
