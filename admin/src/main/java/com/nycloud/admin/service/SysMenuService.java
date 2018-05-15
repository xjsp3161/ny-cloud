package com.nycloud.admin.service;

import com.nycloud.admin.dto.PermissionMenuDto;
import com.nycloud.admin.mapper.SysMenuMapper;
import com.nycloud.admin.model.SysMenu;
import com.nycloud.admin.vo.MenuTree;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return filterMenuTree(list);
    }

    public List<MenuTree> loadPermissionNoExisMenus(PermissionMenuDto dto){
        Map<String, Object> map = new HashMap<String, Object>(1) {{
            put("permissionId", dto.getPermissionId());
            if (StringUtils.isNotBlank(dto.getName())) {
                put("name", dto.getName());
            }
        }};
        List<SysMenu> list = this.mapper.selectPermissionNoExistMenus(map);
        return filterMenuTree(list);
    }

    public List<MenuTree> loadPermissionMenus(PermissionMenuDto dto){
        Map <String, Object> map = new HashMap<String, Object>(1) {{
            put("permissionId", dto.getPermissionId());
            if (StringUtils.isNotBlank(dto.getName())) {
                put("name", dto.getName());
            }
        }};
        List<SysMenu> list = this.mapper.selectPermissionMenus(map);
        return filterMenuTree(list);
    }


    /**
     * 过去节点生成菜单树
     * @param list
     * @return
     */
    private List<MenuTree> filterMenuTree(List<SysMenu> list) {
        List<MenuTree> result = new ArrayList<>();
        if (list == null || list.size() == 0) {
            return result;
        }
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
     * 无限遍历添加添加节点
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
