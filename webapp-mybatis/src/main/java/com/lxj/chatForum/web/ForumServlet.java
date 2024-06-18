package com.lxj.chatForum.web;

import com.lxj.chatForum.service.ForumService;
import com.lxj.chatForum.service.impl.ForumServiceImpl;
import com.lxj.chatForum.utils.DButils;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/forum/getAllArticle","/forum/getArticleBySortId","/forum/getArticleByUid",
        "/forum/edit","/forum/send","/forum/deleteById","/forum/getOneArticle","/forum/getForumsByLikesAndCollect"})
public class ForumServlet extends HttpServlet {

    ForumService forumService=new ForumServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servletPath = request.getServletPath();

        if(servletPath.equals("/forum/deleteById")){
            doDeleteById(request,response);
        }
        else if (servletPath.equals("/forum/send")){
            doSend(request,response);
        }
        else if (servletPath.equals("/forum/edit")) {
            doEdit(request,response);
        }
        else if(servletPath.equals("/forum/getArticleByUid")){
            doGetArticleByUid(request,response);
        }
        else if(servletPath.equals("/forum/getArticleBySortId")){
            doGetArticleBySortId(request,response);
        }
        else if (servletPath.equals("/forum/getAllArticle")) {
            doGetAllArticle(request,response);
        }
        else if(servletPath.equals("/forum/getOneArticle")){
            doGetOneArticle(request,response);
        }
        else if(servletPath.equals("/forum/getForumsByLikesAndCollect")){
            doGetForumsByLikesAndCollect(request,response);
        }
    }

    private void doGetForumsByLikesAndCollect(HttpServletRequest request, HttpServletResponse response) {
        String returnData=forumService.toGetForumsByLikesAndCollect();
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @method: post
     * @path: /forum/deleteById
     * @data: id:uid
     * @contentType:application/json
     */
    private void doDeleteById(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=forumService.toDelete(json);

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @method: post
     * @path:  /forum/send
     * @data: id:uid
     * @contentType:application/json
     */
    private void doSend(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=forumService.toSend(json);

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    /**
     * @method: post
     * @path: /forum/edit
     * @data: id:uid
     * @contentType:application/json
     */
    private void doEdit(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=forumService.toEdit(json);

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @method: post
     * @path: /forum/getArticleByUid
     * @data: {type:All / like  / collect,uid: userId }
     * @contentType:application/json
     */
    private void doGetArticleByUid(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=forumService.getArticleByUid(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * @method: post
     * @path: /forum/getArticleBySortId
     * @data: sortId:number
     * @contentType:application/json
     */
    private void doGetArticleBySortId(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);
        String returnData=forumService.getArticleBySortId(json);
        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @method: get
     * @path: /forum/getAllArticle
     * @
     */
    private void doGetAllArticle(HttpServletRequest request, HttpServletResponse response) {
//        没有参数直接调用服务即可
        String returnData=forumService.getAllArticle();

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void doGetOneArticle(HttpServletRequest request, HttpServletResponse response) {

        String json = ReadJsonUtils.readJson(request);
        String returnData=forumService.getOneArticle(json);

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
