package com.lxj.chatForum.pojo;


import lombok.Data;

@Data
public class SortPojo {
    private Integer id;
    private String sortName;
    private String intro;
    private boolean isLook;
    //    private String create_time;
    private String createTime;
    //    private String update_time;
    private String updateTime;
    private Integer uid;

}
