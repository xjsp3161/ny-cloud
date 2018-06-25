package com.nycloud.admin.controller;

import com.nycloud.admin.dto.UserGroupRoleDto;
import com.nycloud.admin.model.SysUserGroupRolePk;
import com.nycloud.admin.service.SysUserGroupRolePkService;
import com.nycloud.common.vo.HttpResponse;
import com.nycloud.security.annotation.PreAuth;
import com.nycloud.security.annotation.ResourcesMapping;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/18 0018
 * @modified By:
 * @version: 1.0
 **/
@RestController
@RequestMapping(value = "api/sysUserGroupRole")
public class SysUserGroupRoleController {

    @Autowired
    private SysUserGroupRolePkService sysUserGroupRolePkService;

    @ApiOperation(value = "用户组关联角色批量保存",  notes = "保存多个SysRolePermissionPk对象")
    @ResourcesMapping(element = "添加", code = "sys_group_role_add")
    @PreAuth("hasAuthority('sys_group_role_add')")
    @PostMapping("/batchAdd")
    public HttpResponse batchAdd(@RequestBody List<SysUserGroupRolePk> list) {
        if (list == null || list.size() == 0) {
            return HttpResponse.errorParams();
        }
        sysUserGroupRolePkService.batchInsert(list);
        return new HttpResponse().success();
    }

    @ApiOperation(value = "用户组关联角色批量删除",  notes = "根据角色id和多个权限id删除关联")
    @ResourcesMapping(element = "添加", code = "sys_group_role_delete")
    @PreAuth("hasAuthority('sys_group_role_delete')")
    @PostMapping("/batchDelete")
    public HttpResponse batchDelete(@Validated @RequestBody UserGroupRoleDto dto) {
        if (dto.getRoleIds() == null || dto.getRoleIds().length == 0) {
            return HttpResponse.errorParams();
        }
        sysUserGroupRolePkService.batchDelete(dto.getGroupId(), dto.getRoleIds());
        return new HttpResponse().success();
    }

    @ApiOperation(value = "用户组未关联角色查询", notes = "根据角色id查询该角色未关联的权限并返回权限列表")
    @ResourcesMapping(element = "查询", code = "sys_group_no_role")
    @PreAuth("hasAuthority('sys_group_no_role')")
    @GetMapping("/groupNoRelationRoleList")
    public HttpResponse groupNoRelationRoleList(UserGroupRoleDto dto) {
        return HttpResponse.resultSuccess(sysUserGroupRolePkService.loadGroupNoRelationRoles(dto));
    }

    @ApiOperation(value = "用户组已关联角色查询",  notes = "根据角色id查询该角色已关联的权限并返回权限列表")
    @ResourcesMapping(element = "查询", code = "sys_group_role")
    @PreAuth("hasAuthority('sys_group_role')")
    @GetMapping("/groupRoleList")
    public HttpResponse groupRoleList(UserGroupRoleDto dto) {
        return HttpResponse.resultSuccess(sysUserGroupRolePkService.loadGroupRoles(dto));
    }

}
