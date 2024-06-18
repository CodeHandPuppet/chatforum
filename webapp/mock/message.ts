// import { userMessages, groupMessages, users, groups, groupMembers } from './data/data'
// import { GroupMessage, UserMessage } from './data/type';

// //  用消息列表返回出用户列表  和 群列表
// // return{
// //  一个请求是返回用户的message对象
// // 一个请求是返回message对象的聊天消息
// //


// export default [
//     {
//         url: '/api/message/getMessageList',
//         method: 'get',
//         response: (request) => {
//             const token = request.headers.token;
//             if (!token) {
//                 return {
//                     code: 201,
//                     message: '没有用户token'
//                 }
//             }
//             const messageList: { type: string, list: { id: number, name: string, avatar: string, signature: string }[] }[] = []
//             let friendInfo = getUserFriendInfoByToken(token);
//             let groupInfo = getUserGroupInfoByToken(token)
//             messageList.push({ type: 'friend', list: friendInfo! });
//             // messageList.push({ type: 'group', list: groupInfo! });
//             return {
//                 data: messageList
//             }
//         }
//     },
//     {
//         url: '/api/message/memberMessage',
//         method: 'post',
//         response: (request) => {
//             const token = request.headers.token;
//             const { type, id } = request.body;
//             let messageArr: UserMessage[] | GroupMessage[] = [];
//             let groupMemberInfo: {
//                 avatar: string;
//                 name: string;
//                 signature: string;
//                 id: number;
//             }[] = []
//             if (type == 'friend') {
//                 messageArr = getChatHistoryByUserIDs(getId(token), id);
//             }
//             else if (type == 'group') {
//                 messageArr = getChatHistoryByGroupIDs(id);
//                 const memberIds = messageArr.map(member => member.sender_id);
//                 groupMemberInfo = users.filter(user => memberIds.includes(user.id)).map(item => {
//                     const { avatar, nickname: name, id, signature } = item;
//                     return {
//                         avatar, name, signature, id
//                     }
//                 });
//             }
//             return {
//                 code: 200,
//                 message: 'ok',
//                 data: {
//                     messageArr,
//                     groupMemberInfo
//                 }
//             }
//         }

//     }

// ]
// // token 获取  id
// export const getId = (token: string) => {
//     const user = users.find(item => {
//         return item.token == token;
//     })
//     return user?.id;
// }

// function getUserFriendInfoByToken(token) {
//     // 根据 token 查找用户对象
//     const user = users.find((u) => u.token === token);
//     if (!user) {
//         return null; // 若找不到用户，则返回 null
//     }

//     const friendInfo: { id: number, name: string, avatar: string, signature: string }[] = []; // 存储好友信息的数组

//     // 遍历 userMessages 数组
//     for (const message of userMessages) {
//         if (message.sender_id === user.id) {
//             // 找到发送者为该用户的消息
//             const friend = users.find((u) => u.id === message.receiver_id);
//             if (friend) {
//                 // 找到接收者的好友用户对象
//                 const { id, nickname, avatar, signature } = friend;
//                 const temp = friendInfo.find(item => item.id === id);
//                 if (!temp) friendInfo.push({ id, name: nickname, avatar, signature });
//             }
//         }
//         if (message.receiver_id === user.id) {
//             // 找到接收者为该用户的消息
//             const friend = users.find((u) => u.id === message.sender_id);
//             if (friend) {
//                 // 找到接收者的好友用户对象
//                 const { id, nickname, avatar, signature } = friend;
//                 const temp = friendInfo.find(item => item.id === id);
//                 if (!temp) friendInfo.push({ id, name: nickname, avatar, signature });
//             }
//         }
//     }

//     return friendInfo;
// }

// function getUserGroupInfoByToken(token) {
//     // 根据 token 查找用户对象
//     const user = users.find((u) => u.token === token);
//     if (!user) {
//         return null; // 若找不到用户，则返回 null
//     }

//     const groupInfo: { id: number, name: string, avatar: string, signature: string }[] = []; // 存储好友信息的数组

//     // 遍历 userMessages 数组
//     const ids = getGroupsIdByUserToken(token);
//     for (const message of groupMessages) {
//         if (ids.includes(message.group_id)) {
//             // 找到发送者为该用户的消息
//             const group = groups.find((u) => u.id === message.group_id);
//             if (group) {
//                 // 找到接收者的好友用户对象
//                 const { id, group_name: name, avatar, signature } = group;
//                 const temp = groupInfo.find(item => item.id === id);
//                 if (!temp) groupInfo.push({ id, name: name, avatar, signature });
//             }
//         }

//     }

//     return groupInfo;
// }

// // 通过token返回用户的群
// const getGroupsIdByUserToken = (token) => {
//     const user = users.find((user) => user.token === token);
//     if (!user) {
//         return [];
//     }

//     const userGroups = groupMembers
//         .filter((member) => member.user_id === user.id)
//         .map((member) => member.group_id);

//     return userGroups;
// };

// const getChatHistoryByUserIDs = (userID1, userID2) => {
//     const chatHistory = userMessages.filter(
//         (message) =>
//             (message.sender_id === userID1 && message.receiver_id === userID2) ||
//             (message.sender_id === userID2 && message.receiver_id === userID1)
//     )

//     return chatHistory;
// };
// // 通过群聊id返回聊天记录
// const getChatHistoryByGroupIDs = (groupId) => {
//     const chatHistory = groupMessages.filter(
//         (message) =>
//             message.group_id == groupId
//     )
//     return chatHistory;
// };