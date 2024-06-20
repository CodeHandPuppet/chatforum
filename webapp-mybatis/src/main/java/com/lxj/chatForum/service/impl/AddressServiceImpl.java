package com.lxj.chatForum.service.impl;

import com.google.gson.Gson;
import com.lxj.chatForum.dao.AddressDao;
import com.lxj.chatForum.dao.impl.AddressDaoImpl;
import com.lxj.chatForum.mapper.AddressMapper;
import com.lxj.chatForum.pojo.AddressPojo;
import com.lxj.chatForum.service.AddressService;
import com.lxj.chatForum.utils.ReturnData;
import com.lxj.chatForum.utils.SqlSessionUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    AddressMapper addressMapper;


    @Override
    public String toGetAllAddresses() {
        Gson gson=new Gson();
        List<AddressPojo> addresses = addressMapper.getAddresses();
        if(addresses==null){
            return gson.toJson(ReturnData.error("No addresses"));
        }
        return gson.toJson(ReturnData.success(addresses));
    }

    @Override
    public String toAddAddress(String json) {
        Gson gson=new Gson();
        AddressPojo addressPojo = gson.fromJson(json, AddressPojo.class);
        int count = addressMapper.insert(addressPojo);
        if(count==0){
            return gson.toJson(ReturnData.error("No addresses"));
        }
        return gson.toJson(ReturnData.success("yes"));
    }

    @Override
    public String toEditAddress(String json) {
        Gson gson=new Gson();
        AddressPojo addressPojo = gson.fromJson(json, AddressPojo.class);
        int count = addressMapper.update(addressPojo);
        if(count==0){
            return gson.toJson(ReturnData.error("No addresses"));
        }
        return gson.toJson(ReturnData.success("yes"));
    }
}
