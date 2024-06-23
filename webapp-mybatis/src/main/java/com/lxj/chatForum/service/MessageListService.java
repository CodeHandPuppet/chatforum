package com.lxj.chatForum.service;

import com.lxj.chatForum.pojo.FriendsListPojo;
import com.lxj.chatForum.pojo.MessagePojo;

import java.util.List;
import java.util.Map;

public interface MessageListService {

    Map<String, Object> getFriends(String token);

    Map<String, Object> getChatList(String token);

    List<MessagePojo> getOneMessageArr(MessagePojo message);

    void toSendMessage(MessagePojo message);

    void toAddFriend(FriendsListPojo friendsListPojo);

    void toEditAppellation(FriendsListPojo friendsListPojo);
}
