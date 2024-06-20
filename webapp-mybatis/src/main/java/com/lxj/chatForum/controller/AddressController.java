package com.lxj.chatForum.controller;

import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.pojo.AddressPojo;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

//@WebServlet({"/address/getAllAddress","/address/add","/address/edit"})
@Controller
@ResponseBody
@RequestMapping("/address")
public class AddressController  {

    @Resource
    AddressService addressServiceImpl;


    @PostMapping("/getAllAddress")
    public ResponseResult getAllAddress(){
        List<AddressPojo> addresses = addressServiceImpl.toGetAllAddresses();
        return ResponseResult.success(addresses);
    }

    @PostMapping("/add")
    public ResponseResult addAddress(@RequestBody AddressPojo address){
        addressServiceImpl.toAddAddress(address);


        return ResponseResult.success("ok");
    }
    @PostMapping("/edit")
    public ResponseResult editAddress(@RequestBody AddressPojo address){
            addressServiceImpl.toEditAddress(address);

        return ResponseResult.success("ok");
    }
}
