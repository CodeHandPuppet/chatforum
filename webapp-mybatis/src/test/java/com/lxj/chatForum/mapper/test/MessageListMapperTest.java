package com.lxj.chatForum.mapper.test;

import com.lxj.chatForum.mapper.MessageListMapper;
import com.lxj.chatForum.pojo.FriendsListPojo;
import com.lxj.chatForum.utils.SqlSessionUtils;
import org.junit.Test;

import java.util.List;

public class MessageListMapperTest {
    @Test
    public void test(){
        MessageListMapper mapper = SqlSessionUtils.openSession().getMapper(MessageListMapper.class);
        List<FriendsListPojo> chatList = mapper.getChatList("1");
        System.out.println(chatList);
    }
}
