import { AddressData } from "@/type/websiteDataType";
import request from "@/utils/request";

enum API {
    // 获取全部的地址
    ADDRESS_ALL = '/address/getAllAddress',
    // 编辑某个地址
    ADDRESS_EDIT = '/address/edit',
    // 添加某个地址
    ADDRESS_ADD = '/address/add',
}

// 添加某个地址
export const addAddressRequest = (address: AddressData) => {
    const { name, intor, isLook } = address;
    return request.post<any, any>(API.ADDRESS_ADD, { name, intor, isLook });
}
// 编辑某个地址
export const editAddressRequest = (address: AddressData) => {
    const { name, intor, id, isLook } = address;
    return request.post<any, any>(API.ADDRESS_EDIT, { isLook, name, intor, id });
}
// 获取所有的地址
export const getAllAddressRequest = () => {

    return request.get<any, any>(API.ADDRESS_ALL);
}