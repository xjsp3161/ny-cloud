package com.nycloud.auth.model;

import lombok.Data;
import java.util.List;

@Data
public class SysUser {

    private Long id;

    private String username;

    private String password;

    private String nickName;

    private Long createTime;

    private Long lastPasswordChange;

    private Boolean enable;

    private String authorities;

    private List<SysResource> resourceList;

}