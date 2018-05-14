package com.nycloud.admin.service;

import com.github.pagehelper.PageHelper;
import com.nycloud.admin.dto.UserRoleDto;
import com.nycloud.admin.mapper.SysUserMapper;
import com.nycloud.admin.model.SysUser;
import com.nycloud.common.vo.ResponsePage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/9 0009
 * @modified By:
 * @version: 1.0
 **/
@Service
public class SysUserService extends BaseService<SysUserMapper, SysUser> {

    public ResponsePage loadRoleNoExistUsers(UserRoleDto dto){
        PageHelper.startPage(dto.getPage(), dto.getSize());
        Map <String, Object> map = new HashMap(){{
            put("roleId", dto.getRoleId());
            if (StringUtils.isNotBlank(dto.getName())) {
                put("name", dto.getName());
            }
        }};
        return new ResponsePage(this.mapper.selectRoleNoExistUsers(map));
    }

    public List<SysUser> loadRoleUsers(UserRoleDto dto){
        Map <String, Object> map = new HashMap(){{
            put("roleId", dto.getRoleId());
            if (StringUtils.isNotBlank(dto.getName())) {
                put("name", dto.getName());
            }
        }};
        return this.mapper.selectRoleUsers(map);
    }


}
