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

    public static ResponseResult success(Object data){
        return new ResponseResult(200,"ok",data);
    }

    public static ResponseResult error(Object data){
        return new ResponseResult(201,"error",data);
    }
}
