package com.lxj.chatForum.mapper;

import com.lxj.chatForum.pojo.FriendsListPojo;
import com.lxj.chatForum.pojo.MessagePojo;
import com.lxj.chatForum.pojo.UserPojo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface MessageListMapper {
    //获取消息页  朋友列表
    List<UserPojo> getFriendMessageList(String uid);

    //获取朋友页  朋友列表
    List<FriendsListPojo> getChatList(String uid);

    //获取两个人的聊天对话
    List<MessagePojo> getOneMessageArr(@Param("uid") String uid,@Param("senderId") String senderId);

    //发送某个消息
    int insertMessage(MessagePojo message);

    //  添加一位朋友
//    int insertFriend(FriendsListPojo friends);
    int insertFriend(@Param("uid") int uid,@Param("friendId")  int friendId);
    //更新朋友的称呼
    int updateFriend(FriendsListPojo friends);
}
