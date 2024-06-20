package com.lxj.chatForum.oldpojo;

public class NotificationPojo {
//    id:number | string;   // 主键
//    type: 'AllPeople' | 'AddFriend' | 'Message'   //  varchar 就行 不必约束那值
//    content:string
//    sender_id:string | number   //外键 user
//    receiver_id:string | number | null   // 不是外键 可以为空
//    create_time:string       //  默认值  当前时间
//    update_time:string      //  触发器
    private Integer id;
    private String type;
    private String content;
    private Integer sender_id;
    private Integer receiver_id;
    private String create_time;
    private String update_time;
    private boolean isLook;

    public NotificationPojo() {
    }

    public NotificationPojo(Integer id, String type, String content, Integer sender_id, Integer receiver_id, String create_time, String update_time, boolean isLook) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.create_time = create_time;
        this.update_time = update_time;
        this.isLook = isLook;
    }

    public boolean isLook() {
        return isLook;
    }

    public void setLook(boolean look) {
        isLook = look;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSender_id() {
        return sender_id;
    }

    public void setSender_id(Integer sender_id) {
        this.sender_id = sender_id;
    }

    public Integer getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(Integer receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "NotificationPojo{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", sender_id=" + sender_id +
                ", receiver_id=" + receiver_id +
                ", create_time='" + create_time + '\'' +
                ", update_time=" + update_time +
                '}';
    }
}
