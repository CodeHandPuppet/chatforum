package com.lxj.chatForum.service;

import com.lxj.chatForum.pojo.UserPojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {

    //    通过token返回用户信息
    UserPojo getInfoByToken(String token);

    //    通过
    UserPojo getInfoByUid(String id);

    //
    Map<String,String> login(UserPojo user);

    //
    List<UserPojo> toGetAllUser();

    void toEditUser(UserPojo user);

    void toAddUser(UserPojo user);

    String toEditPassword(HashMap<String,String> jsonMap, String token);
}
