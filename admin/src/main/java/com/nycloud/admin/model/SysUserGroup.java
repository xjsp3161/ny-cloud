package com.nycloud.admin.model;

import lombok.Data;
import javax.persistence.Id;

@Data
public class SysUserGroup {

    @Id
    private Integer id;

    private String name;

    private String code;

    private String description;


}