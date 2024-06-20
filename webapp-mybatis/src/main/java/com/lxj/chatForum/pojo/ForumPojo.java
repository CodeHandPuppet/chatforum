package com.lxj.chatForum.pojo;


import lombok.Data;

@Data
public class ForumPojo {
    private Integer id;
    private Integer uid;
    private String title;
    private String content;
    private String intro;
    private String img;
    private Integer likes;
    private Integer collect;

    private Integer sortId;

    //  private String create_time;
    private String createTime;

    //  private String update_time;
    private String updateTime;
    //   实体类
    private SortPojo sort;
    private UserPojo user;

}
