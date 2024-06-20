package com.lxj.chatForum.pojo;


import lombok.Data;

@Data
public class NotificationPojo {

    private Integer id;
    private String type;
    private String content;
    //    private Integer sender_id;
    private Integer senderId;

    //    private Integer receiver_id;
    private Integer receiverId;

    //    private String create_time;
    private Integer createTime;

    //    private String update_time;
    private Integer updateTime;

    private boolean isLook;


}
