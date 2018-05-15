package com.nycloud.admin.mapper;

import com.nycloud.admin.model.SysMenu;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
import java.util.Map;

public interface SysMenuMapper extends Mapper<SysMenu> {

    List<SysMenu> selectByEnableAll(Integer enable);

    /**
     * 根据权限加载已分配的菜单列表
     * @param map 包含permissionId和name模糊检索
     * @return
     */
    List<SysMenu> selectPermissionMenus(Map<String, Object> map);

    /**
     * 加载未分配给当前权限的菜单列表
     * @param map 包含permissionId和name模糊检索
     * @return
     */
    List<SysMenu> selectPermissionNoExistMenus(Map<String, Object> map);
}