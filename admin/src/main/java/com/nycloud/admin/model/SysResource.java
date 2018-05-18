package com.nycloud.admin.model;

import javax.persistence.Id;

public class SysResource {
    @Id
    private Integer id;

    private String name;

    private String url;

    private String urlRequestType;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUrlRequestType() {
        return urlRequestType;
    }

    public void setUrlRequestType(String urlRequestType) {
        this.urlRequestType = urlRequestType == null ? null : urlRequestType.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}