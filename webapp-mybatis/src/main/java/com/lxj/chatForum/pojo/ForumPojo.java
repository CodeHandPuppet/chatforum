package com.lxj.chatForum.pojo;

public class ForumPojo {
    private Integer id;
    private Integer uid;
    private String title;
    private String content;
    private String intor;
    private String img;
    private Integer likes;
    private Integer collect;
    private String create_time;
    private String update_time;
    private Integer sortId;
//   实体类
    private SortPojo sort;
    private UserPojo user;


    public ForumPojo() {
    }

    @Override
    public String toString() {
        return "ForumPojo{" +
                "id=" + id +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", intor='" + intor + '\'' +
                ", img='" + img + '\'' +
                ", likes=" + likes +
                ", collect=" + collect +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", sortId=" + sortId +
                ", sort=" + sort +
                ", user=" + user +
                '}';
    }

    //    visForum
    public ForumPojo(int id, int uid, String title, String content, String intor, Integer sortId, String img,
                     int likes, int collect, String create_time,SortPojo sortpojo,UserPojo user) {
        this.id = id;
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.intor = intor;
        this.sortId = sortId;
        this.img = img;
        this.likes = likes;
        this.collect = collect;
        this.create_time = create_time;
        this.sort=sortpojo;
        this.user=user;
    }
    public static ForumPojo createVisForum(int id, int uid, String title, String content, String intor, Integer sortId, String img,
                                    int likes, int collect, String create_time,SortPojo sortpojo,UserPojo userPojo){
        return new ForumPojo(id,uid,title,content,intor,sortId,img,likes,collect,create_time,sortpojo,userPojo);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SortPojo getSort() {
        return sort;
    }

    public void setSort(SortPojo sort) {
        this.sort = sort;
    }

    public UserPojo getUser() {
        return user;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIntro() {
        return intor;
    }

    public void setIntro(String intor) {
        this.intor = intor;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
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

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public SortPojo getSortPojo() {
        return sort;
    }

    public void setSortPojo(SortPojo sort) {
        this.sort = sort;
    }
    public String getIntor() {
        return intor;
    }

    public void setIntor(String intor) {
        this.intor = intor;
    }
}

//class ForumCollect {
//    private Integer id;
//    private Integer forum_id;
//    private Integer uid;
//    private String create_time;
//    private String update_time;
//
//    public ForumCollect(Integer id, Integer forum_id, Integer uid, String create_time, String update_time) {
//        this.id = id;
//        this.forum_id = forum_id;
//        this.uid = uid;
//        this.create_time = create_time;
//        this.update_time = update_time;
//    }
//
//    public ForumCollect() {
//    }
//}
//class ForumLike {
//    private Integer id;
//    private Integer forum_id;
//    private Integer uid;
//    private String create_time;
//    private String update_time;
//
//    public ForumLike() {
//    }
//
//    public ForumLike(Integer id, Integer forum_id, Integer uid, String create_time, String update_time) {
//        this.id = id;
//        this.forum_id = forum_id;
//        this.uid = uid;
//        this.create_time = create_time;
//        this.update_time = update_time;
//    }
//}
