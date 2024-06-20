package com.lxj.chatForum.controller;

import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.service.ForumService;
import com.lxj.chatForum.service.impl.ForumServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

//@WebServlet({"/forum/getAllArticle","/forum/getArticleBySortId","/forum/getArticleByUid",
//        "/forum/edit","/forum/send","/forum/deleteById","/forum/getOneArticle","/forum/getForumsByLikesAndCollect"})
@RequestMapping("/forum")
public class ForumController  {

    @PostMapping("/getAllArticle")
    public ResponseResult getAllArticle(){
        return null;
    }
    @PostMapping("/getArticleBySortId")
    public ResponseResult getArticleBySortId() {
        return null;
    }

    @PostMapping("/getArticleByUid")
    public ResponseResult getArticleByUid() {
      return null;
    }

    @PostMapping("/edit")
    public ResponseResult edit() {
        return null;
    }

    @PostMapping("/send")
    public ResponseResult send() {

        return null;
    }

    @PostMapping("/deleteById")
    public ResponseResult deleteById() {
        return null;
    }

    @PostMapping("/getOneArticle")
    public ResponseResult getOneArticle() {

        return null;
    }

    @PostMapping("/getForumsByLikesAndCollect")
    public ResponseResult getForumsByLikesAndCollect() {

        return null;
    }
}
