package com.lxj.chatForum.oldpojo;


public class CommentPojo {
//    数据库里面存的
    private Integer id;
    private Integer forumId;
    private Integer uid;
    private String parentId;
    private String content;
    private Integer likes;
    private String contentImg;
    private String createTime;
    private String address;
//转化为实体类   多添加的
    private UserPojo user;

    public CommentPojo() {}

    // 用于返回 前端 的构造函数
    public CommentPojo(Integer id, Integer uid, String parentId, String content,
                       Integer likes, String contentImg, String createTime, String address, UserPojo user) {
        this.id = id;
        this.uid = uid;
        this.parentId = parentId;
        this.content = content;
        this.likes = likes;
        this.contentImg = contentImg;
        this.createTime = createTime;
        this.address = address;
        this.user = user;
    }

    public CommentPojo(Integer id, Integer forumId, Integer uid, String parentId, String content, Integer likes,
                       String contentImg, String createTime, String address) {
        this.id = id;
        this.forumId = forumId;
        this.uid = uid;
        this.parentId = parentId;
        this.content = content;
        this.likes = likes;
        this.contentImg = contentImg;
        this.createTime = createTime;
        this.address = address;
    }

    public static CommentPojo createVisComment(int id, int uid, String parentId, String content, int likes, String contentImg, String createTime, String address, UserPojo userPojo) {
        return new CommentPojo(id,uid,parentId,content,likes,contentImg,createTime,address,userPojo);
    }

    @Override
    public String toString() {
        return "CommentPojo{" +
                "id=" + id +
                ", forumId=" + forumId +
                ", uid=" + uid +
                ", parentId=" + parentId +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", contentImg='" + contentImg + '\'' +
                ", createTime='" + createTime + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserPojo getUser() {
        return user;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }
}

//class CommentLikePojo {
//    private Integer id;
//    private Integer comment_id;
//    private Integer uid;
//    private String send_time;
//    private String update_time;
//
//    public CommentLikePojo() {
//    }
//
//    public CommentLikePojo(Integer id, Integer comment_id, Integer uid, String send_time, String update_time) {
//        this.id = id;
//        this.comment_id = comment_id;
//        this.uid = uid;
//        this.send_time = send_time;
//        this.update_time = update_time;
//    }
//
//    @Override
//    public String toString() {
//        return "CommentLikePojo{" +
//                "id=" + id +
//                ", comment_id=" + comment_id +
//                ", uid=" + uid +
//                ", send_time='" + send_time + '\'' +
//                ", update_time='" + update_time + '\'' +
//                '}';
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getComment_id() {
//        return comment_id;
//    }
//
//    public void setComment_id(Integer comment_id) {
//        this.comment_id = comment_id;
//    }
//
//    public Integer getUid() {
//        return uid;
//    }
//
//    public void setUid(Integer uid) {
//        this.uid = uid;
//    }
//
//    public String getSend_time() {
//        return send_time;
//    }
//
//    public void setSend_time(String send_time) {
//        this.send_time = send_time;
//    }
//
//    public String getUpdate_time() {
//        return update_time;
//    }
//
//    public void setUpdate_time(String update_time) {
//        this.update_time = update_time;
//    }
//}
//
