package com.lxj.chatForum.service;

public interface UserService {

    //    通过token返回用户信息
    String getInfoByToken(String token);

    //    通过
    String getInfoByUid(String json);

    //
    String login(String json);

    //
    String toGetAllUser();

    String toEditUser(String json);

    String toAddUser(String json);

    String toEditPassword(String json, String token);
}
