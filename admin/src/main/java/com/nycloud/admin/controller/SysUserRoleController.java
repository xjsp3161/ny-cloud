package com.nycloud.admin.controller;

import com.nycloud.admin.dto.UserRoleDto;
import com.nycloud.admin.model.SysUserRolePk;
import com.nycloud.admin.service.SysUserRolePkService;
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
@RequestMapping(value = "api/sysUserRole")
public class SysUserRoleController {

    @Autowired
    private SysUserRolePkService sysUserRolePkService;

    @ApiOperation(value = "用户关联角色批量添加",  notes = "保存多个SysUserRolePk对象")
    @ResourcesMapping(elements = "添加", code = "sys_user_role_add")
    @PreAuth("hasAuthority('sys_user_role_add')")
    @PostMapping("/batchAdd")
    public HttpResponse batchAdd(@RequestBody List<SysUserRolePk> list) {
        if (list == null || list.size() == 0) {
            return HttpResponse.errorParams();
        }
        sysUserRolePkService.batchInsert(list);
        return new HttpResponse().success();
    }

    @ApiOperation(value = "用户关联角色批量删除",  notes = "根据用户id和多个角色id删除关联")
    @ResourcesMapping(elements = "删除", code = "sys_user_role_delete")
    @PreAuth("hasAuthority('sys_user_role_delete')")
    @PostMapping("/batchDelete")
    public HttpResponse batchDelete(@Validated @RequestBody UserRoleDto dto) {
        if (dto.getRoleIds() == null || dto.getRoleIds().length == 0) {
            return HttpResponse.errorParams();
        }
        sysUserRolePkService.batchDelete(dto.getUserId(), dto.getRoleIds());
        return new HttpResponse().success();
    }

    @ApiOperation(value = "用户未关联角色查询", notes = "根据用户id查询该用户未关联的角色并返回角色列表")
    @ResourcesMapping(elements = "查询", code = "sys_user_no_role")
    @PreAuth("hasAuthority('sys_user_no_role')")
    @GetMapping("/userNoRelationRoleList")
    public HttpResponse userNoRelationRoleList(UserRoleDto dto) {
        return HttpResponse.resultSuccess(sysUserRolePkService.loadUserNoRelationRoles(dto));
    }

    @ApiOperation(value = "用户已关联角色查询",  notes = "根据用户id查询该用户已关联的角色并返回角色列表")
    @ResourcesMapping(elements = "查询", code = "sys_user_role")
    @PreAuth("hasAuthority('sys_user_role')")
    @GetMapping("/userRoleList")
    public HttpResponse userRoleList(UserRoleDto dto) {
        return HttpResponse.resultSuccess(sysUserRolePkService.loadUserRoles(dto));
    }

}
