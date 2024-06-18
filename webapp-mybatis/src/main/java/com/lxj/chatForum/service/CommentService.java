package com.lxj.chatForum.service;

public interface CommentService {
    String getComments(String json);

    String getSave(String json);

    String toDelete(String json);
}
