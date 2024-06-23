package com.lxj.chatForum.controller;

import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.pojo.UserPojo;
import com.lxj.chatForum.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@WebServlet({"/user/login","/user/getInfo","/user/getInfoByUid","/user/getAllUser"
// ,"/user/add","/user/edit","/user/editPassword"})
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    
    
    
    @Resource
    UserServiceImpl userServiceImpl;
    
    
    
    @PostMapping("/login")
    public ResponseResult login(@RequestBody UserPojo user) {
        Map<String, String> login = userServiceImpl.login(user);

        return ResponseResult.success(login);
    }

    @GetMapping("/getInfo")
    public ResponseResult getInfo(@RequestHeader("token")String token) {
        UserPojo infoByToken = userServiceImpl.getInfoByToken(token);


        return ResponseResult.success(infoByToken);

    }

    @PostMapping("/getInfoByUid")
    public ResponseResult getInfoByUid(@RequestBody Map<String, String> map) {
        UserPojo userInfo = userServiceImpl.getInfoByUid(map.get("id"));

        return ResponseResult.success(userInfo);
    }

    @GetMapping("/getAllUser")
    public ResponseResult getAllUser() {
        List<UserPojo> getAllUser = userServiceImpl.toGetAllUser();

        return ResponseResult.success(getAllUser);
    }

    @PostMapping("/add")
    public ResponseResult add(@RequestBody UserPojo user) {
        userServiceImpl.toAddUser(user);
        return ResponseResult.success("ok");
    }

    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody UserPojo user) {
        userServiceImpl.toEditUser(user);
        return ResponseResult.success("ok");
    }

    @PostMapping("/editPassword")
    public ResponseResult editPassword(@RequestHeader("token")String token,@RequestBody HashMap<String, String> map) {
        userServiceImpl.toEditPassword(map,token);
        return ResponseResult.success("ok");
    }

}