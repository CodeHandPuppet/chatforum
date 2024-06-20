package com.lxj.chatForum.controller;

import com.lxj.chatForum.service.AddressService;
import com.lxj.chatForum.service.impl.AddressServiceImpl;
import com.lxj.chatForum.utils.ReadJsonUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
//@WebServlet({"/address/getAllAddress","/address/add","/address/edit"})
@Controller
@ResponseBody
@RequestMapping("/address")
public class AddressController  {

    @Resource
    AddressService addressServiceImpl;


    @PostMapping("/getAllAddress")
    public String getAllAddress(){
        return addressServiceImpl.toGetAllAddresses();
    }

    @PostMapping("/add")
    public void addAddress(){

    }
    @PostMapping("/edit")
    public void editAddress(){

    }
}
