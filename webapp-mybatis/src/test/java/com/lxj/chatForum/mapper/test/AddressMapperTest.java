package com.lxj.chatForum.mapper.test;

import com.lxj.chatForum.mapper.AddressMapper;
import com.lxj.chatForum.pojo.AddressPojo;
import com.lxj.chatForum.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class AddressMapperTest {
    @Test
    public void test(){
        SqlSession sqlSession = SqlSessionUtils.openSession();
        AddressMapper mapper = sqlSession.getMapper(AddressMapper.class);

        List<AddressPojo> addresses = mapper.getAddresses();
        System.out.println(addresses);
    }
}
