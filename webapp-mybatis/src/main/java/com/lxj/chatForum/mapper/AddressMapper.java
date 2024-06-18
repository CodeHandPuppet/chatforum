package com.lxj.chatForum.mapper;

import com.lxj.chatForum.pojo.AddressPojo;

import java.util.List;

public interface AddressMapper {
    //    获取全部地址
    List<AddressPojo> getAddresses();

    //    插入某一个地址
    int insert(AddressPojo address);

    //    更新某个地址
    int update(AddressPojo address);
}
