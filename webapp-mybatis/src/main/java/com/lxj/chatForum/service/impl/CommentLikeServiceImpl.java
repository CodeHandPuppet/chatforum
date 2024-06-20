package com.lxj.chatForum.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lxj.chatForum.dao.CommentLikeDao;
import com.lxj.chatForum.dao.impl.CommentLikeDaoImpl;
import com.lxj.chatForum.mapper.CommentLikeMapper;
import com.lxj.chatForum.service.CommentLikeService;
import com.lxj.chatForum.utils.ReturnData;
import com.lxj.chatForum.utils.SqlSessionUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CommentLikeServiceImpl implements CommentLikeService {
//    CommentLikeDao commentLikeMapper = new CommentLikeDaoImpl();

    @Resource
    CommentLikeMapper commentLikeMapper;

    Gson gson = new GsonBuilder().create();
    Type type = new TypeToken<HashMap<String, String>>() {}.getType();

    @Override
    public String getLikeArr(String json) {


        Map<String, String> jsonMap = gson.fromJson(json, type);
        String uid = jsonMap.get("uid");
        List<Integer> list = commentLikeMapper.getCommentIdByForumIdAndUid(uid);

        if (list == null) {
            return gson.toJson(ReturnData.error(null));
        }
        HashMap<String, List<Integer>> map = new HashMap<>();
        map.put("likeIds", list);
        return gson.toJson(ReturnData.success(map));
    }

    @Override
    public String addCommentLike(String json) {

        Map<String, String> jsonMap = gson.fromJson(json, type);
        String uid = jsonMap.get("uid");
        String commentId = jsonMap.get("commentId");
        int list = commentLikeMapper.insertByUidAndCommentId(uid, commentId);
        if (list == 0) {
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));
    }

    @Override
    public String deleteCommentLike(String json) {
        Map<String, String> jsonMap = gson.fromJson(json, type);
        String uid = jsonMap.get("uid");
        String commentId = jsonMap.get("commentId");
        int list = commentLikeMapper.deleteByUidAndCommentId(uid, commentId);

        if (list == 0) {
            return gson.toJson(ReturnData.error(null));
        }
        return gson.toJson(ReturnData.success("ok"));

    }

}
