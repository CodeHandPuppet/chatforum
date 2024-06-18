package com.lxj.chatForum.pojo;

public class UserPojo {
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String avatar;
    private String signature;
    private String type;
    private String token;
    private Integer level;
    private Integer likes;
    private Integer collect;
    private Integer forum_number;
    private String homeLink;
    private String address;

    public UserPojo() {}

    //   用户
    public UserPojo(String username, String avatar, Integer level, String homeLink) {
        this.username = username;
        this.avatar = avatar;
        this.level = level;
        this.homeLink = homeLink;
    }
// 原始的
    public UserPojo(Integer id, String username, String nickname, String password, String avatar, String signature,
                    String type, String token, Integer level,  Integer likes,
                    Integer collect, Integer forum_number, String homeLink, String address) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.avatar = avatar;
        this.signature = signature;
        this.type = type;
        this.token = token;
        this.level = level;

        this.likes = likes;
        this.collect = collect;
        this.forum_number = forum_number;
        this.homeLink = homeLink;
        this.address = address;
    }
//userInfo
    public UserPojo(Integer id, String username, String nickname, String avatar, String signature, String type,
                    Integer level, Integer likes, Integer collect, Integer forum_number, String homeLink, String address) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.avatar = avatar;
        this.signature = signature;
        this.type = type;
        this.level = level;

        this.likes = likes;
        this.collect = collect;
        this.forum_number = forum_number;
        this.homeLink = homeLink;
        this.address = address;
    }

    public static UserPojo createUserInfo(Integer id, String username, String nickname, String avatar, String signature,
                                          String type, Integer level,  Integer likes,
                                          Integer collect, Integer forum_number, String homeLink, String address) {
        return  new UserPojo(id,username,nickname,avatar,signature,type,level,likes,collect,
                forum_number,homeLink,address);
    }

    @Override
    public String toString() {
        return "UserPojo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", signature='" + signature + '\'' +
                ", type='" + type + '\'' +
                ", token='" + token + '\'' +
                ", level=" + level +
                ", likes=" + likes +
                ", collect=" + collect +
                ", forum_number=" + forum_number +
                ", homeLink='" + homeLink + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getForum_number() {
        return forum_number;
    }

    public void setForum_number(Integer forum_number) {
        this.forum_number = forum_number;
    }

    public String getHomeLink() {
        return homeLink;
    }

    public void setHomeLink(String homeLink) {
        this.homeLink = homeLink;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
