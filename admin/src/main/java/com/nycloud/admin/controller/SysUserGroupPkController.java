package com.nycloud.admin.controller;

import com.nycloud.admin.dto.UserGroupDto;
import com.nycloud.admin.model.SysUserGroupPk;
import com.nycloud.admin.service.SysUserGroupPkService;
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
 * @date: Created in 2018/6/13 0013
 * @modified By:
 * @version: 1.0
 **/

@RestController
@RequestMapping(value = "api/sysUserGroupPk")
public class SysUserGroupPkController {

    @Autowired
    private SysUserGroupPkService sysUserGroupPkService;

    @ApiOperation(value = "用户组关联用户批量添加",  notes = "添加多个SysUserGroupPk对象")
    @ResourcesMapping(elements = "添加", code = "sys_group_user_add")
    @PreAuth("hasAuthority('sys_group_user_add')")
    @PostMapping("/batchAdd")
    public HttpResponse batchAdd(@RequestBody List<SysUserGroupPk> list) {
        if (list == null || list.size() == 0) {
            return HttpResponse.errorParams();
        }
        sysUserGroupPkService.batchInsert(list);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "用户组关联用户批量删除",  notes = "根据用户id和多个角色id删除关联")
    @ResourcesMapping(elements = "删除", code = "sys_group_user_delete")
    @PreAuth("hasAuthority('sys_group_user_delete')")
    @PostMapping("/batchDelete")
    public HttpResponse batchDelete(@Validated @RequestBody UserGroupDto dto) {
        if (dto.getUserIds() == null || dto.getUserIds().length == 0) {
            return HttpResponse.errorParams();
        }
        sysUserGroupPkService.batchDelete(dto.getGroupId(), dto.getUserIds());
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "用户组未关联用户查询", notes = "根据用户id查询该用户未关联的角色并返回角色列表")
    @ResourcesMapping(elements = "查询", code = "sys_group_no_user")
    @PreAuth("hasAuthority('sys_group_no_user')")
    @GetMapping("/groupNoRelationUserList")
    public HttpResponse groupNoRelationUserList(UserGroupDto dto) {
        return HttpResponse.resultSuccess(sysUserGroupPkService.loadGroupNoRelationUsers(dto));
    }

    @ApiOperation(value = "用户组已关联用户查询",  notes = "根据用户id查询该用户已关联的角色并返回角色列表")
    @ResourcesMapping(elements = "查询", code = "sys_group_user")
    @PreAuth("hasAuthority('sys_group_user')")
    @GetMapping("/groupUserList")
    public HttpResponse groupUserList(UserGroupDto dto) {
        return HttpResponse.resultSuccess(sysUserGroupPkService.loadGroupUsers(dto));
    }

}
