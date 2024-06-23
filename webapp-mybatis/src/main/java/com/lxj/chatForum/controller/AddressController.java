package com.lxj.chatForum.controller;

import com.lxj.chatForum.dto.ResponseResult;
import com.lxj.chatForum.pojo.AddressPojo;
import com.lxj.chatForum.service.AddressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@WebServlet({"/address/getAllAddress","/address/add","/address/edit"})
@Controller
@ResponseBody
@RequestMapping("/address")
public class AddressController  {


    @Resource
    AddressService addressServiceImpl;


    @GetMapping("/getAllAddress")
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
