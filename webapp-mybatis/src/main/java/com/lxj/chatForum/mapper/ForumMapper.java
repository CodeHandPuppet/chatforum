package com.lxj.chatForum.mapper;

import com.lxj.chatForum.pojo.ForumPojo;

import java.util.List;

public interface ForumMapper {
    // 获取全部论坛文章
    List<ForumPojo> getAllForum();

    //根据 sortId 获取 论坛文章
    List<ForumPojo> getForumBySortId(String sortId);

    //根据 uid  获取全部文章
    List<ForumPojo> getAllForumByUid(String uid);

    //根据
    List<ForumPojo> getLikeForumByUid(String uid);

    //
    List<ForumPojo> getCollectForum(String uid);

    //通过 id 获取 文章
    ForumPojo getForumById(String forumId);

    // 更新forum
    int updateForum(ForumPojo forum);

    //添加forum
    int addForum(ForumPojo forum);

    //删除forum
    int deleteForumById(String id);


    //获得按照热度排序
    List<ForumPojo> getForumsByLikesAndCollect();
}
