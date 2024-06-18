package com.lxj.chatForum.web;

import com.lxj.chatForum.service.ForumLikeOrCollectService;
import com.lxj.chatForum.service.impl.ForumLikeOrCollectServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet({"/forumLike/add","/forumLike/remove","/forumLike/getArr","/forumCollect/add","/forumCollect/remove","/forumCollect/getArr"})
public class ForumLikeOrCollectServlet extends HttpServlet {
    ForumLikeOrCollectService forumLikeOrCollectService=new ForumLikeOrCollectServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if(servletPath.equals("/forumLike/add")){
            doLikeAdd(request, response);
        }
        else if(servletPath.equals("/forumLike/remove")){
            doLikeRemove(request, response);
        }
        else if(servletPath.equals("/forumCollect/add")){
            doCollectAdd(request, response);
        }
        else if(servletPath.equals("/forumCollect/remove")){
            doCollectRemove(request,response);
        }
        else if(servletPath.equals("/forumCollect/getArr")){
            doCollectGetArr(request,response);
        }
        else if(servletPath.equals("/forumLike/getArr")){
            doLikeGetArr(request,response);
        }
    }

    private void doCollectGetArr(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=forumLikeOrCollectService.allArrTypeMethods(json,"collectArr");

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doLikeGetArr(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=forumLikeOrCollectService.allArrTypeMethods(json,"likeArr");

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doLikeAdd(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=forumLikeOrCollectService.allTypeMethods(json,"likeAdd");

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doLikeRemove(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=forumLikeOrCollectService.allTypeMethods(json,"likeRemove");

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doCollectAdd(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=forumLikeOrCollectService.allTypeMethods(json,"collectAdd");

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doCollectRemove(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=forumLikeOrCollectService.allTypeMethods(json,"collectRemove");

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
