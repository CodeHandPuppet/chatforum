package com.lxj.chatForum.service;

public interface NotificationService {

    String toGetAllAdminNotifications( );
    String toGetFriendNotifications(String json);
    String toDeleteFriendNotifications(String json);
    String toAddFriendNotifications(String json);
    String toEditAdminNotifications(String json);
    String toRemoveAdminNotifications(String json);
    String toAddAdminNotifications(String json);
}
