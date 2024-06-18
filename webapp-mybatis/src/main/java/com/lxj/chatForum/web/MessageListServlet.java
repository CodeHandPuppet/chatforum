package com.lxj.chatForum.web;

import com.lxj.chatForum.service.MessageListService;
import com.lxj.chatForum.service.impl.MessageListServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/message/getFriendMessageList","/message/getOneMessageArr","/chat/getFriends","/message/send","/friends/add","/friends/editAppellation"})
public class MessageListServlet extends HttpServlet {

    MessageListService messageListService=new MessageListServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if(servletPath.equals("/message/getFriendMessageList")){
            doGetFriendMessageList(request,response);
        }
        else if(servletPath.equals("/message/getOneMessageArr")){
            doGetOneMessageArr(request,response);
        }
        else  if(servletPath.equals("/chat/getFriends")){
            doGetChatList(request,response);
        }
        else if(servletPath.equals("/message/send")){
            doSendMessage(request,response);
        }
        else if(servletPath.equals("/friends/add")){
            doAddFriends(request,response);
        }
        else if(servletPath.equals("/friends/editAppellation")){
            doEditAppellation(request,response);
        }
    }
//修改备注
    private void doEditAppellation(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=messageListService.toEditAppellation(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //  添加好友
    private void doAddFriends(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=messageListService.toAddFriend(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //    发送消息
    private void doSendMessage(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=messageListService.toSendMessage(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //  好友列表
    private void doGetChatList(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        String returnData=messageListService.getChatList(token);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//  消息列表
    private void doGetFriendMessageList(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        String returnData=messageListService.getFriends(token);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//  获取某两个人的对话
    private void doGetOneMessageArr(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=messageListService.getOneMessageArr(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
