package com.lxj.chatForum.pojo;

public class MessagePojo {

    private Integer id;
    private Integer uid;
    private Integer receiver_id;
    private String content;
    private String send_time;

    public MessagePojo() {
    }

    public MessagePojo(Integer id, Integer uid, Integer receiver_id, String content, String send_time) {
        this.id = id;
        this.uid = uid;
        this.receiver_id = receiver_id;
        this.content = content;
        this.send_time = send_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(Integer receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }
}
