package com.lxj.chatForum.service.impl;

import com.google.gson.Gson;
import com.lxj.chatForum.dao.AddressDao;
import com.lxj.chatForum.dao.impl.AddressDaoImpl;
import com.lxj.chatForum.mapper.AddressMapper;
import com.lxj.chatForum.pojo.AddressPojo;
import com.lxj.chatForum.service.AddressService;
import com.lxj.chatForum.utils.ReturnData;
import com.lxj.chatForum.utils.SqlSessionUtils;

import java.util.List;

public class AddressServiceImpl implements AddressService {

//    AddressDao addressDao=new AddressDaoImpl();
    AddressMapper addressDao= SqlSessionUtils.openSession().getMapper(AddressMapper.class);
    @Override
    public String toGetAllAddresses() {
        Gson gson=new Gson();
        List<AddressPojo> addresses = addressDao.getAddresses();
        if(addresses==null){
            return gson.toJson(ReturnData.error("No addresses"));
        }
        return gson.toJson(ReturnData.success(addresses));
    }

    @Override
    public String toAddAddress(String json) {
        Gson gson=new Gson();
        AddressPojo addressPojo = gson.fromJson(json, AddressPojo.class);
        int count = addressDao.insert(addressPojo);
        if(count==0){
            return gson.toJson(ReturnData.error("No addresses"));
        }
        return gson.toJson(ReturnData.success("yes"));
    }

    @Override
    public String toEditAddress(String json) {
        Gson gson=new Gson();
        AddressPojo addressPojo = gson.fromJson(json, AddressPojo.class);
        int count = addressDao.update(addressPojo);
        if(count==0){
            return gson.toJson(ReturnData.error("No addresses"));
        }
        return gson.toJson(ReturnData.success("yes"));
    }
}
