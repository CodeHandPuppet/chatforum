package com.lxj.chatForum.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lxj.chatForum.dao.NotificationDao;
import com.lxj.chatForum.dao.impl.NotificationDaoImpl;
import com.lxj.chatForum.mapper.NotificationMapper;
import com.lxj.chatForum.pojo.ForumPojo;
import com.lxj.chatForum.pojo.NotificationPojo;
import com.lxj.chatForum.pojo.UserPojo;
import com.lxj.chatForum.service.NotificationService;
import com.lxj.chatForum.utils.ReturnData;
import com.lxj.chatForum.utils.SqlSessionUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationServiceImpl implements NotificationService {
//    NotificationDao notificationDao=new NotificationDaoImpl();

    NotificationMapper notificationDao= SqlSessionUtils.openSession().getMapper(NotificationMapper.class);
    Gson gson=new GsonBuilder().create();
    @Override
    public String toGetAllAdminNotifications() {

        List<NotificationPojo> list=notificationDao.getAdminNotifications();
        if(list == null){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success(list));

    }

    @Override
    public String toGetFriendNotifications(String json) {
        Type type = new TypeToken<HashMap<String,String>>() {}.getType();
        Map<String,String> jsonMap = gson.fromJson(json, type);
        String uid = jsonMap.get("uid");
        List<UserPojo> list=notificationDao.getFriendsUserInfo(uid);
        if(list == null){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success(list));
    }

    @Override
    public String toDeleteFriendNotifications(String json) {

        Type type = new TypeToken<HashMap<String,String>>() {}.getType();
        Map<String,String> jsonMap = gson.fromJson(json, type);
        String receiverId = jsonMap.get("receiver_id");
        String senderId=jsonMap.get("sender_id");
        int count = notificationDao.delete(receiverId, senderId);
        if(count==0){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));

    }

    @Override
    public String toAddFriendNotifications(String json) {
        Type type = new TypeToken<HashMap<String,String>>() {}.getType();
        Map<String,String> jsonMap = gson.fromJson(json, type);
        String receiverId = jsonMap.get("receiver_id");
        String senderId=jsonMap.get("sender_id");
        int count = notificationDao.addFriendNot(receiverId, senderId);
        if(count==0){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String toEditAdminNotifications(String json) {
        NotificationPojo notificationPojo = gson.fromJson(json, NotificationPojo.class);
        int count=notificationDao.updateAdminNot(notificationPojo);
        if(count==0){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String toRemoveAdminNotifications(String json) {
        Gson gson=new GsonBuilder().create();
        Type type = new TypeToken<HashMap<String,String>>() {}.getType();
        Map<String,String> jsonMap = gson.fromJson(json, type);
        String id = jsonMap.get("id");
        int count = notificationDao.deleteById(id);
        if(count==0){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String toAddAdminNotifications(String json) {

        NotificationPojo notificationPojo = gson.fromJson(json, NotificationPojo.class);
        int count=notificationDao.addAdminNot(notificationPojo);
        if(count==0){
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }
}
