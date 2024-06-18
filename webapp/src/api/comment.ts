import request from "@/utils/request"
import { CommentDataResponseData, CommentLikesIdResponseData, SaveOrDeleteResponseData, VisComment } from "./type"
import { CommentData } from "@/type/websiteDataType";

enum API {
    // 通过文章获取 
    COMMENT_GETCOMMENTS = "comment/getComments",
    // 保存评论
    COMMENT_SAVE = "/comment/save",
    // 删除评论
    COMMENT_DELETE = "/comment/deleteById",
    // 获取自己点赞了的评论 id 数组
    COMMENTLIKE_GETLIKES = "commentLike/getLikes",
    // 点赞
    COMMENTLIKE_ADD = "/commentLike/toLike",
    // 取消点赞
    COMMENTLIKE_REMOVE = "/commentLike/delete"
}

// 获取某文章的全部配置
export const getCommentsRequest = (forumId: number | string) => {

    return request.post<any, CommentDataResponseData>(API.COMMENT_GETCOMMENTS, { forumId });

}
// 保存  评论
export const CommentSaveRequest = (comment: CommentData) => {

    const { uid, forumId, parentId, content } = comment;

    return request.post<any, SaveOrDeleteResponseData>(API.COMMENT_SAVE, { uid, forumId, parentId, content });
}
// 删除评论
export const CommentDeleteRequest = (id: number | string, parentId: string | null | number) => {
    return request.post<any, SaveOrDeleteResponseData>(API.COMMENT_DELETE, { id, parentId });
}

// 获得喜欢数组
export const getLikesRequest = (uid: string | number) => {
    return request.post<any, CommentLikesIdResponseData>(API.COMMENTLIKE_GETLIKES, { uid })
}

// 点赞
export const addCommentLikesRequest = (uid: string | number, commentId: string | number) => {
    return request.post<any, SaveOrDeleteResponseData>(API.COMMENTLIKE_ADD, { uid, commentId });
}

// 取消电站
export const removeCommentLikeRequest = (uid: string | number, commentId: string | number) => {
    return request.post<any, SaveOrDeleteResponseData>(API.COMMENTLIKE_REMOVE, { uid, commentId });
}