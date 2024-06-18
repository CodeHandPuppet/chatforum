import request from "@/utils/request"
import { SaveOrDeleteResponseData, UserDataResponseData, UserLoginResponseData } from "./type";
import { UserData } from "@/type/websiteDataType";


enum API {
    // post    1. 用户登录判断，登录成功返回 token      user/login
    USER_LOGIN = '/user/login',
    // get     2. 通过 token 返回用户的 基本信息        user/info
    USER_INFO = '/user/getInfo',
    // post  3. 通过某一个人的 id 返回他的 基本信息 
    ONEUSER_INFO = "/user/getInfoByUid",
    // 返回所有用户的信息
    USER_INFOARR = '/user/getAllUser',
    //添加用户 
    USER_ADD = '/user/add',
    // 编辑用户
    USER_EDIT = '/user/edit',
    // 编辑密码
    USER_EDITPASSWORD = '/user/editPassword'
}

// 检查是否登录成功
export const userloginRequest = (data: { username: string, password: string }) => {
    return request.post<any, UserLoginResponseData>(API.USER_LOGIN, data);
}
// 获取自己的个人信息
export const getUserinfoRequest = () => {
    return request.get<any, UserDataResponseData>(API.USER_INFO);
}
// 获取某个人的账号信息
export const getOneUserInfoRequest = (id: string | number) => {
    return request.post<any, UserDataResponseData>(API.ONEUSER_INFO, { id: id });
}
// 返回所有用户的信息
export const getAllUsersInfoRequest = () => {
    return request.get<any, any>(API.USER_INFOARR);
}
//添加用户 
export const addUserRequest = (user: UserData) => {
    const { username, nickname, password, signature, address, avatar } = user;
    return request.post<any, SaveOrDeleteResponseData>(API.USER_ADD, { username, nickname, password, signature, address, avatar });
}
// 编辑用户
export const editUserRequest = (user: UserData) => {
    const { username, nickname, password, signature, address, avatar, type, id } = user;
    return request.post<any, SaveOrDeleteResponseData>(API.USER_EDIT, { type, username, nickname, password, signature, address, avatar, id });
}
// 编辑账号类型
export const editTypeUserRequest = (id: number | string, type: string) => {
    return request.post<any, any>(API.USER_ADD, { id, type });
}

// 修改密码
export const editUserPassword = (newPassword: string, oldPassword: string) => {
    return request.post<any, SaveOrDeleteResponseData>(API.USER_EDITPASSWORD, { newPassword, oldPassword });
}