package com.nycloud.admin.model;

import lombok.Data;
import javax.persistence.Id;
import java.util.List;

@Data
public class SysUser {

    @Id
    private Long id;

    private String username;

    private String password;

    private Long createTime;

    private Long lastPasswordChange;

    private Integer enable;

    private String authorities;

    private String name;

    private List<SysResource> resourceList;

}