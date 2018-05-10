package com.nycloud.admin.service;

import com.nycloud.admin.mapper.SysMenuMapper;
import com.nycloud.admin.model.SysMenu;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/10 0010
 * @modified By:
 * @version: 1.0
 **/
@Service
public class SysMenuService extends BaseService<SysMenuMapper, SysMenu> {

    public List<SysMenu> loadAllMenuTreeList() {
        List<SysMenu> list = this.mapper.selectAll();
        List<SysMenu> result = new ArrayList<>();
        for (SysMenu menu: list) {
            if (menu.getLevel() == 1) {
                menu.setChildren(new ArrayList<>());
                result.add(menu);
            } else {
                for (int i = 0; i < result.size(); i ++) {
                    SysMenu child = result.get(i);
                    if (!menu.getParentId().equals(child.getId())) {
                        this.parseMenu(result, menu);
                    } else {
                        child.getChildren().add(menu);
                    }
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 无限遍历添加节点
     * @param list
     * @param menu
     */
    private void parseMenu(List<SysMenu> list, SysMenu menu) {
        for (int i = 0; i < list.size(); i ++) {
            SysMenu sysMenu = list.get(i);
            if (!sysMenu.getId().equals(menu.getParentId())) {
                if (sysMenu.getChildren() != null && sysMenu.getChildren().size() > 0) {
                    this.parseMenu(sysMenu.getChildren(), menu);
                    break;
                } else {
                    continue;
                }
            } else {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<SysMenu>(){{
                        add(menu);
                    }});
                } else {
                    sysMenu.getChildren().add(menu);
                }
                break;
            }
        }
    }

}
