package com.lxj.chatForum.controller;

import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.service.MessageListService;
import com.lxj.chatForum.service.impl.MessageListServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

//@WebServlet({"/message/getFriendMessageList","/message/getOneMessageArr",
// "/chat/getFriends","/message/send","/friends/add","/friends/editAppellation"})
@Controller
public class MessageListController extends HttpServlet {
    @PostMapping("/message/getFriendMessageList")
    public ResponseResult getFriendMessageList() {
        
        return null;
    }

    @PostMapping("/message/getOneMessageArr")
    public ResponseResult getOneMessageArr() {
        
        return null;
    }

    @PostMapping("/chat/getFriends")
    public ResponseResult getFriends() {
        
        return null;
    }

    @PostMapping("/message/send")
    public ResponseResult send() {
        
        return null;
    }

    @PostMapping("/friends/add")
    public ResponseResult add() {
        
        return null;
    }

    @PostMapping("/friends/editAppellation")
    public ResponseResult editAppellation() {
        
        return null;
    }




}
