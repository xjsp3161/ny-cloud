package com.nycloud.admin.security;

import lombok.Data;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/6/11 0011
 * @modified By:
 * @version: 1.0
 **/
@Data
public class UserEntity {

    private Long userId;

    private String username;

    public UserEntity(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
