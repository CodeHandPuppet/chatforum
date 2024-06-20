package com.lxj.chatForum.pojo;


import lombok.Data;

@Data
public class AddressPojo {
    private Integer id;
    private String name;
    private String intro;
    private String addressImg;
    private String content;
    private String createTime;
    private Boolean isLook;

}
