import { NotificationData } from "@/type/websiteDataType";
import request from "@/utils/request"


enum API {
    // 获取管理员发送的通知
    NOTIFICATION_ADMIN = '/notification/getAdminNot',
    // 获取所有验证好友的同意请求
    NOTIFICATION_GETADDFRIEND = '/notification/getFriendNot',
    // 删除好友申请
    NOTIFICATION_DELFRIEND = "/notification/delFriendNot",
    // 添加好友
    NOTIFICATION_ADDFRIEND = "/notification/addFriendNot",
    // 添加管理管通知
    NOTIFICATION_ADDADMINNOTIFICATION = "/notification/addAdminNot",
    // 删除管理员通知
    NOTIFICATION_REMOVEADMINNOTIFICATION = "/notification/removeAdminNot",
    // 编辑管理员通知
    NOTIFICATION_EDITADMINNOTIFICATION = "/notification/editAdminNot"
}


// 获取管理员发送的通知
export const getAdminNotificationRequest = () => {
    return request.get(API.NOTIFICATION_ADMIN);
}
// 获取所有验证好友的同意请求
export const getAddFriendRequest = (uid: string | number) => {
    return request.post<any, any>(API.NOTIFICATION_GETADDFRIEND, { uid });
}
// 删除好友同意申请
export const delFriendNotificationRequest = (receiver_id: string | number, sender_id: string | number) => {
    return request.post<any, any>(API.NOTIFICATION_DELFRIEND, { receiver_id, sender_id });
}
// 添加好友发出申请
export const addFriendNotificationRequest = (receiver_id: string | number, sender_id: string | number) => {
    return request.post<any, any>(API.NOTIFICATION_ADDFRIEND, { receiver_id, sender_id });
}
// 
export const addNotificationRequest = (notification: NotificationData) => {
    const { content, isLook, sender_id } = notification;
    return request.post<any, any>(API.NOTIFICATION_ADDADMINNOTIFICATION, { content, isLook, sender_id });

}
export const editNotificationRequest = (notification: NotificationData) => {
    const { id, content, isLook } = notification;
    return request.post<any, any>(API.NOTIFICATION_EDITADMINNOTIFICATION, { id, content, isLook });
}

export const removeNotificationRequest = (id: string | number) => {
    return request.post<any, any>(API.NOTIFICATION_REMOVEADMINNOTIFICATION, { id });
}