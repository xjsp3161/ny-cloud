package com.nycloud.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: 用户登陆请求
 * @author: super.wu
 * @date: Created in 2018/4/28 0028
 * @modified By:
 * @version: 1.0
 **/
@Data
public class UserDto {

    /** 用户名 **/
    @NotBlank(message = "用户名不能为空")
    private String username;
    /** 密码 **/
    @NotBlank(message = "密码不能为空")
    private String password;
    /** 客户端 pc ios android **/
    @NotBlank(message = "客户端信息不能为空")
    private String clientId;
    /** 入口 代表使用登陆接口的系统 **/
    @NotBlank(message = "入口不能为空")
    private String entrance;
    /** 版本 **/
    @NotBlank(message = "版本不能为空")
    private String version;
}
