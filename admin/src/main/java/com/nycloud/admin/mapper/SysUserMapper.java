package com.nycloud.admin.mapper;

import com.nycloud.admin.model.SysUser;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser> {

    /**
     * 根据角色加载用户列表
     * @param map 包含roleId和name模糊检索
     * @return
     */
    List<SysUser> selectRoleUsers(Map<String, Object> map);

    /**
     * 加载不属于当前角色的用户列表
     * @param map 包含roleId和name模糊检索
     * @return
     */
    List<SysUser> selectRoleNoExistUsers(Map<String, Object> map);

}