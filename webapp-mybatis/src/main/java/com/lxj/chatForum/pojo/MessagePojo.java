package com.lxj.chatForum.pojo;

import lombok.Data;

@Data
public class MessagePojo {

    private Integer id;
    private Integer uid;
    //    private Integer receiver_id;
    private Integer receiverId;

    private String content;
    //    private String send_time;
    private String sendTime;

}
