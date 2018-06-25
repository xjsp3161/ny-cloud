package com.nycloud.admin.controller;

import com.nycloud.admin.dto.RolePermissionDto;
import com.nycloud.admin.model.SysRolePermissionPk;
import com.nycloud.admin.service.SysRolePermissionServicePk;
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
@RequestMapping(value = "api/sysRolePermission")
public class SysRolePermissionController {

    @Autowired
    private SysRolePermissionServicePk sysRolePermissionPkService;

    @ApiOperation(value = "角色关联权限批量保存",  notes = "保存多个SysRolePermissionPk对象")
    @ResourcesMapping(element = "添加", code = "sys_role_permission_add")
    @PreAuth("hasAuthority('sys_role_permission_add')")
    @PostMapping("/batchAdd")
    public HttpResponse batchAdd(@RequestBody List<SysRolePermissionPk> list) {
        if (list == null || list.size() == 0) {
            return HttpResponse.errorParams();
        }
        sysRolePermissionPkService.batchInsert(list);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "角色关联权限批量删除",  notes = "根据角色id和多个权限id删除关联")
    @ResourcesMapping(element = "删除", code = "sys_role_permission_delete")
    @PreAuth("hasAuthority('sys_role_permission_delete')")
    @PostMapping("/batchDelete")
    public HttpResponse batchDelete(@Validated @RequestBody RolePermissionDto dto) {
        if (dto.getPermissionIds() == null || dto.getPermissionIds().length == 0) {
            return HttpResponse.errorParams();
        }
        sysRolePermissionPkService.batchDelete(dto.getRoleId(), dto.getPermissionIds());
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "角色未关联权限查询", notes = "根据角色id查询该角色未关联的权限并返回权限列表")
    @ResourcesMapping(element = "查询", code = "sys_role_no_permission")
    @PreAuth("hasAuthority('sys_role_no_permission')")
    @GetMapping("/roleNoRelationPermissionList")
    public HttpResponse roleNoRelationPermissionList(RolePermissionDto dto) {
        return HttpResponse.resultSuccess(sysRolePermissionPkService.loadRoleNoRelationPermissions(dto));
    }

    @ApiOperation(value = "角色已关联权限查询",  notes = "根据角色id查询该角色已关联的权限并返回权限列表")
    @ResourcesMapping(element = "查询", code = "sys_role_permission")
    @PreAuth("hasAuthority('sys_role_permission')")
    @GetMapping("/rolePermissionList")
    public HttpResponse rolePermissionList(RolePermissionDto dto) {
        return HttpResponse.resultSuccess(sysRolePermissionPkService.loadRolePermissions(dto));
    }

}
