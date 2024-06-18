// import { users } from './data/data'


// // 登录后一定要token传递参数



// export default [
//     {
//         url: "/api/user/login",
//         method: "post",
//         response: ({ body }) => {
//             const { username, password } = body;


//             const checkUser = users.find((user) => {
//                 return user.username == username && user.password == password;
//             })
//             if (!checkUser) {
//                 return {
//                     code: 201,
//                     messages: '',
//                     data: { body }
//                 }
//             }
//             return {
//                 code: 200,
//                 message: "ok",
//                 data: {
//                     token: checkUser.token
//                 }
//             }
//         }
//     }, {
//         url: "/api/user/info",
//         method: "get",
//         response: (request) => {

//             const token = request.headers.token;
//             const checkUser = users.find((user) => {
//                 console.log(user.token);

//                 return user.token == token;

//             })

//             if (checkUser) {
//                 const { nickname, username, avatar, id, signature, type } = checkUser!;
//                 return {
//                     code: 200,
//                     message: "ok",
//                     data: {
//                         nickname,
//                         username,
//                         avatar,
//                         id,
//                         signature,
//                         type
//                     }
//                 }
//             }
//             else {
//                 return {
//                     code: 200,
//                     message: "error",
//                     data: {}
//                 }
//             }


//         }
//     }
// ]
