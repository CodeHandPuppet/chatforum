package com.lxj.chatForum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




//@WebServlet({"/user/login","/user/getInfo","/user/getInfoByUid","/user/getAllUser"
// ,"/user/add","/user/edit","/user/editPassword"})
@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public void login() {
        // Implementation goes here
    }

    @PostMapping("/getInfo")
    public void getInfo() {
        // Implementation goes here
    }

    @PostMapping("/getInfoByUid")
    public void getInfoByUid() {
        // Implementation goes here
    }

    @PostMapping("/getAllUser")
    public void getAllUser() {
        // Implementation goes here
    }

    @PostMapping("/add")
    public void add() {
        // Implementation goes here
    }

    @PostMapping("/edit")
    public void edit() {
        // Implementation goes here
    }

    @PostMapping("/editPassword")
    public void editPassword() {
        // Implementation goes here
    }

}