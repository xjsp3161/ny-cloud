package com.nycloud.admin.mapper;

import com.nycloud.admin.model.SysUser;
import com.nycloud.admin.vo.SysUserInfo;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper extends Mapper<SysUser> {

    SysUserInfo selectUserGroup(Long userId);

}