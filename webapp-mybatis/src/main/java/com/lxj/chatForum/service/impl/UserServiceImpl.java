package com.lxj.chatForum.service.impl;


import com.lxj.chatForum.mapper.UserMapper;
import com.lxj.chatForum.pojo.UserPojo;
import com.lxj.chatForum.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    //    UserDao userMapper=new UserDaoImpl();
//    UserMapper userMapper = SqlSessionUtils.openSession().getMapper(UserMapper.class);

    @Resource
    UserMapper userMapper;



    @Override
    public UserPojo getInfoByToken(String token) {


//
        UserPojo userInfo = userMapper.getUserInfoByToken(token);

        return userInfo;
    }

    @Override
    public UserPojo getInfoByUid(String id) {

        UserPojo userInfo = userMapper.getInfoById(id);

        return userInfo;
    }

    @Override
    public Map<String,String> login(UserPojo user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String token = userMapper.getTokenByUsernameAndPassword(username, password);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    @Override
    public List<UserPojo> toGetAllUser() {



        List<UserPojo> users = userMapper.getUsers();



        return users;
    }

    @Override
    public void toEditUser(UserPojo user) {

        int update = userMapper.update(user);



    }

    @Override
    public void toAddUser( UserPojo user) {

        int update = userMapper.insert(user);


    }

    @Override
    public String toEditPassword(HashMap<String,String> jsonMap, String token) {

        String oldPassword = jsonMap.get("oldPassword");
        String newPassword = jsonMap.get("newPassword");
//      获得用户密码
        String userPassword = userMapper.userPasswordByToken(token);
//      判断密码是否正确
        if (userPassword.equals(oldPassword)) {
            int i = userMapper.updatePasswordByToken(newPassword, token);
//            修改
            return "更改成功";
        } else {

            return "更改失败";
        }
    }
}
