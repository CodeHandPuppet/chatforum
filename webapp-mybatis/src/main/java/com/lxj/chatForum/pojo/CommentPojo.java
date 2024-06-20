package com.lxj.chatForum.pojo;

import lombok.Data;

@Data
public class CommentPojo {
    //    数据库里面存的
    private Integer id;
    private Integer forumId;
    private Integer uid;
    private String parentId;
    private String content;
    private Integer likes;
    private String contentImg;
    private String address;

    private String createTime;

    //转化为实体类   多添加的
    private UserPojo user;


}


