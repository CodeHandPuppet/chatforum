package com.lxj.chatForum.controller;

import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.pojo.ForumPojo;
import com.lxj.chatForum.service.ForumService;
import com.lxj.chatForum.service.impl.ForumServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//@WebServlet({"/forum/getAllArticle","/forum/getArticleBySortId","/forum/getArticleByUid",
//        "/forum/edit","/forum/send","/forum/deleteById","/forum/getOneArticle","/forum/getForumsByLikesAndCollect"})
@RequestMapping("/forum")
@Controller
public class ForumController  {

    @Resource
    ForumService forumServiceImpl;

    /**
     * 获取全部文章
     * @method GET
     * @return ResponseResult<ForumPojo>
     */
    @PostMapping("/getAllArticle")
    public ResponseResult getAllArticle(){
        List<ForumPojo> allArticle = forumServiceImpl.getAllArticle();
        return ResponseResult.success(allArticle);
    }

    /**
     * 通过分类返回文章列表
     * @param map
     * @return ResponseResult<ForumPojo>
     */
    @PostMapping("/getArticleBySortId")
    public ResponseResult getArticleBySortId(@RequestBody HashMap<String,String> map) {

        List<ForumPojo> list = forumServiceImpl.getArticleBySortId(map.get("sortId"));

        return ResponseResult.success(list);
    }

    /**
     * 获取某人 某类的 的全部文章
     * @param map {type: "ALL" | "like" | "collect" ,uid: String}
     * @return ResponseResult<ForumPojo>
     */
    @PostMapping("/getArticleByUid")
    public ResponseResult getArticleByUid(@RequestBody HashMap<String,String> map) {

        List<ForumPojo> list = forumServiceImpl.getArticleByUid(map);

        return ResponseResult.success(list);
    }


    /**
     * 编辑forum文章
     * @param forum
     * @return ok
     */
    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody ForumPojo forum) {
        forumServiceImpl.toEdit(forum);

        return ResponseResult.success("ok");
    }

    /**
     * 编辑文章
     * @method post
     * @param forum
     * @return
     */
    @PostMapping("/send")
    public ResponseResult send(@RequestBody ForumPojo forum) {
            forumServiceImpl.toSend(forum);
        return ResponseResult.success("ok");
    }

    /**
     * 根据发送过来的id删除文章
     * @param map
     * @return
     */
    @PostMapping("/deleteById")
    public ResponseResult deleteById(@RequestBody HashMap<String,String> map) {

        forumServiceImpl.toDelete(map.get("id"));

        return ResponseResult.success("ok");
    }

    /**
     * 根据文章id获取某一篇文章
     * @param map
     * @return
     */
    @PostMapping("/getOneArticle")
    public ResponseResult getOneArticle(@RequestBody HashMap<String,String> map) {
        ForumPojo forum = forumServiceImpl.getOneArticle(map.get("forumId"));
        return ResponseResult.success(forum);
    }

    /**
     * 根据 喜欢 和 收集 返回热点文章
     * @return
     */
    @PostMapping("/getForumsByLikesAndCollect")
    public ResponseResult getForumsByLikesAndCollect() {
        List<ForumPojo> list = forumServiceImpl.toGetForumsByLikesAndCollect();
        return ResponseResult.success(list);
    }
}
