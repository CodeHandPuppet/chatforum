package com.lxj.chatForum.controller;

import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.pojo.CommentPojo;
import com.lxj.chatForum.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@WebServlet({"/comment/getComments","/comment/save","/comment/deleteById"})
@Controller
@ResponseBody
@RequestMapping("/comment")
public class CommentController  {

    @Resource
    CommentService commentServiceImpl;


    @PostMapping("/getComments")
    public ResponseResult getComments(@RequestBody Map<String,String> map){

        List<CommentPojo> comments = commentServiceImpl.getComments(map.get("forumId"));

        return ResponseResult.success(comments);
    }

    @PostMapping("/save")
    public ResponseResult save(@RequestBody CommentPojo comment){

        Map<String, Integer> save = commentServiceImpl.getSave(comment);

        return ResponseResult.success(save);
    }


    @PostMapping("/deleteById")
    public ResponseResult deleteById(@RequestBody CommentPojo comment){
        commentServiceImpl.toDelete(comment);
        return ResponseResult.success("ok");
    }


}
