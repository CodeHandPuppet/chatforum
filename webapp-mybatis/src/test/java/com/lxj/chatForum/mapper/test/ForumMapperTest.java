package com.lxj.chatForum.mapper.test;

import com.lxj.chatForum.mapper.ForumMapper;
import com.lxj.chatForum.pojo.ForumPojo;
import com.lxj.chatForum.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class ForumMapperTest {
    SqlSession sqlSession = SqlSessionUtils.openSession();
    ForumMapper mapper = sqlSession.getMapper(ForumMapper.class);

    @Test
    public void test(){
        List<ForumPojo> allForum = mapper.getLikeForumByUid("1");
        System.out.println(allForum);
    }
}
