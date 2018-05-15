package com.nycloud.admin.vo;

import com.nycloud.admin.model.SysMenu;
import java.util.List;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/11 0011
 * @modified By:
 * @version: 1.0
 **/
public class MenuTree extends SysMenu {

    /**
     * 扩展属性 父级名称
     **/
    private String parentName;
    /**
     * 扩展属性 子级列表
     **/
    private List<MenuTree> children;

    public MenuTree(SysMenu menu) {
        this.id = menu.getId();
        this.title = menu.getTitle();
        this.url = menu.getUrl();
        this.icon = menu.getIcon();
        this.parentId = menu.getParentId();
        this.sort = menu.getSort();
        this.level = menu.getLevel();
        this.name = menu.getName();
        this.path = menu.getPath();
        this.component = menu.getComponent();
        this.description = menu.getDescription();
        this.enable = menu.getEnable();
    }


    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }
}