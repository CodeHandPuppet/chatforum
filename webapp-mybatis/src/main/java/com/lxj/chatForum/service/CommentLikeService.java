package com.lxj.chatForum.service;

public interface CommentLikeService {
    String getLikeArr(String json);
    String addCommentLike(String json);
    String deleteCommentLike(String json);
}
