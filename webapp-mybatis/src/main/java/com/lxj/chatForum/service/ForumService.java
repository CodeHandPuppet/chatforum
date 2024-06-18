package com.lxj.chatForum.service;

public interface ForumService {
    String getAllArticle();

    String getArticleBySortId(String json);

    String getArticleByUid(String json);

    String getOneArticle(String json);

    String toSend(String json);
    String toEdit(String json);
    String toDelete(String json);

    String toGetForumsByLikesAndCollect();

}
