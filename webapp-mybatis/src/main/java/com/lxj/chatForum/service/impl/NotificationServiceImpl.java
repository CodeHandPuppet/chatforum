package com.lxj.chatForum.service.impl;


import com.lxj.chatForum.mapper.NotificationMapper;
import com.lxj.chatForum.pojo.NotificationPojo;
import com.lxj.chatForum.pojo.UserPojo;
import com.lxj.chatForum.service.NotificationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class NotificationServiceImpl implements NotificationService {

    
    @Resource
    NotificationMapper notificationMapper;


    @Override
    public List<NotificationPojo> toGetAllAdminNotifications() {

        List<NotificationPojo> list=notificationMapper.getAdminNotifications();

        return list;

    }

    @Override
    public List<UserPojo> toGetFriendNotifications(String uid) {

        List<UserPojo> list=notificationMapper.getFriendsUserInfo(uid);


        return list;
    }

    @Override
    public void toDeleteFriendNotifications(NotificationPojo notificationPojo) {

        Integer receiverId = notificationPojo.getReceiverId();
        Integer senderId = notificationPojo.getSenderId();
        int count = notificationMapper.delete(receiverId+"", senderId+"");


    }

    @Override
    public void toAddFriendNotifications(NotificationPojo notificationPojo) {
        Integer receiverId = notificationPojo.getReceiverId();
        Integer senderId = notificationPojo.getSenderId();
        int count = notificationMapper.addFriendNot(receiverId+"", senderId+"");

    }

    @Override
    public void toEditAdminNotifications(NotificationPojo notificationPojo) {

        int count=notificationMapper.updateAdminNot(notificationPojo);

    }

    @Override
    public void toRemoveAdminNotifications(String id) {

        int count = notificationMapper.deleteById(id);

    }

    @Override
    public void toAddAdminNotifications(NotificationPojo notificationPojo) {


        int count=notificationMapper.addAdminNot(notificationPojo);

    }
}
