package com.nycloud.admin.model;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class SysRole {

    @Id
    private Integer id;

    @NotEmpty(message = "角色名称不能为空")
    private String name;

    @NotEmpty(message = "角色编码不能为空")
    private String code;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

}