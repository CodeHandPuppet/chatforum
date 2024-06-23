import request from "@/utils/request";
import { FrendsListResponseData, MessageArrResponseData, SaveOrDeleteResponseData } from "./type";
import { FriendsListData, UserMessageData } from "@/type/websiteDataType";




enum API {
    GET_MESSAGELIET = '/message/getFriendMessageList',
    GET_MEMBERMESSAGE = '/message/getOneMessageArr',
    MESSAGE_SEDN = "/message/send",
    // 添加好友
    FIREND_ADD = "/friends/add",
    // 编辑好友
    FRIEND_EIDTAPPELLATION = '/friends/editAppellation'
}

// 获取消息清单
export const getMessageListRequest = () => {
    return request.get<any, FrendsListResponseData>(API.GET_MESSAGELIET);
}

// 获取消息 和 内部群成员的基本信息
export const getMemberMessage = (uid: string | number, receiverId: number | string) => {
    return request.post<any, MessageArrResponseData>(API.GET_MEMBERMESSAGE, { uid, receiverId })
}
// 发送请求
export const sendMessageRequest = (message: UserMessageData) => {
    const { content, uid, receiverId } = message;
    return request.post<any, SaveOrDeleteResponseData>(API.MESSAGE_SEDN, { receiverId, uid, content })
}
// 添加好友
export const addFriendRequest = (uid: string | number, friendId: number | string) => {
    return request.post<any, SaveOrDeleteResponseData>(API.FIREND_ADD, { friendId, uid });
}
// 编辑备注
export const editFriendAppellationRequest = (uid: string | number, friendId: number | string, appellation: string | null) => {
    return request.post<any, SaveOrDeleteResponseData>(API.FRIEND_EIDTAPPELLATION, { friendId, uid, appellation });
}


