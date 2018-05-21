package com.nycloud.auth.model;

import lombok.Data;

@Data
public class SysResource {

    private Integer id;

    private String name;

    private String url;

    private String urlRequestType;

    private String description;

}