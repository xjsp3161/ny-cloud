package com.nycloud.admin.controller;

import com.nycloud.admin.dto.SysUserDto;
import com.nycloud.admin.model.SysUserGroup;
import com.nycloud.admin.service.SysUserGroupService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
import com.nycloud.security.annotation.PreAuth;
import com.nycloud.security.annotation.ResourcesMapping;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/2 0002
 * @modified By:
 * @version: 1.0
 **/
@RestController
@RequestMapping(value = "api/sysUserGroup")
public class SysUserGroupController {

    @Autowired
    private SysUserGroupService sysUserGroupService;

    @ApiOperation(value = "用户组添加", notes = "根据SysUserGroup对象创建用户组")
    @ResourcesMapping(elements = "删除", code = "sys_user_group_add")
    @PreAuth("hasAuthority('sys_user_group_add')")
    @PostMapping
    public HttpResponse save(@RequestBody SysUserGroup sysUserGroup, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysUserGroupService.insert(sysUserGroup);
        return new HttpResponse().success();
    }

    @ApiOperation(value = "用户组删除", notes = "根据用户组id删除用户组信息")
    @ResourcesMapping(elements = "删除", code = "sys_user_group_delete")
    @PreAuth("hasAuthority('sys_user_group_delete')")
    @DeleteMapping("/{id}")
    public HttpResponse delete(@PathVariable int id) {
        sysUserGroupService.deleteById(id);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "用户组查询", notes = "可分页并可根据用户组名称模糊检索")
    @ResourcesMapping(elements = "查询", code = "sys_user_group_query")
    @PreAuth("hasAuthority('sys_user_group_query')")
    @GetMapping
    public HttpResponse query(RequestDto requestDto) {
        requestDto.setKey("name");
        return new HttpResponse().success(sysUserGroupService.findByPageList(requestDto));
    }

    @ApiOperation(value = "用户修改", notes = "根据传递的SysUserGroup对象来更新, SysUserGroup对象必须包含id")
    @ResourcesMapping(elements = "修改", code = "sys_user_group_update")
    @PreAuth("hasAuthority('sys_user_group_update')")
    @PutMapping
    public HttpResponse update(@Validated @RequestBody SysUserGroup dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysUserGroupService.updateById(dto);
        return HttpResponse.resultSuccess();
    }

}
