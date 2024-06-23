// 定义 Notification 接口
export interface Notification {
    id: number;
    senderId: number;
    receiverId: number;
    type: 'forum' | 'message' | 'add' | 'delete';
}

// 定义 User 接口
export interface User {
    id: number;
    username: string;
    nickname: string;
    password: string;
    avatar: string;
    signature: string;
    type: 'admin' | 'teacher' | 'student';
    token: string
}

// 定义 Group 接口
export interface Group {
    id: number;
    groupName: string;
    owner_id: number;
    avatar: string;
    signature: string
}

// 定义 User_message 接口
export interface UserMessage {
    id: number;
    senderId: number;
    receiverId: number;
    content: string;
    sendTime: string;
}

// 定义 Group_message 接口
export interface GroupMessage {
    id: number;
    senderId: number;
    group_id: number;
    content: string;
    sendTime: string;
}

// 定义 Forum 接口
export interface Forum {
    id: number;
    senderId: number;
    like: number;
    content: string;
    collect: number;
    title: string;
    sendTime: string;
    img: string
    sort: string
}

// groupMember
export interface groupMember {
    id: number;
    group_id: number;
    uid: number;
}
// friendsList
export interface FriendsList {
    id: number;
    uid: number;
    friendId: number;
}