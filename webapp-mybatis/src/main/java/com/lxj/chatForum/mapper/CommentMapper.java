package com.lxj.chatForum.mapper;

import com.lxj.chatForum.pojo.CommentPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    //    通过文章获取对应的全部评论
    List<CommentPojo> getCommentsByForumId(String forumId);

    //插入
    int insertReturnId(CommentPojo comment);

    //根据id删除评论
    int deleteById(@Param("id") String id,@Param("parentId") String parentId);
}
