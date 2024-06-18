package com.lxj.chatForum.dto;

import com.google.gson.annotations.Expose;
import com.lxj.chatForum.utils.ReturnData;

public class ResponseResult {

    int code;

    String message;

    Object data;

    public ResponseResult() {
    }

    public ResponseResult(int code, String message, Object data) {
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
