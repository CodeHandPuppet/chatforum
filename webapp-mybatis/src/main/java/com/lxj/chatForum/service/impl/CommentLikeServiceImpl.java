package com.lxj.chatForum.service.impl;


import com.lxj.chatForum.mapper.CommentLikeMapper;
import com.lxj.chatForum.pojo.CommentLikePojo;
import com.lxj.chatForum.service.CommentLikeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CommentLikeServiceImpl implements CommentLikeService {



    @Resource
    CommentLikeMapper commentLikeMapper;



    @Override
    public List<Integer> getLikeArr(String uid) {

        List<Integer> list = commentLikeMapper.getCommentIdByForumIdAndUid(uid);

        return list;
    }


    @Override
    public void addCommentLike(CommentLikePojo commentLike) {

        String uid = commentLike.getUid();
        String commentId = commentLike.getCommentId();
        commentLikeMapper.insertByUidAndCommentId(uid, commentId);

    }



    @Override
    public void deleteCommentLike(CommentLikePojo commentLike) {
        String uid = commentLike.getUid();
        String commentId = commentLike.getCommentId();
        commentLikeMapper.deleteByUidAndCommentId(uid, commentId);


    }

}
