package com.lxj.chatForum.pojo;


import lombok.Data;

@Data
public class UserPojo {
    private Integer id;

    private String username;

    private String nickname;

    private String password;

    private String avatar;

    private String signature;

    private String type;

    private String token;

    private Integer level;

    private Integer likes;

    private Integer collect;

    //    private Integer forum_number;
    private Integer forumNumber;

    private String homeLink;

    private String address;


}
