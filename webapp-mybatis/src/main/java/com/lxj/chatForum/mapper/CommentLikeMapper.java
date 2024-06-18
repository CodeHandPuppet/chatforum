package com.lxj.chatForum.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentLikeMapper {
    //获取评论的id  通过喜欢表的 uid
    List<Integer> getCommentIdByForumIdAndUid(String uid);

    //删除某一个喜欢值   通过  uid 和 commentId
    int deleteByUidAndCommentId(@Param("uid") String uid,@Param("commentId") String commentId);

    //插入
    int insertByUidAndCommentId(@Param("uid") String uid,@Param("commentId") String commentId);
}
