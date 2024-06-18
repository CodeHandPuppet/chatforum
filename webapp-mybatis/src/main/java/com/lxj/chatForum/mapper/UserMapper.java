package com.lxj.chatForum.mapper;

import com.lxj.chatForum.pojo.UserPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    //    获取token
    String getTokenByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    //通过token，获得用户的基本信息
    UserPojo getUserInfoByToken(String token);

    //获得单个用户的基本信息
    UserPojo getInfoById(String id);

    // 获取token
    String getIdBytoken(String id);

    //获得全部用户
    List<UserPojo> getUsers();

    //   更新
    int update(UserPojo user);

    //    插入
    int insert(UserPojo user);

    //获得密码
    String userPasswordByToken(String token);

    //修改密码
    int updatePasswordByToken(@Param("password") String password,@Param("token") String token);
}
