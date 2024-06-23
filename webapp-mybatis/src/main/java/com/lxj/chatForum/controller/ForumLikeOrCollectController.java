package com.lxj.chatForum.controller;


import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.service.ForumLikeOrCollectService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

//@WebServlet({"/forumLike/add","/forumLike/remove","/forumLike/getArr",
// "/forumCollect/add","/forumCollect/remove","/forumCollect/getArr"})
@Controller
@ResponseBody
public class ForumLikeOrCollectController {



    @Resource
    ForumLikeOrCollectService forumLikeOrCollectServiceImpl;




    @PostMapping("/forumLike/add")
    public ResponseResult add(@RequestBody HashMap<String,String> map) {

      forumLikeOrCollectServiceImpl.allTypeMethods(map,"likeAdd");

      return ResponseResult.success("ok");
    }

    @PostMapping("/forumLike/remove")
    public ResponseResult remove(@RequestBody HashMap<String,String> map) {
        forumLikeOrCollectServiceImpl.allTypeMethods(map,"likeRemove");
        return ResponseResult.success("ok");
    }

    @PostMapping("/forumLike/getArr")
    public ResponseResult getArr(@RequestBody HashMap<String,String> map) {
        HashMap<String, List<Integer>> likeRemove = forumLikeOrCollectServiceImpl.allArrTypeMethods(map, "likeArr");
        return ResponseResult.success(likeRemove);
    }

//

    @PostMapping("/forumCollect/add")
    public ResponseResult forumCollectAdd(@RequestBody HashMap<String,String> map){
        forumLikeOrCollectServiceImpl.allTypeMethods(map,"collectAdd");
        return ResponseResult.success("ok");
    }
    @PostMapping("/forumCollect/remove")
    public  ResponseResult forumCollectRemove(@RequestBody HashMap<String,String> map){
        forumLikeOrCollectServiceImpl.allTypeMethods(map,"collectRemove");
        return ResponseResult.success("ok");
    }
    @PostMapping("/forumCollect/getArr")
    public ResponseResult forumCollectGetArr(@RequestBody HashMap<String,String> map){
        HashMap<String, List<Integer>> likeRemove = forumLikeOrCollectServiceImpl.allArrTypeMethods(map, "collectArr");
        return ResponseResult.success(likeRemove);
    }
}
