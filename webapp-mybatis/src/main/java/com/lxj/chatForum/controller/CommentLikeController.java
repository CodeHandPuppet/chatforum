package com.lxj.chatForum.controller;

import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.service.CommentLikeService;
import com.lxj.chatForum.service.impl.CommentLikeServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
//@WebServlet({"/commentLike/getLikes","/commentLike/toLike","/commentLike/delete"})
@Controller
@RequestMapping("/commentLike")
public class CommentLikeController{

    @PostMapping("/getLikes")
    public ResponseResult getLikes(){


        return null;
    }

    @PostMapping("/toLike")
    public ResponseResult toLike(){
        return null;
    }

    @PostMapping("/delete")
    public ResponseResult delete(){
        return null;
    }
}
