package com.lxj.chatForum.service.impl;


import com.lxj.chatForum.mapper.AddressMapper;
import com.lxj.chatForum.pojo.AddressPojo;
import com.lxj.chatForum.service.AddressService;
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
