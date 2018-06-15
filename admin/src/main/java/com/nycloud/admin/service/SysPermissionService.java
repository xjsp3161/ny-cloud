package com.nycloud.admin.service;

import com.nycloud.admin.mapper.SysPermissionMapper;
import com.nycloud.admin.mapper.SysPermissionMenuPkMapper;
import com.nycloud.admin.mapper.SysPermissionResourcePkMapper;
import com.nycloud.admin.model.SysPermission;
import com.nycloud.admin.model.SysPermissionMenuPk;
import com.nycloud.admin.model.SysPermissionResourcePk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/15 0015
 * @modified By:
 * @version: 1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermissionService extends BaseService<SysPermissionMapper, SysPermission> {

    @Autowired
    private SysPermissionMenuPkMapper sysPermissionMenuPkMapper;

    @Autowired
    private SysPermissionResourcePkMapper sysPermissionResourcePkMapper;

    public void delete(int id) {
        // 删除关联菜单
        SysPermissionMenuPk permissionMenuPk = new SysPermissionMenuPk();
        permissionMenuPk.setPermissionId(id);
        sysPermissionMenuPkMapper.delete(permissionMenuPk);
        // 删除关联资源
        SysPermissionResourcePk permissionResourcePk = new SysPermissionResourcePk();
        permissionResourcePk.setPermissionId(id);
        sysPermissionResourcePkMapper.delete(permissionResourcePk);
        // 删除权限
        this.deleteById(id);
    }
}
