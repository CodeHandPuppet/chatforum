package com.lxj.chatForum.controller;

import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.pojo.CommentLikePojo;
import com.lxj.chatForum.service.CommentLikeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@WebServlet({"/commentLike/getLikes","/commentLike/toLike","/commentLike/delete"})
@Controller
@ResponseBody
@RequestMapping("/commentLike")
public class CommentLikeController{

    @Resource
    CommentLikeService commentLikeServiceImpl;



    @PostMapping("/getLikes")
    public ResponseResult getLikes(@RequestBody Map<String,String> map){

        List<Integer> likes = commentLikeServiceImpl.getLikeArr(map.get("uid"));
        HashMap<String, List<Integer>> map1 = new HashMap<>();

        map1.put("likeIds",likes);
        return ResponseResult.success(map1);
    }


    @PostMapping("/toLike")
    public ResponseResult toLike(@RequestBody CommentLikePojo commentLike){

    commentLikeServiceImpl.addCommentLike(commentLike);

        return ResponseResult.success("ok");
    }


    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody CommentLikePojo commentLike){
        commentLikeServiceImpl.deleteCommentLike(commentLike);
        return ResponseResult.success("ok");
    }

}
