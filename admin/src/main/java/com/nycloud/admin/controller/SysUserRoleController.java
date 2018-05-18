package com.nycloud.admin.controller;


import com.nycloud.admin.dto.UserRoleDto;
import com.nycloud.admin.model.SysUser;
import com.nycloud.admin.model.SysUserRolePk;
import com.nycloud.admin.service.SysRoleService;
import com.nycloud.admin.service.SysUserRolePkService;
import com.nycloud.admin.service.SysUserService;
import com.nycloud.common.vo.HttpResponse;
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

    @ApiOperation(value = "用户未关联角色查询", notes = "根据用户id查询该用户未关联的角色并返回角色列表")
    @GetMapping("/userNoRelationRoleList")
    public HttpResponse userNoRelationRoleList(UserRoleDto dto) {
        return HttpResponse.resultSuccess(sysUserRolePkService.loadUserNoRelationRoles(dto));
    }

    @ApiOperation(value = "用户已关联角色查询",  notes = "根据用户id查询该用户已关联的角色并返回角色列表")
    @GetMapping("/userRoleList")
    public HttpResponse userRoleList(UserRoleDto dto) {
        return HttpResponse.resultSuccess(sysUserRolePkService.loadUserRoles(dto));
    }

    @ApiOperation(value = "用户关联角色批量保存",  notes = "保存多个SysUserRolePk对象")
    @PostMapping("/batchSave")
    public HttpResponse batchSave(@RequestBody List<SysUserRolePk> list) {
        if (list == null || list.size() == 0) {
            return HttpResponse.errorParams();
        }
        sysUserRolePkService.batchInsert(list);
        return new HttpResponse().success();
    }

    @ApiOperation(value = "用户关联角色批量删除",  notes = "根据用户id和多个角色id删除关联")
    @PostMapping("/batchDelete")
    public HttpResponse batchDelete(@Validated @RequestBody UserRoleDto dto) {
        if (dto.getRoleIds() == null || dto.getRoleIds().length == 0) {
            return HttpResponse.errorParams();
        }
        sysUserRolePkService.batchDelete(dto.getUserId(), dto.getRoleIds());
        return new HttpResponse().success();
    }

}
