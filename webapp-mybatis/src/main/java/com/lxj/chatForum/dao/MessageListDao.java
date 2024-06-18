package com.lxj.chatForum.dao;

import com.lxj.chatForum.pojo.FriendsListPojo;
import com.lxj.chatForum.pojo.MessagePojo;

import java.util.HashMap;
import java.util.List;

public interface MessageListDao {
    HashMap<String,Object>getFriendMessageList(String uid);

    HashMap<String, Object> getChatList(String uid);

    List<MessagePojo> getOneMessageArr(String uid, String sendId);

    int insertMessage(MessagePojo message);
    int insertFriend(FriendsListPojo friends);
    int updateFriend(FriendsListPojo friends);
}
