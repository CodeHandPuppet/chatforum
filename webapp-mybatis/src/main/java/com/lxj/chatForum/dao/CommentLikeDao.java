package com.lxj.chatForum.dao;

import com.lxj.chatForum.pojo.CommentPojo;

import java.util.List;

public interface CommentLikeDao {

    List<Integer> getCommentIdByForumIdAndUid( String uid);
    int deleteByUidAndCommentId(String uid,String commentId);
    int insertByUidAndCommentId(String uid,String commentId);


}
