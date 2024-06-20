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
    public List<AddressPojo> toGetAllAddresses() {


        List<AddressPojo> addresses = addressMapper.getAddresses();

        return addresses;



    }

    @Override
    public int toAddAddress(AddressPojo address) {



        int count = addressMapper.insert(address);

        return count;

    }

    @Override
    public int toEditAddress(AddressPojo address) {


        int count = addressMapper.update(address);

        return count;

    }
}
