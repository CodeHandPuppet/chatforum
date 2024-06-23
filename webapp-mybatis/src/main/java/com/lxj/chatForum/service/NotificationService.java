package com.lxj.chatForum.service;

import com.lxj.chatForum.pojo.NotificationPojo;
import com.lxj.chatForum.pojo.UserPojo;

import java.util.List;

public interface NotificationService {

    List<NotificationPojo> toGetAllAdminNotifications( );

    List<UserPojo> toGetFriendNotifications(String uid);

    void toDeleteFriendNotifications(NotificationPojo notificationPojo);

    void toAddFriendNotifications(NotificationPojo notificationPojo);

    void toEditAdminNotifications(NotificationPojo notificationPojo);

    void toRemoveAdminNotifications(String id);

    void toAddAdminNotifications(NotificationPojo notificationPojo);
}
