// import { users, friendsList, groupMembers, groups } from './data/data'


// // 通过token获取用户信息 id
// export const getId = (token: string) => {


//     const user = users.find(item => {
//         return item.token == token;
//     })

//     return user?.id;
// }

// // 获取用户列表

// export default [

//     {
//         url: '/api/chatList/friends',
//         method: 'get',
//         response: (request) => {
//             const token = request.headers.token;
//             const user_id = getId(token);
//             const friendsId: number[] = []
//             friendsList.forEach(item => {
//                 if (user_id == item.user_id) {
//                     friendsId.push(item.friend_id);
//                 }
//             })
//             const userInfo = users.map(item => {
//                 if (friendsId.includes(item.id)) {
//                     return {
//                         id: item.id,
//                         name: item.nickname,
//                         avatar: item.avatar,
//                         signature: item.signature,
//                     }
//                 }
//             }).filter(item => {
//                 return item;
//             })
//             return {
//                 code: 200,
//                 message: 'ok',
//                 data: [
//                     ...userInfo
//                 ]
//             }
//         }
//     },
//     {
//         url: '/api/chatList/groups',
//         method: 'get',
//         response: (request) => {
//             const token = request.headers.token;
//             const user_id = getId(token);
//             const groupsId: number[] = []
//             groupMembers.forEach(item => {
//                 if (user_id == item.user_id) {
//                     groupsId.push(item.group_id);
//                 }
//             })
//             const groupInfo = groups.map(item => {
//                 if (groupsId.includes(item.id)) {
//                     return {
//                         id: item.id,
//                         name: item.group_name,
//                         avatar: item.avatar,
//                         signature: item.signature,
//                     }
//                 }
//             }).filter(item => {
//                 return item;
//             })
//             return {
//                 code: 200,
//                 message: 'ok',
//                 data: [
//                     ...groupInfo
//                 ]
//             }
//         }
//     }

// ]

