package com.lxj.chatForum.pojo;


import lombok.Data;

@Data
public class ForumLikePojo {

    private Integer id;

    private Integer forumId;

    private Integer uid;

    private String createTime;

    private String updateTime;

}
