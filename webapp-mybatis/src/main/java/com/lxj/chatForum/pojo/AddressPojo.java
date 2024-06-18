package com.lxj.chatForum.pojo;

public class AddressPojo {
    private Integer id;
    private String name;
    private String intor;
    private String addressImg;
    private String content;
    private String createTime;
    private Boolean isLook;
    public AddressPojo() {
    }

    public AddressPojo(Integer id, String name, String intor, String addressImg, String content, String createTime, Boolean isLook) {
        this.id = id;
        this.name = name;
        this.intor = intor;
        this.addressImg = addressImg;
        this.content = content;
        this.createTime = createTime;
        this.isLook =isLook;
    }

    @Override
    public String toString() {
        return "AddressPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intor='" + intor + '\'' +
                ", addressImg='" + addressImg + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntor() {
        return intor;
    }

    public void setIntor(String intor) {
        this.intor = intor;
    }

    public String getAddressImg() {
        return addressImg;
    }

    public void setAddressImg(String addressImg) {
        this.addressImg = addressImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isLook() {
        return isLook;
    }
}
