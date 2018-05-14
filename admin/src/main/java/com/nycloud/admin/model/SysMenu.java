package com.nycloud.admin.model;


import javax.persistence.Id;

public class SysMenu {

    /** 菜单Id **/
    @Id
    protected Integer id;
    /** 菜单标题 **/
    protected String title;
    /** 菜单接口路径 **/
    protected String url;
    /** 菜单图标 **/
    protected String icon;
    /** 父级Id **/
    protected Integer parentId;
    /** 排序 **/
    protected Integer sort;
    /** 等级 **/
    protected Integer level;
    /** 前端国际化名称 **/
    protected String name;
    /** 前端访问路径 **/
    protected String path;
    /** 前端组件 **/
    protected String component;
    /** 描述 **/
    protected String description;
    /** 是否启用 **/
    protected Integer enable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

}