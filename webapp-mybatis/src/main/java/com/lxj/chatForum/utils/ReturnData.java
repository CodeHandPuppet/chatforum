package com.lxj.chatForum.utils;

import com.google.gson.annotations.Expose;

public class ReturnData {
    @Expose
    int code;
    @Expose
    String message;
    @Expose
    Object data;

    public ReturnData() {
    }

    public ReturnData(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ReturnData success(Object data){
        return new ReturnData(200,"ok",data);
    }

   public static ReturnData error(Object data){
        return new ReturnData(201,"error",data);
    }
}
