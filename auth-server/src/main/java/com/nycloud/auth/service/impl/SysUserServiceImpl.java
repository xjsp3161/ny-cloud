package com.nycloud.auth.service.impl;

import com.nycloud.auth.dao.SysResourceMapper;
import com.nycloud.auth.dao.SysUserMapper;
import com.nycloud.auth.model.SysUser;
import com.nycloud.auth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/4/28 0028
 * @modified By:
 * @version: 1.0
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    private SysResourceMapper resourceMapper;

    @Override
    public SysUser findByUserName(String username) {
        return sysUserMapper.findSysUserByUserName(username);
    }

    public SysUser selectUserResources(Integer userId) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (sysUser != null) {
            sysUser.setResourceList(resourceMapper.selectUserResources(sysUser.getId()));
        }
        return sysUser;
    }


}
