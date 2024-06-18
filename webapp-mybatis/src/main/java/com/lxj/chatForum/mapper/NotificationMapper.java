package com.lxj.chatForum.mapper;

import com.lxj.chatForum.pojo.NotificationPojo;
import com.lxj.chatForum.pojo.UserPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotificationMapper {
    //获取管理员全部通知
    List<NotificationPojo> getAdminNotifications();

    // 获取 申请加uid的人的个人信息
    List<UserPojo> getFriendsUserInfo(String uid);

    //根据接收id和发送id删除数据
    int delete(@Param("receiverId") String receiverId,@Param("senderId") String senderId);

    //添加好友通知
    int addFriendNot(@Param("receiverId")String receiverId,@Param("senderId") String senderId);

    //根据id删除数据
    int deleteById(String id);

    //添加管理员通知
    int addAdminNot(NotificationPojo notification);

    //更新管理员发布的通知
    int updateAdminNot(NotificationPojo notification);
}
