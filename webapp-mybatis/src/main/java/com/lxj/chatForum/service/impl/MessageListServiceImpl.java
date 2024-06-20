package com.lxj.chatForum.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lxj.chatForum.dao.UserDao;
import com.lxj.chatForum.dao.impl.UserDaoImpl;
import com.lxj.chatForum.mapper.MessageListMapper;
import com.lxj.chatForum.mapper.UserMapper;
import com.lxj.chatForum.pojo.FriendsListPojo;
import com.lxj.chatForum.pojo.MessagePojo;
import com.lxj.chatForum.pojo.UserPojo;
import com.lxj.chatForum.service.MessageListService;
import com.lxj.chatForum.utils.ReturnData;
import com.lxj.chatForum.utils.SqlSessionUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageListServiceImpl implements MessageListService {

//    UserDao userMapper = new UserDaoImpl();
//    MessageListDao messageListMapper=new MessageListDaoImpl();
    @Resource
    UserMapper userMapper;
    @Resource
    MessageListMapper messageListMapper;

    @Override
    public String getFriends(String token) {
        Gson gson = new GsonBuilder().create();

        String uid = userMapper.getIdBytoken(token);
//        HashMap<String,Object> map=messageListMapper.getFriendMessageList(uid);  // 老代码
        List<UserPojo> friendMessageList = messageListMapper.getFriendMessageList(uid);
        HashMap<String, Object> map = new HashMap();
        map.put("type", "friend");
        map.put("friends", friendMessageList);
        if (friendMessageList == null) {
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success(map));
    }

    @Override
    public String getChatList(String token) {
        Gson gson = new GsonBuilder().create();
        String uid = userMapper.getIdBytoken(token);
//        HashMap<String,Object> map=messageListMapper.getChatList(uid); //老代码
        List<FriendsListPojo> chatList = messageListMapper.getChatList(uid);
        HashMap<String, Object> map = new HashMap();
        map.put("friends", chatList);
        if (chatList == null) {
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success(map));
    }

    @Override
    public String getOneMessageArr(String json) {
        Gson gson = new GsonBuilder().create();
        Type typeC = new TypeToken<HashMap<String, String>>() {
        }.getType();
        Map<String, String> jsonMap = gson.fromJson(json, typeC);
        String uid = jsonMap.get("uid");
        String send_id = jsonMap.get("receiver_id");
        List<MessagePojo> list = messageListMapper.getOneMessageArr(uid, send_id);
        if (list == null) {
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success(list));
    }

    @Override
    public String toSendMessage(String json) {
        Gson gson = new GsonBuilder().create();
        MessagePojo messagePojo = gson.fromJson(json, MessagePojo.class);
        int count = messageListMapper.insertMessage(messagePojo);
        if (count == 0) {
            gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String toAddFriend(String json) {
        Gson gson = new GsonBuilder().create();
        FriendsListPojo friendsListPojo = gson.fromJson(json, FriendsListPojo.class);
//        int count = messageListMapper.insertFriend(friendsListPojo); //老代码
        int count=messageListMapper.insertFriend(friendsListPojo.getUid(),friendsListPojo.getFriend_id());
        if(count>0){
            count+=messageListMapper.insertFriend(friendsListPojo.getFriend_id(),friendsListPojo.getUid());
        }



        if (count == 0) {
            gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String toEditAppellation(String json) {
        Gson gson = new GsonBuilder().create();
        FriendsListPojo friendsListPojo = gson.fromJson(json, FriendsListPojo.class);
        int count = messageListMapper.updateFriend(friendsListPojo);
        if (count == 0) {
            gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }
}
