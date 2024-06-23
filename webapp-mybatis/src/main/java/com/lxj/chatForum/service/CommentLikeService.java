package com.lxj.chatForum.service;

import com.lxj.chatForum.pojo.CommentLikePojo;

import java.util.List;

public interface CommentLikeService {


    List<Integer> getLikeArr(String uid);


    void addCommentLike(CommentLikePojo commentLike);


    void  deleteCommentLike(CommentLikePojo commentLike);
}
