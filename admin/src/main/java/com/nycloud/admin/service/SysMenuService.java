package com.nycloud.admin.service;

import com.nycloud.admin.mapper.SysMenuMapper;
import com.nycloud.admin.model.SysMenu;
import com.nycloud.admin.vo.MenuTree;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = Exception.class)
public class SysMenuService extends BaseService<SysMenuMapper, SysMenu> {

    public List<MenuTree> loadAllMenuTreeList() {
        List<SysMenu> list = this.mapper.selectByEnableAll(1);
        List<MenuTree> result = new ArrayList<>();
        for (SysMenu sysMenu: list) {
            MenuTree menu = new MenuTree(sysMenu);
            if (menu.getLevel() == 1) {
                menu.setChildren(new ArrayList<>());
                result.add(menu);
            } else {
                for (int i = 0; i < result.size(); i ++) {
                    MenuTree child = result.get(i);
                    if (!menu.getParentId().equals(child.getId())) {
                        this.parseMenu(result, menu);
                    } else {
                        menu.setParentName(menu.getTitle());
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
    private void parseMenu(List<MenuTree> list, MenuTree menu) {
        for (int i = 0; i < list.size(); i ++) {
            MenuTree sysMenu = list.get(i);
            if (!sysMenu.getId().equals(menu.getParentId())) {
                if (sysMenu.getChildren() != null && sysMenu.getChildren().size() > 0) {
                    this.parseMenu(sysMenu.getChildren(), menu);
                    break;
                } else {
                    continue;
                }
            } else {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<MenuTree>(){{
                        add(menu);
                    }});
                } else {
                    menu.setParentName(sysMenu.getTitle());
                    sysMenu.getChildren().add(menu);
                }
                break;
            }
        }
    }

}
