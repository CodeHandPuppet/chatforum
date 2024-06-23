package com.lxj.chatForum.service;

import com.lxj.chatForum.pojo.ForumPojo;

import java.util.HashMap;
import java.util.List;

public interface ForumService {
    /**
     * 获取全部文章
     * @return  List<ForumPojo>
     */
    List<ForumPojo> getAllArticle();

    /**
     * 通过id返回文章列表
     * @param sortId
     * @return List<ForumPojo>
     */
    List<ForumPojo> getArticleBySortId(String sortId);

    /**
     * 获取某人 某类的 的全部文章
     * @param map
     * @return
     */
    List<ForumPojo> getArticleByUid(HashMap<String ,String> map);

    /**
     * 获取某一篇文章根据id
     * @param forumId
     * @return
     */
    ForumPojo getOneArticle(String forumId);

    /**
     * 发表文章
     * @param forum
     */
    void toSend(ForumPojo forum);

    /**
     *编辑保存文章
     * @param forum
     */
    void toEdit(ForumPojo forum);

    /**
     * 根据id删除forum文章
     * @param id
     */
    void toDelete(String id);

    /**
     * 根据 like 和 collect 获取热点信息
     * @return
     */
    List<ForumPojo> toGetForumsByLikesAndCollect();

}
