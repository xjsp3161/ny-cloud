package com.nycloud.admin.service;

import com.nycloud.admin.dto.SysUserDto;
import com.nycloud.admin.mapper.SysResourceMapper;
import com.nycloud.admin.mapper.SysUserGroupPkMapper;
import com.nycloud.admin.mapper.SysUserMapper;
import com.nycloud.admin.model.SysUser;
import com.nycloud.admin.model.SysUserGroupPk;
import com.nycloud.admin.vo.SysUserInfo;
import com.nycloud.common.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private SysUserGroupPkMapper sysUserGroupPkMapper;

    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserDto dto) {
        SysUser sysUser = new SysUser();
        sysUser.setId(SnowFlake.getInstance().nextId());
        sysUser.setUsername(dto.getUsername());
        sysUser.setPassword(dto.getPassword());
        sysUser.setName(dto.getName());
        sysUser.setState(Integer.valueOf(dto.getState()));
        Long currentTime = System.currentTimeMillis();
        sysUser.setCreateTime(currentTime);
        sysUser.setLastPasswordChange(currentTime);
        super.insert(sysUser);

        SysUserGroupPk pk = new SysUserGroupPk();
        pk.setUserId(sysUser.getId());
        pk.setGroupId(Integer.valueOf(dto.getGroupId()));
        sysUserGroupPkMapper.insert(pk);
    }

    public SysUserInfo selectUserGroupInfo(Long userId) {
        SysUserInfo sysUserInfo = this.mapper.selectUserGroup(userId);
        return sysUserInfo;
    }

    public SysUser selectUserResources(Long userId) {
        SysUser sysUser = this.mapper.selectByPrimaryKey(userId);
        if (sysUser != null) {
            sysUser.setResourceList(sysResourceMapper.selectUserResources(sysUser.getId()));
        }
        return sysUser;
    }

}
