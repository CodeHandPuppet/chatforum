package com.lxj.chatForum.mapper.test;

import com.lxj.chatForum.mapper.CommentMapper;
import com.lxj.chatForum.pojo.CommentPojo;
import com.lxj.chatForum.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CommentMapperTest {
    @Test
    public void test(){
        SqlSession sqlSession = SqlSessionUtils.openSession();
        CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
        List<CommentPojo> commentsByForumId = mapper.getCommentsByForumId("1");
        System.out.println(commentsByForumId);
    }
}
