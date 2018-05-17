package com.nycloud.admin.service;

import com.nycloud.admin.mapper.SysMenuMapper;
import com.nycloud.admin.model.SysMenu;
import com.nycloud.admin.util.MenuTreeUtil;
import com.nycloud.admin.model.MenuTree;
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
        List<MenuTree> list = this.mapper.selectByEnableAll(1);
        return MenuTreeUtil.generateMenuTree(list);
    }

    public List<MenuTree> loadPermissionNoExistMenus(Integer permissionId){
        List<MenuTree> allList = this.mapper.selectByEnableAll(1);
        List<MenuTree> currentList = this.mapper.selectPermissionNoExistMenus(permissionId);
        return MenuTreeUtil.filterGenerateSortMenu(allList, currentList);
    }

    public List<MenuTree> loadPermissionMenus(Integer permissionId){
        List<MenuTree> allList =this.mapper.selectByEnableAll(1);
        List<MenuTree> currentList = this.mapper.selectPermissionMenus(permissionId);
        return MenuTreeUtil.filterGenerateSortMenu(allList, currentList);
    }

}
