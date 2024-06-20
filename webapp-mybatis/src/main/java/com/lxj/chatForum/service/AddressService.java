package com.lxj.chatForum.service;



import com.lxj.chatForum.pojo.AddressPojo;

import java.util.List;

public interface AddressService {
    List<com.lxj.chatForum.pojo.AddressPojo>  toGetAllAddresses();
    int toAddAddress(AddressPojo address);
    int toEditAddress(AddressPojo address);
}
