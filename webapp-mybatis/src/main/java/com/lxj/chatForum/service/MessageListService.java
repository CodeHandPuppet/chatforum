package com.lxj.chatForum.service;

public interface MessageListService {
    String getFriends(String token);

    String getChatList(String token);

    String getOneMessageArr(String json);

    String toSendMessage(String json);

    String toAddFriend(String json);
    String toEditAppellation(String json);
}
