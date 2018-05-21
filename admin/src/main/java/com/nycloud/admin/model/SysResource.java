package com.nycloud.admin.model;

import lombok.Data;
import javax.persistence.Id;

@Data
public class SysResource {

    @Id
    private Integer id;

    private String name;

    private String url;

    private String urlRequestType;

    private String description;

}