// import { forum } from './data/data'
// import { Forum } from './data/type'


// // 1 user 发送论坛     /api/forum/sendForum post
// // 2 user 获取点赞最多的论坛   /api/forum/bestLisk post
// // 3 user 获取收藏最多的论坛  /api/forum/bestCollect get


// export default [
//     // {
//     //     url: '/api/forum/send',
//     //     method: 'post',
//     //     response: ({ body }) => {
//     //         let { content, senderId } = body
//     //         forum.push({ id: forum.length + 1, senderId, content, like: 0, collect: 0 ,});
//     //         return {
//     //             code: 200,
//     //             message: 'ok',
//     //             data: { forum }
//     //         }
//     //     }
//     // },
//     {
//         url: '/api/forum/bestlike',
//         method: 'get',
//         response: () => {
//             let m: number = 0;
//             forum.forEach(item => {
//                 if (item.like >= m) {
//                     m = item.like;
//                 }
//             })
//             return {
//                 code: 200,
//                 message: 'ok',
//                 data: {
//                     result: forum[m]
//                 }
//             }
//         }
//     },
//     {
//         url: '/api/forum/bestcollect',
//         method: 'get',
//         response: () => {
//             let m: number = 0;
//             forum.forEach(item => {
//                 if (item.like >= m) {
//                     m = item.collect;
//                 }
//             })
//             return {
//                 code: 200,
//                 message: 'ok',
//                 data: {
//                     result: forum[m]
//                 }
//             }
//         }
//     },


// ]