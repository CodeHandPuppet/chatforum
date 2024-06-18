package com.lxj.chatForum.dao;

import com.lxj.chatForum.pojo.ForumPojo;

import java.util.List;

public interface ForumDao {

    List<ForumPojo> getAllForum();
    List<ForumPojo> getForumBySortId(String sortId);
    List<ForumPojo> getAllForumByUid(String uid);
    List<ForumPojo> getLikeForumByUid(String uid);
    List<ForumPojo> getCollectForum(String uid);
    ForumPojo getForumById(String forumId);
    int updateForum(ForumPojo forum);
    int addForum(ForumPojo forum);
    int deleteForumById(String id);
}
