import request from "@/utils/request"
import { SaveOrDeleteResponseData, VisForum, VisForumResponseData } from "./type";



enum API {
    // 获取某一篇文章
    FORUM_GETONEARTICLE = "forum/getOneArticle",
    // 返回全部的论坛文章的列表   /forum/getAllArticle                
    FORUM_ALLARTICLE = 'forum/getAllArticle',
    // 根据分类返回论坛文章列表  /forum/getArticleBySort             data:sort_id
    FORUM_ALLARTICLEBYSORT = 'forum/getArticleBySortId',
    // 通过 的uid ,返回 某人 的全部文章  /forum/getArticleByUid      data:uid   type:all,collect,like
    FORUM_USERARTICLE = 'forum/getArticleByUid',
    // 编辑文章    /forum/edit                  data:forum
    FORUM_EDIT = '/forum/edit',
    // 保存文章    /forum/save                  data:forum
    FORUM_SAVE = '/forum/send',
    // 删除文章    /forum/delete/                  data:forumId
    FORUM_DELETE = '/forum/deleteById',
    // 获得热度文章
    FORUM_LIKANDFORUM = '/forum/getForumsByLikesAndCollect'
}

// 获取某一篇文章
export const getOneArticleRequest = (forumId: number | string) => {
    return request.post<any, VisForumResponseData>(API.FORUM_GETONEARTICLE, { forumId });
}

// 获取所有文章

export const getAllArticleRequest = () => {
    return request.get<any, VisForumResponseData>(API.FORUM_ALLARTICLE);
}

// 通过分类获得该分类的所有文章
export const getArticleBySortRequest = (sortId: string | number) => {
    return request.post<any, VisForumResponseData>(API.FORUM_ALLARTICLEBYSORT, { sortId });
}

// 通过uid获得该用户的 收藏 ，所有 ，喜欢 的文章
export const getArticleByUidRequest = (type: "collect" | "All" | "like", uid: number | string) => {
    return request.post<any, VisForumResponseData>(API.FORUM_USERARTICLE, { type, uid });
}

// 编辑该文章
export const editArticleRequest = (forum: VisForum) => {
    const { title, content, intro, img, sortId, id, uid } = forum;
    return request.post<any, SaveOrDeleteResponseData>(API.FORUM_EDIT, { title, content, intro, img, sortId, id, uid });
}

// 发送文章
export const saveArticleRequest = (forum: VisForum) => {
    const { title, content, intro, img, sortId, uid } = forum;
    return request.post<any, SaveOrDeleteResponseData>(API.FORUM_SAVE, { uid, title, content, intro, img, sortId });
}

// 删除文章

export const deleteArticleRequest = (id: number | string) => {
    return request.post<any, SaveOrDeleteResponseData>(API.FORUM_DELETE, { id });
}

// 获得热度文章
export const getForumsByLikAndCotRequest = () => {
    return request.get<any, VisForumResponseData>(API.FORUM_LIKANDFORUM);
}