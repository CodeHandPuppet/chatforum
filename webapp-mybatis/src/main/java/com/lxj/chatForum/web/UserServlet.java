package com.lxj.chatForum.web;

import com.google.gson.Gson;
import com.lxj.chatForum.service.impl.UserServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

//@WebServlet({"/user/login","/user/getInfo","/user/getInfoByUid","/user/getAllUser","/user/add","/user/edit","/user/editPassword"})
public class UserServlet extends HttpServlet {

    UserServiceImpl userService=new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        获取请求的是哪个 servlet
        String servletPath = request.getServletPath();

        if(servletPath.equals("/user/login")){
            doLogin(request,response);
        }
        else if(servletPath.equals("/user/getInfo")){
            doGetInfo(request,response);
        }
        else if (servletPath.equals("/user/getInfoByUid")) {
            doGetInfoByUid(request,response);
        }
        else if (servletPath.equals("/user/edit")){
                toEditUser(request,response);
        }
        else if(servletPath.equals("/user/add")){
            toAddUser(request,response);
        }
        else if(servletPath.equals("/user/getAllUser")){
            doGetUsers(request,response);
        }
        else if(servletPath.equals("/user/editPassword")){
            doEditPassword(request,response);
        }
    }

    private void doEditPassword(HttpServletRequest request, HttpServletResponse response) {

        String json = ReadJsonUtils.readJson(request);
//        通过 id返回用户的基本信息的 json
        String token=request.getHeader("token");
        String returnData=userService.toEditPassword(json, token);
        System.out.println(returnData);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void toEditUser(HttpServletRequest request, HttpServletResponse response) {

        String json = ReadJsonUtils.readJson(request);
//        通过 id返回用户的基本信息的 json
        String returnData=userService.toEditUser(json);
        System.out.println(returnData);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void toAddUser(HttpServletRequest request, HttpServletResponse response) {
        //        获取传递过来的json数据  {id: }
        String json = ReadJsonUtils.readJson(request);
//        通过 id返回用户的基本信息的 json
        String returnData=userService.toAddUser(json);
        System.out.println(returnData);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doGetUsers(HttpServletRequest request, HttpServletResponse response) {

        String returnData=userService.toGetAllUser();
        System.out.println(returnData);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @method: post
     * @path: /user/login
     * @data: {username:  string , password:string }
     * @contentType:application/json
     * @return: token:
     */
    private void doLogin(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData = userService.login(json);
        System.out.println(returnData);
//        username    password  找到对应的用户返回token
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @method: get
     * @path: /user/getInfo
     * @header : token:
     * @return : UserPojo.createUserInfo
     */
    private void doGetInfo(HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader("token");
//        需要通过token找到用户信息的service
        String returnData=userService.getInfoByToken(token);
        System.out.println(returnData);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * @method: post
     * @path: /user/getInfoByUid
     * @data: id:uid
     * @contentType:application/json
     * @return: UserPojo.createUserInfo
     */
    private void doGetInfoByUid(HttpServletRequest request, HttpServletResponse response) {
//        获取传递过来的json数据  {id: }
        String json = ReadJsonUtils.readJson(request);
//        通过 id返回用户的基本信息的 json
        String returnData=userService.getInfoByUid(json);
        System.out.println(returnData);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
