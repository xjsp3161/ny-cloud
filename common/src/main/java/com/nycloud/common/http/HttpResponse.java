package com.nycloud.common.http;

import com.nycloud.common.util.Constant;
import lombok.*;

/**
 * @description: 通用Http返回模型
 * @author: super.wu
 * @date: Created in 2018/4/20 0020
 * @modified By:
 * @version: 1.0
 **/
@Data
@Getter
@Setter
@ToString
public class HttpResponse <T> {

    /** 错误码 **/
    private int code;
    /** 提示消息文本 **/
    private String msg;
    /** 返回数据 **/
    private T data;

    public HttpResponse success() {
        this.code = Constant.HTTP_RESPONSE_SUCCESS_CODE;
        return this;
    }

    public HttpResponse success(T data) {
        this.data = data;
        return success();
    }

    public HttpResponse error(int code) {
        return this.error(code, null, null);
    }

    public HttpResponse error(int code, String msg) {
        return this.error(code, msg, null);
    }

    public HttpResponse error(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        return this;
    }

}

