package com.lxj.chatForum.controller;

import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.pojo.CommentPojo;
import com.lxj.chatForum.service.CommentLikeService;
import com.lxj.chatForum.service.CommentService;
import com.lxj.chatForum.service.impl.CommentLikeServiceImpl;
import com.lxj.chatForum.service.impl.CommentServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//@WebServlet({"/comment/getComments","/comment/save","/comment/deleteById"})
@Controller
@ResponseBody
@RequestMapping("/comment")
public class CommentController  {

    @Resource
    CommentService commentServiceImpl;

    @GetMapping("/getComments")
    public ResponseResult getComments(@RequestParam("forumId") String forumId){

        List<CommentPojo> comments = commentServiceImpl.getComments(forumId);

        return ResponseResult.success(comments);
    }

    @PostMapping("/save")
    public ResponseResult save(@RequestBody CommentPojo comment){

        int id = commentServiceImpl.getSave(comment);

        return ResponseResult.success(new HashMap<String, Integer>().put("id", id));
    }

    @PostMapping("/deleteById")
    public ResponseResult deleteById(@RequestBody CommentPojo comment){
        commentServiceImpl.toDelete(comment);
        return ResponseResult.success("ok");
    }


}
