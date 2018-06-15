package com.nycloud.admin.service;

import com.nycloud.admin.dto.SysUserDto;
import com.nycloud.admin.mapper.*;
import com.nycloud.admin.model.SysRole;
import com.nycloud.admin.model.SysUser;
import com.nycloud.admin.model.SysUserGroupPk;
import com.nycloud.admin.model.SysUserRolePk;
import com.nycloud.admin.vo.SysUserDetail;
import com.nycloud.admin.vo.SysUserInfo;
import com.nycloud.common.utils.ListUtils;
import com.nycloud.common.utils.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/9 0009
 * @modified By:
 * @version: 1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserService extends BaseService<SysUserMapper, SysUser> {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private SysUserGroupPkMapper sysUserGroupPkMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRolePkMapper sysUserRolePkMapper;

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

    public void update(SysUserDto dto) {
        long userId = Long.valueOf(dto.getId());
        int newGroupId = Integer.valueOf(dto.getGroupId());

        // 判断是否修改用户组
        SysUserGroupPk pk = new SysUserGroupPk();
        pk.setUserId(userId);
        SysUserGroupPk newPk = sysUserGroupPkMapper.selectOne(pk);
        if (newPk == null) {
            pk.setGroupId(newGroupId);
            sysUserGroupPkMapper.insert(pk);
        } else {
            final int originalGroupId = newPk.getGroupId();
            if (originalGroupId != newGroupId) {
                Map<String, Object> map = new HashMap<String, Object>(3) {{
                    put("originalGroupId", originalGroupId);
                    put("newGroupId", newGroupId);
                    put("userId", userId);
                }};
                sysUserGroupPkMapper.updateGroupUser(map);
            }
        }

        SysUser sysUser = this.selectById(userId);
        sysUser.setUsername(dto.getUsername());
        sysUser.setName(dto.getName());
        sysUser.setState(Integer.valueOf(dto.getState()));
        this.mapper.updateByPrimaryKey(sysUser);
    }

    /**
     * 删除用户，在删除用户的同时，需要先删除用户和角色还有用户之前的关联关系
     * @param id 用户ID
     */
    public void delete(Long id) {
        super.deleteById(id);
        // 删除用户和角色的关联
        SysUserRolePk userRolePk = new SysUserRolePk();
        userRolePk.setUserId(id);
        sysUserRolePkMapper.delete(userRolePk);
        // 删除和用户组之间的关联
        SysUserGroupPk userGroupPk = new SysUserGroupPk();
        userGroupPk.setUserId(id);
        sysUserGroupPkMapper.delete(userGroupPk);
        // 删除用户
        this.deleteById(id);
    }

    public SysUserInfo selectUserGroupInfo(Long userId) {
        return this.mapper.selectUserGroup(userId);
    }

    public SysUserDetail selectUserDetail(Long userId) {
        Map<String, Object> userMap = new HashMap<String, Object>(1) {{
            put("userId", userId);
        }};
        List<SysRole> roleList = new ArrayList<>();
        List<SysRole> userRoles = sysRoleMapper.selectUserRoles(userMap);
        if (!ListUtils.isEmpty(userRoles)) {
            roleList.addAll(userRoles);
        }
        SysUserInfo userInfo = this.selectUserGroupInfo(userId);
        if (!StringUtils.isEmpty(userInfo.getName())) {
            Map<String, Object> groupMap = new HashMap<String, Object>(1) {{
                put("groupId", userInfo.getGroupId());
            }};
            List<SysRole> groupRoles = sysRoleMapper.selectUserGroupRoles(groupMap);
            if (!ListUtils.isEmpty(groupRoles)) {
                roleList.addAll(groupRoles);
            }
        }
        SysUserDetail userDetail = new SysUserDetail();
        userDetail.setUserGroupInfo(userInfo);
        // 用户角色和用户组角色 合并后角色去重
        if (!ListUtils.isEmpty(roleList)) {
            List<String> roleCodes = new ArrayList<>();
            roleList = roleList.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(SysRole::getId))), ArrayList::new));
            roleList.forEach(item -> roleCodes.add(item.getCode()));
            userDetail.setRoleCodes(roleCodes);
            userDetail.setRoleList(roleList);
        }
        userDetail.setResourceList(sysResourceMapper.selectUserResources(userId));
        return userDetail;
    }

    public SysUser selectUserResources(Long userId) {
        SysUser sysUser = this.mapper.selectByPrimaryKey(userId);
        if (sysUser != null) {
            sysUser.setResourceList(sysResourceMapper.selectUserResources(sysUser.getId()));
        }
        return sysUser;
    }

}
