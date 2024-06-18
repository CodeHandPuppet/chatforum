import request from "@/utils/request";
import { ChatListResponseData } from "./type";

enum API {
    // 好友
    GET_FRIENDS = '/chat/getFriends',

}

export const getChatFriendsRequest = () => {
    return request.get<any, ChatListResponseData>(API.GET_FRIENDS);
}


/*

    1. 通过好友数据库 和 群数据库 各自返回 群成员和好友成员列表

*/