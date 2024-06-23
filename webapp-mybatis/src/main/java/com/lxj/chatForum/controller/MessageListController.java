package com.lxj.chatForum.controller;

import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.pojo.FriendsListPojo;
import com.lxj.chatForum.pojo.MessagePojo;
import com.lxj.chatForum.service.MessageListService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@WebServlet({"/message/getFriendMessageList","/message/getOneMessageArr",
// "/chat/getFriends","/message/send","/friends/add","/friends/editAppellation"})
@Controller
@ResponseBody
public class MessageListController extends HttpServlet {


    @Resource
    MessageListService messageListServiceImpl;

    @GetMapping("/message/getFriendMessageList")
    public ResponseResult getFriendMessageList(@RequestHeader("token")String token){

        Map<String, Object> friends = messageListServiceImpl.getFriends(token);

        return ResponseResult.success(friends);
    }

    @PostMapping("/message/getOneMessageArr")
    public ResponseResult getOneMessageArr(@RequestBody MessagePojo message) {
        List<MessagePojo> oneMessageArr = messageListServiceImpl.getOneMessageArr(message);
        return ResponseResult.success(oneMessageArr);
    }

    @GetMapping("/chat/getFriends")
    public ResponseResult getFriends(@RequestHeader("token")String token) {
        Map<String, Object> chatList = messageListServiceImpl.getChatList(token);
        return ResponseResult.success(chatList);
    }

    @PostMapping("/message/send")
    public ResponseResult send(@RequestBody MessagePojo message) {
        messageListServiceImpl.toSendMessage(message);
        return ResponseResult.success("ok");
    }

    @PostMapping("/friends/add")
    public ResponseResult add(@RequestBody FriendsListPojo friend) {
        messageListServiceImpl.toAddFriend(friend);
        return ResponseResult.success("ok");
    }

    @PostMapping("/friends/editAppellation")
    public ResponseResult editAppellation(@RequestBody FriendsListPojo friend) {
        messageListServiceImpl.toEditAppellation(friend);
        return ResponseResult.success("ok");
    }




}
