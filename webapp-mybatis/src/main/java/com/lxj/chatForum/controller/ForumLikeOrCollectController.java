package com.lxj.chatForum.controller;


import com.lxj.chatForum.dto.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@WebServlet({"/forumLike/add","/forumLike/remove","/forumLike/getArr",
// "/forumCollect/add","/forumCollect/remove","/forumCollect/getArr"})
@Controller
public class ForumLikeOrCollectController {

    @PostMapping("/forumLike/add")
    public ResponseResult add() {
        return null;
    }

    @PostMapping("/forumLike/remove")
    public ResponseResult remove() {
        return null;
    }

    @PostMapping("/forumLike/getArr")
    public ResponseResult getArr() {
        return null;
    }

//

    @PostMapping("/forumCollect/add")
    public ResponseResult forumCollectAdd(){
        return null;
    }
    @PostMapping("/forumCollect/remove")
    public  ResponseResult forumCollectRemove(){
        return null;
    }
    @PostMapping("/forumCollect/getArr")
    public ResponseResult forumCollectGetArr(){
        return null;
    }
}
