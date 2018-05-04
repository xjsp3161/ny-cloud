package com.nycloud.auth.service;

import com.nycloud.auth.model.SysUser;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/4/28 0028
 * @modified By:
 * @version: 1.0
 **/
public interface SysUserService {

    SysUser findByUserName(String username);

}
