package com.lxj.chatForum.web;

import com.lxj.chatForum.service.CommentService;
import com.lxj.chatForum.service.impl.CommentServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet({"/comment/getComments","/comment/save","/comment/deleteById"})
public class CommentServlet extends HttpServlet {

    CommentService commentService=new CommentServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servletPath = request.getServletPath();

        if(servletPath.equals("/comment/getComments")){
            doGetComments(request,response);
        }
        else if(servletPath.equals("/comment/save")){
            doSave(request,response);
        }
        else if (servletPath.equals("/comment/deleteById")) {
            doDeleteComment(request,response);
        }
    }

    private void doDeleteComment(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=commentService.toDelete(json);

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doSave(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=commentService.getSave(json);

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @method: post
     * @path: /comment/getComments
     * @data: forumId: Integer
     * @contentType:application/json
     */
    private void doGetComments(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=commentService.getComments(json);

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
