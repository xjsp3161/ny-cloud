package com.nycloud.admin.service;

import com.nycloud.admin.mapper.SysUserRolePkMapper;
import com.nycloud.admin.model.SysUserRolePk;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/14 0014
 * @modified By:
 * @version: 1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserRolePkService extends BaseService<SysUserRolePkMapper, SysUserRolePk> {

    public Integer batchInsert(List<SysUserRolePk> list) {
        return this.mapper.insertUserRoles(list);
    }

    public Integer batchDelete(Integer roleId, Integer [] userIds) {
        Map<String, Object> map = new HashMap(){{
           put("roleId", roleId);
           put("userIds", userIds);
        }};
        return this.mapper.deleteUserRoles(map);
    }
}
