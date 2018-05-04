package com.nycloud.auth.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

/**
 * @description: 通用Http响应类
 * @author: super.wu
 * @date: Created in 2018/4/27 0027
 * @modified By:
 * @version: 1.0
 **/
@Data
public class HttpResponse<T>{

    /** 响应编码 **/
    private int code = Constants.RESULT_SUCCESS;
    /** 响应消息 **/
    private String msg;
    /** 返回数据 **/
    private  T data;

    public HttpResponse success() {
        return this.success(null);
    }

    public HttpResponse success(T data) {
        this.code = Constants.RESULT_SUCCESS;
        this.data = data;
        return this;
    }

    public HttpResponse error(int code) {
        return this.error(code, null, null);
    }

    public HttpResponse error(int code, String msg) {
        return this.error(code, msg, null);
    }

    public HttpResponse error(int code, String msg, T data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
        return this;
    }

    public String toJsonString() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }
}
