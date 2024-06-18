// forum 文章
export interface ForumData {
    id?: string | number;
    uid?: string | number;
    title?: string;
    content: string;
    intor?: string;
    sortId?: string | number;
    img?: string;
    likes?: string | number;
    collect?: string | number;
    create_time?: string;
    update_time?: string;
}

// forum_collect 文章的收藏表
export interface ForumCollectData {
    id: string | number;
    forum_id: string | number;
    user_id: string | number;
    create_time?: string;
    update_time?: string;
}

// forum_like 文章的喜欢表
export interface ForumLikeData {
    id: string | number;
    forum_id: string;
    uid: string;
    create_time?: string;
    update_time?: string;
}

// sort 分类
export interface SortData {
    id?: string | number;
    sortName?: string;
    intor?: string;
    isLook?: boolean;
    create_time?: string;
    update_time?: string;
    uid?: string | number;
}

// group 群的基本信息
export interface GroupData {
    id: string | number;
    group_name: string;
    uid: string;
    avatar: string;
    signature: string;
    create_time?: string;
    update_time?: string;
}

// groupMessage 群内部消息
export interface GroupMessageData {
    id: string | number;
    sender_id: string;
    group_id: string;
    content: string;
    sendTime: string;
    content_img?: string;
}

// group_member 群成员
export interface GroupMemberData {
    id: string | number;
    group_id: string;
    uid: string;
    group_nickname?: string;
    group_avatar?: string;
    join_time?: string;
}

/*
 *      User
 */
// user 用户

export interface UserData {
    id: string | number;
    username: string;
    nickname: string;
    password?: string;
    avatar: string;
    address: string;
    signature: string;
    type: string;
    token?: string;
    level?: number;
    create_time?: string;
    update_time?: string;
    likes?: number;
    forum_number?: number;
    collect?: number;
    homeLink?: string;
}

// UserMessage 用户消息
export interface UserMessageData {
    id?: string | number;
    uid: string;
    receiver_id?: string;
    content: string;
    send_time?: string;
}

// friendsList 好友列表
export interface FriendsListData {
    id: string;
    uid: string;
    friend_id: string;
    appellation: string;
    create_time?: string;
}

// websiteInfo 网站基本信息
export interface WebsiteInfoData {
    // Define the structure of website information here
}

// comment 评论
export interface CommentData {
    id?: string | number;
    forumId: string | number;
    uid: string | number;
    parentId: string | number | null;
    content: string;
    likes?: number;
    contentImg?: string;
    createTime?: string;
    address?: string;
}

// comment_like 评论的喜欢表
export interface CommentLikeData {
    id: string | number;
    uid: string;
    comment_id: string;
    send_time?: string;
    update_time?: string;
}

// address 地址
export interface AddressData {
    id?: string | number;
    name: string;
    intor: string;
    address_img?: string;
    content?: string;
    createTime?: string;
    isLook?: boolean;
}

export interface NotificationData {
    id?: number | string;
    type?: "AllPeople" | "AddFriend" | "Message";
    content?: string;
    sender_id?: string | number;
    receiver_id?: string | number;
    create_time?: string;
    update_time?: string;
    isLook?: boolean;
}
