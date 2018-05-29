package com.nycloud.admin.service;

import com.nycloud.admin.mapper.SysResourceMapper;
import com.nycloud.admin.mapper.SysUserMapper;
import com.nycloud.admin.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/9 0009
 * @modified By:
 * @version: 1.0
 **/
@Service
public class SysUserService extends BaseService<SysUserMapper, SysUser> {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    public SysUser selectUserResources(Long userId) {
        SysUser sysUser = this.mapper.selectByPrimaryKey(userId);
        if (sysUser != null) {
            sysUser.setResourceList(sysResourceMapper.selectUserResources(sysUser.getId()));
        }
        return sysUser;
    }

}
