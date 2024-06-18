package com.lxj.chatForum.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForumLikeOrCollectMapper {
    //    删除collent
    int deleteCollect(@Param("uid") String uid, @Param("forumId") String forumId);

    //插入收藏
    int insertCollect(@Param("uid") String uid, @Param("forumId") String forumId);

    //删除 喜欢
    int deleteLike(@Param("uid") String uid, @Param("forumId") String forumId);

    //插入喜欢
    int insertLike(@Param("uid") String uid, @Param("forumId") String forumId);

    //获取喜欢数组  用来进行点赞用的
    List<Integer> getAllLikeForumId(String uid);

    //收藏数组  用来收藏展示用的
    List<Integer> getAllCollectForumId(String uid);
}
