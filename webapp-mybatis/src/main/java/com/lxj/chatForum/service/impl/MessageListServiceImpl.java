package com.lxj.chatForum.service.impl;

import com.lxj.chatForum.mapper.MessageListMapper;
import com.lxj.chatForum.mapper.UserMapper;
import com.lxj.chatForum.pojo.FriendsListPojo;
import com.lxj.chatForum.pojo.MessagePojo;
import com.lxj.chatForum.pojo.UserPojo;
import com.lxj.chatForum.service.MessageListService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageListServiceImpl implements MessageListService {


    @Resource
    UserMapper userMapper;
    @Resource
    MessageListMapper messageListMapper;

    @Override
    public Map<String,Object> getFriends(String token) {


        String uid = userMapper.getIdBytoken(token);

        List<UserPojo> friendMessageList = messageListMapper.getFriendMessageList(uid);

        HashMap<String, Object> map = new HashMap();
        map.put("type", "friend");
        map.put("friends", friendMessageList);

       return map;
    }

    @Override
    public Map<String,Object>  getChatList(String token) {

        String uid = userMapper.getIdBytoken(token);

        List<FriendsListPojo> chatList = messageListMapper.getChatList(uid);
        HashMap<String, Object> map = new HashMap();
        map.put("friends", chatList);
        return map;
    }

    @Override
    public  List<MessagePojo> getOneMessageArr(MessagePojo message) {

        String uid = message.getUid()+"";
        String send_id = message.getReceiverId()+"";
        List<MessagePojo> list = messageListMapper.getOneMessageArr(uid, send_id);

        return list;
    }

    @Override
    public void toSendMessage(MessagePojo message) {

        int count = messageListMapper.insertMessage(message);


    }

    @Override
    public void toAddFriend(FriendsListPojo friendsListPojo) {


        int count=messageListMapper.insertFriend(friendsListPojo.getUid(),friendsListPojo.getFriendId());
        if(count>0){
            count+=messageListMapper.insertFriend(friendsListPojo.getFriendId(),friendsListPojo.getUid());
        }



    }

    @Override
    public void toEditAppellation(FriendsListPojo friendsListPojo) {

        int count = messageListMapper.updateFriend(friendsListPojo);

    }
}
