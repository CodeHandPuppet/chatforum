package com.lxj.chatForum.pojo;


import lombok.Data;

@Data
public class FriendsListPojo {
    private Integer id;
    private Integer uid;
    //    private Integer friend_id;
    private Integer friendId;
    private String appellation;


    //
    private UserPojo friendInfo;

}
