import { CommentApi } from "undraw-ui";

export function buildCommentTree(comments: CommentApi[]): CommentApi[] {

    for (const comment of comments) {
        if (comment.parentId == 'null') {
            comment.parentId = null;
        }
    }
    const commentMap: { [key: string]: CommentApi } = {};
    const rootComments: CommentApi[] = [];
    // Create a map of comments using their IDs as keys
    for (const comment of comments) {
        comment.reply = { total: 0, list: [] }; // Initialize the reply field
        commentMap[comment.id.toString()] = comment;
    }

    // Iterate over the comments to find and attach their parent comments
    for (const comment of comments) {
        const parentId = comment.parentId;
        if (parentId && commentMap[parentId.toString()]) {
            commentMap[parentId.toString()].reply!.list.push(comment);
            commentMap[parentId.toString()].reply!.total++;
        } else {
            comment.parentId = null;
            rootComments.push(comment);
        }
    }

    return rootComments;
}



// config.comments = [
//     {
//       id: 4,
//       parentId: null,
//       uid: 2, //   用户 id
//       address: "来自上海", //
//       content:
//         "缘生缘灭，缘起缘落，我在看别人的故事，别人何尝不是在看我的故事?别人在演绎人生，我又何尝不是在这场戏里?谁的眼神沧桑了谁?我的眼神，只是沧桑了自己[喝酒]",
//       likes: 2,
//       contentImg: "https://gitee.com/undraw/undraw-ui/raw/master/public/docs/normal.webp",
//       createTime: dayjs().subtract(10, "minute").toString(),
//       user: {
//         username: "落🤍尘",
//         avatar:
//           "https://static.juzicon.com/avatars/avatar-200602130320-HMR2.jpeg?x-oss-process=image/resize,w_100",
//         level: 6, // 用户等级
//         homeLink: "/100", //跳转的标签
//       },
//     },
//     {
//       id: 5,
//       parentId: 4,
//       uid: 4, //   用户 id
//       address: "来自上海", //
//       content:
//         "缘生缘灭，缘起缘落，我在看别人的故事，别人何尝不是在看我的故事?别人在演绎人生，我又何尝不是在这场戏里?谁的眼神沧桑了谁?我的眼神，只是沧桑了自己[喝酒]",
//       likes: 2,
//       contentImg: "https://gitee.com/undraw/undraw-ui/raw/master/public/docs/normal.webp",
//       createTime: dayjs().subtract(10, "minute").toString(),
//       user: {
//         username: "落🤍尘",
//         avatar:
//           "https://static.juzicon.com/avatars/avatar-200602130320-HMR2.jpeg?x-oss-process=image/resize,w_100",
//         level: 6, // 用户等级
//         homeLink: "/100", //跳转的标签
//       },
//     },
//   ];