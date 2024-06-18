// 定义 Notification 接口
export interface Notification {
    id: number;
    sender_id: number;
    receiver_id: number;
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
    group_name: string;
    owner_id: number;
    avatar: string;
    signature: string
}

// 定义 User_message 接口
export interface UserMessage {
    id: number;
    sender_id: number;
    receiver_id: number;
    content: string;
    send_time: string;
}

// 定义 Group_message 接口
export interface GroupMessage {
    id: number;
    sender_id: number;
    group_id: number;
    content: string;
    send_time: string;
}

// 定义 Forum 接口
export interface Forum {
    id: number;
    sender_id: number;
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
    user_id: number;
}
// friendsList
export interface FriendsList {
    id: number;
    user_id: number;
    friend_id: number;
}