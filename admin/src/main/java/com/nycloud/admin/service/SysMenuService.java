package com.nycloud.admin.service;

import com.nycloud.admin.dto.PermissionMenuDto;
import com.nycloud.admin.mapper.SysMenuMapper;
import com.nycloud.admin.model.SysMenu;
import com.nycloud.admin.vo.MenuTree;
import com.nycloud.common.utils.ListUtils;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        return filterMenuTree(convertMenuTreeList(list));
    }

    public List<MenuTree> loadPermissionNoExisMenus(PermissionMenuDto dto){
        Map<String, Object> map = new HashMap<String, Object>(1) {{
            put("permissionId", dto.getPermissionId());
            if (StringUtils.isNotBlank(dto.getName())) {
                put("name", dto.getName());
            }
        }};
        List<SysMenu> list = this.mapper.selectPermissionNoExistMenus(map);
        return filterMenuTree(convertMenuTreeList(list));
    }

    public List<MenuTree> loadPermissionMenus(PermissionMenuDto dto){
        Map <String, Object> map = new HashMap<String, Object>(1) {{
            put("permissionId", dto.getPermissionId());
            if (StringUtils.isNotBlank(dto.getName())) {
                put("name", dto.getName());
            }
        }};
        List<SysMenu> list = this.mapper.selectPermissionMenus(map);
        return filterMenuTree1(convertMenuTreeList(list));
    }

    private List<MenuTree> convertMenuTreeList(List<SysMenu> list) {
        if (ListUtils.isEmpty(list)) {
            return null;
        }
        List<MenuTree> result = new ArrayList<>();
        for (SysMenu sysMenu : list) {
            result.add(new MenuTree(sysMenu));
        }
        return result;
    }

    private List<MenuTree> filterMenuTree1(List<MenuTree> list2) {
        if (ListUtils.isEmpty(list2)) {
            return null;
        }
        List<SysMenu> list1 = this.mapper.selectByEnableAll(1);
        Map<Integer, MenuTree> map1 = new HashMap<>();
        Map<Integer, MenuTree> map2 = new HashMap<>();
        for (int i = 0; i < list1.size(); i ++) {
            map1.put(list1.get(i).getId(), new MenuTree(list1.get(i)));
        }
        for (int j = 0; j < list2.size(); j ++) {
            MenuTree menuTree = new MenuTree(list2.get(j));
            if (menuTree.getLevel() == 1) {
                map2.put(menuTree.getId(), menuTree);
                continue;
            }
            boolean isBreak = false;
            while(!isBreak) {
                MenuTree sub = map2.get(menuTree.getParentId());
                if (sub == null) {
                    sub = map1.get(menuTree.getParentId());
                    map2.put(sub.getId(), sub);
                    if (sub.getLevel() == 1) {
                        map2.put(menuTree.getId(), menuTree);
                        isBreak = true;
                    }
                } else {
                    map2.put(menuTree.getId(), menuTree);
                    isBreak = true;
                }
                menuTree = sub;
            }
        }
        List<MenuTree> sortList = new ArrayList<>();
        Iterator<Map.Entry<Integer, MenuTree>> iterator = map2.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, MenuTree> entry = iterator.next();
            sortList.add(map2.get(entry.getKey()));
        }
        Collections.sort(sortList, new Comparator<MenuTree>() {
            @Override
            public int compare(MenuTree o1, MenuTree o2) {
                if (!o1.getLevel().equals(o2.getLevel())) {
                    return o1.getLevel() - o2.getLevel();
                } else if (o1.getLevel().equals(o2.getLevel()) && o1.getSort() != null && o2.getSort() != null) {
                    return o1.getSort() - o2.getSort();
                } else {
                    return o1.getId() - o2.getId();
                }
            }
        });
        return filterMenuTree(sortList);
    }


    /**
     * 过去节点生成菜单树
     * @param list
     * @return
     */
    private List<MenuTree> filterMenuTree(List<MenuTree> list) {
        List<MenuTree> result = new ArrayList<>();
        if (ListUtils.isEmpty(list)) {
            return result;
        }
        for (MenuTree menu: list) {
            if (menu.getLevel() == 1) {
                menu.setChildren(new ArrayList<>());
                result.add(menu);
            } else {
                for (int i = 0; i < result.size(); i ++) {
                    MenuTree child = result.get(i);
                    if (!menu.getParentId().equals(child.getId())) {
                        this.filterMenuChildTree(result, menu);
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
     * 递归遍历添加子节点
     * @param list
     * @param menu
     */
    private void filterMenuChildTree(List<MenuTree> list, MenuTree menu) {
        for (int i = 0; i < list.size(); i ++) {
            MenuTree sysMenu = list.get(i);
            if (!sysMenu.getId().equals(menu.getParentId())) {
                if (sysMenu.getChildren() != null && sysMenu.getChildren().size() > 0) {
                    this.filterMenuChildTree(sysMenu.getChildren(), menu);
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
