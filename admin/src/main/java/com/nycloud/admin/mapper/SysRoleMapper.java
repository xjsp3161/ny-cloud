package com.nycloud.admin.mapper;

import com.nycloud.admin.model.SysRole;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
import java.util.Map;

public interface SysRoleMapper extends Mapper<SysRole> {

    /**
     * 加载不属于当前用户的角色列表
     * @param map 包含roleId和name模糊检索
     * @return
     */
    List<SysRole> selectUserNoRoles(Map<String, Object> map);


    List<SysRole> selectUserRoles(Map<String, Object> map);


    List<SysRole> selectUserGroupNoRoles(Map<String, Object> map);


    List<SysRole> selectUserGroupRoles(Map<String, Object> map);


}