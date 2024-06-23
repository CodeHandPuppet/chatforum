// forum 文章
export interface ForumData {
    id?: string | number;
    uid?: string | number;
    title?: string;
    content: string;
    intro?: string;
    sortId?: string | number;
    img?: string;
    likes?: string | number;
    collect?: string | number;
    createTime?: string;
    updateTime?: string;
}

// forum_collect 文章的收藏表
export interface ForumCollectData {
    id: string | number;
    forumId: string | number;
    uid: string | number;
    createTime?: string;
    updateTime?: string;
}

// forum_like 文章的喜欢表
export interface ForumLikeData {
    id: string | number;
    forumId: string;
    uid: string;
    createTime?: string;
    updateTime?: string;
}

// sort 分类
export interface SortData {
    id?: string | number;
    sortName?: string;
    intro?: string;
    isLook?: boolean;
    createTime?: string;
    updateTime?: string;
    uid?: string | number;
}

// group 群的基本信息
export interface GroupData {
    id: string | number;
    groupName: string;
    uid: string;
    avatar: string;
    signature: string;
    createTime?: string;
    updateTime?: string;
}

// groupMessage 群内部消息
export interface GroupMessageData {
    id: string | number;
    senderId: string;
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
    createTime?: string;
    updateTime?: string;
    likes?: number;
    forumNumber?: number;
    collect?: number;
    homeLink?: string;
}

// UserMessage 用户消息
export interface UserMessageData {
    id?: string | number;
    uid: string;
    receiverId?: string;
    content: string;
    sendTime?: string;
}

// friendsList 好友列表
export interface FriendsListData {
    id: string;
    uid: string;
    friendId: string;
    appellation: string;
    createTime?: string;
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
    commentId: string;
    sendTime?: string;
    updateTime?: string;
}

// address 地址
export interface AddressData {
    id?: string | number;
    name: string;
    intro: string;
    addressImg?: string;
    content?: string;
    createTime?: string;
    isLook?: boolean;
}

export interface NotificationData {
    id?: number | string;
    type?: "AllPeople" | "AddFriend" | "Message";
    content?: string;
    senderId?: string | number;
    receiverId?: string | number;
    createTime?: string;
    updateTime?: string;
    isLook?: boolean;
}
