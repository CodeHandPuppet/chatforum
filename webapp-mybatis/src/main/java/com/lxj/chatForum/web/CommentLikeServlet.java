package com.lxj.chatForum.web;

import com.lxj.chatForum.service.CommentLikeService;
import com.lxj.chatForum.service.impl.CommentLikeServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet({"/commentLike/getLikes","/commentLike/toLike","/commentLike/delete"})
public class CommentLikeServlet  extends HttpServlet {

    CommentLikeService commentLikeService=new CommentLikeServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servletPath = request.getServletPath();


      if(servletPath.equals("/commentLike/getLikes")){
            doGetLikeArr(request,response);
      }
      else if(servletPath.equals("/commentLike/toLike")){
            doAdd(request,response);
      }
      else if(servletPath.equals("/commentLike/delete")){
        doDel(request,response);
      }

    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=commentLikeService.deleteCommentLike(json);

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=commentLikeService.addCommentLike(json);

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @method: post
     * @path: /commentLike/getLikes
     * @data: uid: Integer
     * @contentType:application/json
     */
    private void doGetLikeArr(HttpServletRequest request, HttpServletResponse response) {
        String json = ReadJsonUtils.readJson(request);

        String returnData=commentLikeService.getLikeArr(json);

        try {
            response.getWriter().print(returnData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
