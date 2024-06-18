package com.lxj.chatForum.dao;

import com.lxj.chatForum.pojo.UserPojo;

import java.util.List;

public interface UserDao {
    String getTokenByUsernameAndPassword(String username, String password);

    UserPojo getUserInfoByToken(String token);

    UserPojo getInfoById(String id);

    String getIdBytoken(String token);

    List<UserPojo> getUsers();

    int update(UserPojo user);
    int insert(UserPojo user);
}
