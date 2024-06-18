import request from "@/utils/request"
import { CommentLikeOrCollectIdResponseData, SaveOrDeleteResponseData } from "./type"

enum API {
    // 获取点赞数组
    FORUMLIKE_ARR = "/forumLike/getArr",
    // 获取收藏数组
    FORUMCOLLECT_ARR = "/forumCollect/getArr",
    // 点赞
    FORUMLIKE_ADD = "/forumLike/add",
    // 取消点赞
    FORUMLIKE_REOMOVE = "/forumLike/remove",
    // 收藏
    FORUMCOLLECT_ADD = "/forumCollect/add",
    // 取消收藏
    FORUMCOLLECT_REMOVE = "/forumCollect/remove",
}

// 获取点赞数组
export const getArrForumLikeRequest = (uid: number | string) => {
    return request.post<any, CommentLikeOrCollectIdResponseData>(API.FORUMLIKE_ARR, { uid });
}
// 获取收藏数组
export const getArrForumCollectRequest = (uid: number | string) => {
    return request.post<any, CommentLikeOrCollectIdResponseData>(API.FORUMCOLLECT_ARR, { uid });
}
// 点赞
export const addForumLikeRequest = (uid: string | number, forumId: string | number) => {
    return request.post<any, SaveOrDeleteResponseData>(API.FORUMLIKE_ADD, { uid, forumId });
}
// 取消点赞
export const removeForumLikeRequest = (uid: string | number, forumId: string | number) => {
    return request.post<any, SaveOrDeleteResponseData>(API.FORUMLIKE_REOMOVE, { uid, forumId });
}
// 收藏
export const addForumCollectRequest = (uid: string | number, forumId: string | number) => {
    return request.post<any, SaveOrDeleteResponseData>(API.FORUMCOLLECT_ADD, { uid, forumId });
}
// 取消收藏
export const removeForumCollectRequest = (uid: string | number, forumId: string | number) => {
    return request.post<any, SaveOrDeleteResponseData>(API.FORUMCOLLECT_REMOVE, { uid, forumId });
}