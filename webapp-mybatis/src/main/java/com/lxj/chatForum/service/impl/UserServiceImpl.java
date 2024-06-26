package com.lxj.chatForum.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lxj.chatForum.mapper.UserMapper;
import com.lxj.chatForum.pojo.UserPojo;
import com.lxj.chatForum.service.UserService;
import com.lxj.chatForum.utils.ReturnData;
import com.lxj.chatForum.utils.SqlSessionUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    //    UserDao userDao=new UserDaoImpl();
    UserMapper userDao = SqlSessionUtils.openSession().getMapper(UserMapper.class);

    @Override
    public String getInfoByToken(String token) {

        Gson gson = new GsonBuilder().create();
//
        UserPojo userInfo = userDao.getUserInfoByToken(token);

        if (userInfo == null) {
            return gson.toJson(ReturnData.error(userInfo));
        } else {
            return gson.toJson(ReturnData.success(userInfo));
        }
    }

    @Override
    public String getInfoByUid(String json) {
        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        Map<String, String> jsonMap = gson.fromJson(json, type);
        UserPojo userInfo = userDao.getInfoById(jsonMap.get("id"));

        if (userInfo == null) {
            return gson.toJson(ReturnData.error(null));
        } else {
            return gson.toJson(ReturnData.success(userInfo));
        }
    }

    @Override
    public String login(String json) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        Map<String, String> jsonMap = gson.fromJson(json, type);
        String username = jsonMap.get("username");
        String password = jsonMap.get("password");
//
        String token = userDao.getTokenByUsernameAndPassword(username, password);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        if (token == null) {
            return gson.toJson(ReturnData.error(map));
        } else {
            return gson.toJson(ReturnData.success(map));
        }
    }

    @Override
    public String toGetAllUser() {
        Gson gson = new GsonBuilder().create();


        List<UserPojo> users = userDao.getUsers();
        if (users == null) {
            return gson.toJson(ReturnData.error("error"));
        } else {
            return gson.toJson(ReturnData.success(users));
        }
    }

    @Override
    public String toEditUser(String json) {
        Gson gson = new GsonBuilder().create();

        UserPojo userPojo = gson.fromJson(json, UserPojo.class);
        int update = userDao.update(userPojo);
        if (update == 0) {
            return gson.toJson(ReturnData.error("error"));
        }
        return gson.toJson(ReturnData.success("ok"));

    }

    @Override
    public String toAddUser(String json) {
        Gson gson = new GsonBuilder().create();

        UserPojo userPojo = gson.fromJson(json, UserPojo.class);
        int update = userDao.insert(userPojo);
        if (update == 0) {
            return gson.toJson(ReturnData.error("error"));
        }
        return gson.toJson(ReturnData.success("ok"));

    }

    @Override
    public String toEditPassword(String json, String token) {
        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        Map<String, String> jsonMap = gson.fromJson(json, type);
        String oldPassword = jsonMap.get("oldPassword");
        String newPassword = jsonMap.get("newPassword");
//      获得用户密码
        String userPassword = userDao.userPasswordByToken(token);
//      判断密码是否正确
        if (userPassword.equals(oldPassword)) {
            int i = userDao.updatePasswordByToken(newPassword, token);
//            修改
            return gson.toJson(ReturnData.success("更改成功"));
        } else {
            ReturnData err = ReturnData.error("密码错误");
            return gson.toJson(err);
        }
    }
}
