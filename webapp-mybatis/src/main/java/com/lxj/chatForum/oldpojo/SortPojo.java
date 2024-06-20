package com.lxj.chatForum.oldpojo;

public class SortPojo {
    private Integer id;
    private String sortName;
    private String intor;
    private boolean isLook;
    private String create_time;
    private String update_time;
    private Integer uid;

    public SortPojo() {
    }

    public SortPojo(Integer id, String sortName, String intor, boolean isLook, String create_time,
                    String update_time, Integer uid) {
        this.id = id;
        this.sortName = sortName;
        this.intor = intor;
        this.isLook = isLook;
        this.create_time = create_time;
        this.update_time = update_time;
        this.uid = uid;
    }

    public SortPojo(Integer id, String sortName) {
        this.id = id;
        this.sortName = sortName;
    }

    @Override
    public String toString() {
        return "SortPojo{" +
                "id=" + id +
                ", sortName='" + sortName + '\'' +
                ", intor='" + intor + '\'' +
                ", isLook=" + isLook +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", uid=" + uid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getIntor() {
        return intor;
    }

    public void setIntor(String intor) {
        this.intor = intor;
    }

    public boolean isLook() {
        return isLook;
    }

    public void setLook(boolean look) {
        isLook = look;
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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
