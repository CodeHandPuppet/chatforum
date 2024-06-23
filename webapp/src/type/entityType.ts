// forum 文章
export interface ForumEntity {
    id?: string | number;
    uid?: string | number;
    title?: string;
    content: string;
    intro?: string;
    sortId?: string | number;
    img?: string;
    likes?: string | number;
    collect?: string | number;
    createTime?: number
    updateTime?: number

}



// forum_collect 文章的收藏表
export interface ForumCollectEntity {
    id: string | number;
    forumId: string | number;
    uid: string | number;
    createTime?: string;
    updateTime?: string;

}




// forum_like 文章的喜欢表
export interface ForumLikeEntity {
    id: string | number;
    forumId: string;
    uid: string;
    createTime?: string;
    updateTime?: string;
}




// sort 分类
export interface SortEntity {
    id?: string | number;
    sortName?: string;
    intro?: string;
    isLook?: boolean;
    createTime?: string;
    updateTime?: string;
    uid?: string | number;
}



// group 群的基本信息
export interface GroupEntity {
    id: string | number;
    groupName: string;
    uid: string;
    avatar: string;
    signature: string;
    createTime?: string;
    updateTime?: string;
}



// groupMessage 群内部消息
export interface GroupMessageEntity {
    id: string | number;
    senderId: string;
    groupId: string;
    content: string;
    sendTime: string;
    contentImg?: string;
}



// group_member 群成员
export interface GroupMemberEntity {
    id: string | number;
    groupId: string;
    uid: string;
    groupNickname?: string;
    groupAvatar?: string;
    joinTime?: string;
}


/*
 *      User
 */
// user 用户

export interface UserEntity {
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
export interface UserMessageEntity {
    id?: string | number;
    uid: string;
    receiverId?: string;
    content: string;
    sendTime?: string;
}


// friendsList 好友列表
export interface FriendsListEntity {
    id: string;
    uid: string;
    friendId: string;
    appellation: string;
    createTime?: string;
}




// websiteInfo 网站基本信息
export interface WebsiteInfoEntity {

}


// comment 评论
export interface CommentEntity {
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
export interface CommentLikeEntity {
    id: string | number;
    uid: string;
    commentId: string;
    sendTime?: string;
    updateTime?: string;
}


// address 地址
export interface AddressEntity {
    id?: string | number;
    name: string;
    intro: string;
    addressImg?: string;
    content?: string;
    createTime?: string;
    isLook?: boolean;
}


export interface NotificationEntity {
    id?: number | string;
    type?: "AllPeople" | "AddFriend" | "Message";
    content?: string;
    senderId?: string | number;
    receiverId?: string | number;
    createTime?: string;
    updateTime?: string;
    isLook?: boolean;
}
