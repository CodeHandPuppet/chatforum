package com.lxj.chatForum.pojo;

public class FriendsListPojo {
    private Integer id;
    private Integer uid;
    private Integer friend_id;
    private String appellation;
    private UserPojo friendInfo;
    public FriendsListPojo() {
    }

    public FriendsListPojo(Integer id, Integer uid, Integer friend_id, String appellation) {
        this.id = id;
        this.uid = uid;
        this.friend_id = friend_id;
        this.appellation = appellation;
    }

    public UserPojo getFriendInfo() {
        return friendInfo;
    }

    @Override
    public String toString() {
        return "FriendsListPojo{" +
                "id=" + id +
                ", uid=" + uid +
                ", friend_id=" + friend_id +
                ", appellation='" + appellation + '\'' +
                ", friendInfo=" + friendInfo +
                '}';
    }

    public void setFriendInfo(UserPojo friendInfo) {
        this.friendInfo = friendInfo;
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

    public Integer getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(Integer friend_id) {
        this.friend_id = friend_id;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }
}
