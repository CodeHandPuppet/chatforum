import { CommentData, ForumData, SortData, UserData, UserMessageData } from "@/type/websiteDataType";
import { CommentApi } from "undraw-ui";


export interface ResponseData {
    code: number;
    message: string;
}

//  保存  或者  删除   返回的
export interface SaveOrDeleteResponseData extends ResponseData {
    data: any
}

/*
*    forum
*/

export interface VisForum extends ForumData {
    sort?: SortData;
    user?: UserData;
}

export interface VisForumResponseData extends ResponseData {
    data: VisForum[] | VisForum,

}

export interface CommentLikeOrCollectIdResponseData extends ResponseData {
    data: {
        Ids: number[]
    }
}

/*
*    user
*/

// 用户个人信息
export interface UserDataResponseData extends ResponseData {
    data: UserData
}

// 登录 成功
export interface UserLoginResponseData extends ResponseData {
    data: any
}
// 注册成功

/*
*    comment
*/

// 定义评论返回类型
export interface VisComment {
    id: number;
    uid: number;
    parentId: number | null;
    content: string;
    likes: number;
    contentImg: string;
    createTime: string;
    address: string;
    user: {
        username: string;
        avatar: string;
        level: number;
        homeLink: string;
    }
}

export interface CommentDataResponseData extends ResponseData {
    data: CommentApi[]

}


export interface CommentLikesIdResponseData extends ResponseData {
    data: {
        likeIds: number[]
    }
}


/*
*    Notification
*/



/*
*    sort
*/
export interface SortDataResponseData extends ResponseData {
    data: SortData[]
}
/**
 *
 * 拉坤代码
 */
export interface FrendsListResponseData extends ResponseData {
    data: {
        type: string
        friends: UserData[]
    }
}
export interface ChatFriends {

    appellation: string,
    friendInfo: UserData
}

export interface ChatListResponseData extends ResponseData {
    data: {
        friends: ChatFriends[]
    }
}

export interface MessageArrResponseData extends ResponseData {
    data: UserMessageData[]
}