package com.lxj.chatForum.dao;

import java.util.List;

public interface ForumLikeOrCollectDao {
    int deleteCollect(String uid, String forumId);

    int insertCollect(String uid, String forumId);

    int deleteLike(String uid, String forumId);

    int insertLike(String uid, String forumId);

    List<Integer> getAllLikeForumId(String uid);

    List<Integer> getAllCollectForumId(String uid);
}
