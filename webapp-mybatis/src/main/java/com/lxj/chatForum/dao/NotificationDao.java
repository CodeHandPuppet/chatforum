package com.lxj.chatForum.dao;

import com.lxj.chatForum.pojo.NotificationPojo;
import com.lxj.chatForum.pojo.UserPojo;

import java.util.List;

public interface NotificationDao {
    List<NotificationPojo> getAdminNotifications();
    List<UserPojo> getFriendsUserInfo(String uid);
    int delete(String receiverId, String senderId);
    int addFriendNot(String receiverId, String senderId);
    int deleteById(String id);
    int addAdminNot(NotificationPojo notification);
    int updateAdminNot(NotificationPojo notification);
}
