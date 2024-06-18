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

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageListServiceImpl implements MessageListService {

//    UserDao userDao = new UserDaoImpl();
//    MessageListDao messageListDao=new MessageListDaoImpl();
    UserMapper userDao= SqlSessionUtils.openSession().getMapper(UserMapper.class);
    MessageListMapper messageListDao = SqlSessionUtils.openSession().getMapper(MessageListMapper.class);

    @Override
    public String getFriends(String token) {
        Gson gson = new GsonBuilder().create();

        String uid = userDao.getIdBytoken(token);
//        HashMap<String,Object> map=messageListDao.getFriendMessageList(uid);  // 老代码
        List<UserPojo> friendMessageList = messageListDao.getFriendMessageList(uid);
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
        String uid = userDao.getIdBytoken(token);
//        HashMap<String,Object> map=messageListDao.getChatList(uid); //老代码
        List<FriendsListPojo> chatList = messageListDao.getChatList(uid);
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
        List<MessagePojo> list = messageListDao.getOneMessageArr(uid, send_id);
        if (list == null) {
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success(list));
    }

    @Override
    public String toSendMessage(String json) {
        Gson gson = new GsonBuilder().create();
        MessagePojo messagePojo = gson.fromJson(json, MessagePojo.class);
        int count = messageListDao.insertMessage(messagePojo);
        if (count == 0) {
            gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String toAddFriend(String json) {
        Gson gson = new GsonBuilder().create();
        FriendsListPojo friendsListPojo = gson.fromJson(json, FriendsListPojo.class);
//        int count = messageListDao.insertFriend(friendsListPojo); //老代码
        int count=messageListDao.insertFriend(friendsListPojo.getUid(),friendsListPojo.getFriend_id());
        if(count>0){
            count+=messageListDao.insertFriend(friendsListPojo.getFriend_id(),friendsListPojo.getUid());
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
        int count = messageListDao.updateFriend(friendsListPojo);
        if (count == 0) {
            gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }
}
