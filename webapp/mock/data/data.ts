import { Forum, Group, GroupMessage, Notification, User, UserMessage, FriendsList, groupMember } from './type'


// 登录用户一   





// 模拟的用户数据
export const users: User[] = [
    { id: 1, username: 'student1', nickname: '美咲', password: 'student1', avatar: 'src/assets/image/avatar_1.jpg', type: 'student', signature: "这个人很懒没有留下什么！", token: 'user1' },
    { id: 2, username: 'student2', nickname: '爱丽丝', password: 'student2', avatar: 'src/assets/image/avatar_2.jpg', type: 'student', signature: "", token: 'user2' },
    { id: 3, username: 'student3', nickname: '丽斯娅', password: 'student3', avatar: 'src/assets/image/avatar_3.jpg', type: 'student', signature: "", token: 'user3' },
    { id: 4, username: 'admin1', nickname: '莉亚', password: 'student4', avatar: 'src/assets/image/avatar_4.jpg', type: 'admin', signature: "", token: 'user4' },
    { id: 5, username: 'teacher1', nickname: '艾莉', password: 'student5', avatar: 'src/assets/image/avatar_5.jpg', type: 'teacher', signature: "", token: 'user5' },
    { id: 6, username: 'admin4', nickname: '莉莉丝', password: 'student6', avatar: 'src/assets/image/avatar_6.jpg', type: 'teacher', signature: "", token: 'user6' },
    { id: 7, username: 'admin5', nickname: '蒂法', password: 'student7', avatar: 'src/assets/image/avatar_7.jpg', type: 'student', signature: "", token: 'user7' },
];

// 模拟的聊天消息数据
export const userMessages: UserMessage[] = [
    { id: 1, senderId: 1, receiverId: 2, content: 'Hello friend!', sendTime: '2023-09-15 10:00:00' },
    { id: 2, senderId: 2, receiverId: 1, content: 'Hi there!', sendTime: '2023-09-15 10:01:00' },
    { id: 3, senderId: 1, receiverId: 3, content: 'How are you?', sendTime: '2023-09-15 11:00:00' },
    { id: 4, senderId: 5, receiverId: 1, content: 'I am good, thanks!', sendTime: '2023-09-15 11:01:01' },
    { id: 5, senderId: 3, receiverId: 1, content: 'I am good, thanks!', sendTime: '2023-09-15 11:20:00' },
    { id: 6, senderId: 3, receiverId: 1, content: 'I am good, thanks!', sendTime: '2023-09-15 11:30:00' },
];

// 模拟的群组数据
export const groups: Group[] = [
    { id: 1, groupName: '一家亲', owner_id: 1, avatar: 'src/assets/image/avatar_8.jpg', signature: '' },
    { id: 2, groupName: '舔狗一家', owner_id: 1, avatar: 'src/assets/image/avatar_9.jpg', signature: '' },
    { id: 3, groupName: '女神', owner_id: 1, avatar: 'src/assets/image/avatar_10.jpg', signature: '' },
    { id: 4, groupName: '动漫谈论', owner_id: 4, avatar: 'src/assets/image/avatar_11.jpg', signature: '' },
    { id: 5, groupName: '游戏之家', owner_id: 5, avatar: 'src/assets/image/avatar_12.jpg', signature: '' },

];

// 模拟的群组成员数据
export const groupMembers: groupMember[] = [

    // 一群
    { id: 1, group_id: 1, uid: 1 },
    { id: 6, group_id: 1, uid: 2 },
    { id: 6, group_id: 1, uid: 4 },
    { id: 6, group_id: 1, uid: 5 },
    { id: 6, group_id: 1, uid: 6 },
    // 二群
    { id: 2, group_id: 2, uid: 1 },
    { id: 6, group_id: 2, uid: 2 },
    { id: 6, group_id: 2, uid: 4 },
    { id: 6, group_id: 2, uid: 5 },
    { id: 6, group_id: 2, uid: 7 },
    // 三群
    { id: 3, group_id: 3, uid: 1 },
    { id: 3, group_id: 3, uid: 6 },
    { id: 3, group_id: 3, uid: 5 },
    { id: 3, group_id: 3, uid: 2 },
    { id: 3, group_id: 3, uid: 3 },
    // 四群
    { id: 4, group_id: 4, uid: 4 },
    { id: 4, group_id: 4, uid: 1 },
    { id: 4, group_id: 4, uid: 3 },
    { id: 4, group_id: 4, uid: 2 },
    { id: 4, group_id: 4, uid: 7 },
    // 五群
    { id: 5, group_id: 5, uid: 5 },
    { id: 5, group_id: 5, uid: 2 },
    { id: 5, group_id: 5, uid: 3 },
    { id: 5, group_id: 5, uid: 7 },
    { id: 5, group_id: 5, uid: 6 },
    { id: 5, group_id: 5, uid: 4 },
];
// 模拟好友成员数据
export const friendsList: FriendsList[] = [
    { id: 1, uid: 1, friendId: 2 },
    { id: 1, uid: 2, friendId: 1 },
    { id: 1, uid: 1, friendId: 3 },
    { id: 1, uid: 3, friendId: 1 },
    { id: 1, uid: 1, friendId: 4 },
    { id: 1, uid: 4, friendId: 1 },
    { id: 1, uid: 1, friendId: 5 },
    { id: 1, uid: 5, friendId: 1 },
]
// 模拟的群组消息数据
export const groupMessages: GroupMessage[] = [
    { id: 1, group_id: 1, senderId: 1, content: 'Welcome to the group!', sendTime: '2023-09-15 12:00:00' },
    { id: 2, group_id: 1, senderId: 2, content: 'Thank you!', sendTime: '2023-09-15 12:01:00' },
    { id: 3, group_id: 2, senderId: 1, content: 'Let\'s discuss the project', sendTime: '2023-09-15 13:00:00' },
    { id: 4, group_id: 2, senderId: 7, content: 'Sure, I have some ideas!', sendTime: '2023-09-15 13:01:00' },
];
export const forum: Forum[] = [
    { id: 1, senderId: 1, collect: 0, like: 0, content: "", title: "", img: "", sendTime: '2023-09-15 13:00:00', sort: "" },

]

export const notification: Notification[] = []