package com.lxj.chatForum.controller;

import com.lxj.chatForum.service.CommentService;
import com.lxj.chatForum.service.impl.CommentServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

//@WebServlet({"/comment/getComments","/comment/save","/comment/deleteById"})
@Controller
@RequestMapping("/comment")
public class CommentController  {

    @PostMapping("/getComments")
    public void getComments(){

    }

    @PostMapping("/save")
    public void save(){

    }
    @PostMapping("/deleteById")
    public void deleteById(){

    }


}
