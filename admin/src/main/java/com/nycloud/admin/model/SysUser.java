package com.nycloud.admin.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import javax.persistence.Id;
import java.util.List;

@Data
public class SysUser {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
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