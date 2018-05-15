package com.nycloud.admin.service;

import com.nycloud.admin.mapper.SysPermissionMenuPkMapper;
import com.nycloud.admin.model.SysPermissionMenuPk;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/15 0015
 * @modified By:
 * @version: 1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermissionMenuPkService extends BaseService<SysPermissionMenuPkMapper, SysPermissionMenuPk> {

    public Integer batchInsert(List<SysPermissionMenuPk> list) {
        return this.mapper.insertPermissionMenus(list);
    }

    public Integer batchDelete(Integer permissionId, Integer [] menuIds) {
        Map<String, Object> map = new HashMap(){{
            put("permissionId", permissionId);
            put("menuIds", menuIds);
        }};
        return this.mapper.deletePermissionMenus(map);
    }
}
