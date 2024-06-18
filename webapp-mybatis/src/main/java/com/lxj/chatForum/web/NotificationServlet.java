package com.lxj.chatForum.web;

import com.lxj.chatForum.service.NotificationService;
import com.lxj.chatForum.service.impl.NotificationServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet({"/notification/getAdminNot","/notification/getFriendNot","/notification/delFriendNot"
//        ,"/notification/addFriendNot","/notification/removeAdminNot","/notification/addAdminNot","/notification/editAdminNot"})
public class NotificationServlet extends HttpServlet {

    NotificationService notificationService=new NotificationServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if(servletPath.equals("/notification/getAdminNot")){
            doGetAdminNotification(request,response);
        }
        else if(servletPath.equals("/notification/getFriendNot")){
            doGetFriendNotification(request,response);
        }
        else if(servletPath.equals("/notification/delFriendNot")){
            doDeleteFriendNotification(request,response);
        }
        else if(servletPath.equals("/notification/addFriendNot")){
            doAddFriendNotification(request,response);
        }
        else if(servletPath.equals("/notification/addAdminNot")){
            toAddAdminNotification(request,response);
        }
        else if(servletPath.equals("/notification/removeAdminNot")){
            toRemoveAdminNotification(request,response);
        }
        else if(servletPath.equals("/notification/editAdminNot")){
            toEditAdminNotification(request,response);
        }
    }

    private void toRemoveAdminNotification(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=notificationService.toRemoveAdminNotifications(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toEditAdminNotification(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=notificationService.toEditAdminNotifications(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toAddAdminNotification(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=notificationService.toAddAdminNotifications(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void doAddFriendNotification(HttpServletRequest request, HttpServletResponse response) {

        String json = ReadJsonUtils.readJson(request);
        String returnData=notificationService.toAddFriendNotifications(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void doDeleteFriendNotification(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=notificationService.toDeleteFriendNotifications(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void doGetFriendNotification(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=notificationService.toGetFriendNotifications(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doGetAdminNotification(HttpServletRequest request, HttpServletResponse response) {


        String returnData=notificationService.toGetAllAdminNotifications();
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
