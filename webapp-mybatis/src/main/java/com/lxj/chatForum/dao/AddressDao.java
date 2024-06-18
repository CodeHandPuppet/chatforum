package com.lxj.chatForum.dao;

import com.lxj.chatForum.pojo.AddressPojo;

import java.util.List;

public interface AddressDao {
    List<AddressPojo>  getAddresses();
    int insert(AddressPojo address);
    int update(AddressPojo address);
}
