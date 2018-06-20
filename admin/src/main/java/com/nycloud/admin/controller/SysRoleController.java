package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysPermission;
import com.nycloud.admin.model.SysRole;
import com.nycloud.admin.service.SysRoleService;
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
 * @date: Created in 2018/5/10 0010
 * @modified By:
 * @version: 1.0
 **/
@RestController
@RequestMapping(value = "api/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "角色添加", notes = "根据SysRole对象创建角色")
    @ResourcesMapping(elements = "添加", code = "sys_role_add")
    @PreAuth("hasAuthority('sys_role_add')")
    @PostMapping
    public HttpResponse add(@Validated @RequestBody SysRole role, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysRoleService.insert(role);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "角色删除", notes = "根据角色id删除角色信息")
    @ResourcesMapping(elements = "删除", code = "sys_role_delete")
    @PreAuth("hasAuthority('sys_role_delete')")
    @DeleteMapping("/{id}")
    public HttpResponse delete(@PathVariable int id) {
        sysRoleService.deleteById(id);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "角色查询", notes = "可分页并可根据角色名称模糊检索")
    @ResourcesMapping(elements = "删除", code = "sys_role_query")
    @PreAuth("hasAuthority('sys_role_query')")
    @GetMapping
    public HttpResponse query(RequestDto requestDto) {
        requestDto.setKey("name");
        return HttpResponse.resultSuccess(sysRoleService.findByPageList(requestDto));
    }

    @ApiOperation(value = "角色修改", notes = "根据传递的SysRole对象来更新, SysRole对象必须包含id")
    @ResourcesMapping(elements = "修改", code = "sys_role_update")
    @PreAuth("hasAuthority('sys_role_update')")
    @PutMapping
    public HttpResponse update(@Validated @RequestBody SysRole sysRole, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysRoleService.updateById(sysRole);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "用户详情查询", notes = "根据id查询用户详细信息")
    @ResourcesMapping(elements = "查询详情", code = "sys_role_detail")
    @PreAuth("hasAuthority('sys_role_detail')")
    @GetMapping("/{id}")
    public HttpResponse info(@PathVariable int id) {
        return HttpResponse.resultSuccess(sysRoleService.selectById(id));
    }

    @ApiOperation(value = "角色是否已存在", notes = "根据SysRole对象设定的字段值来查询判断")
    @ResourcesMapping(elements = "查询", code = "sys_role_exist")
    @PreAuth("hasAuthority('sys_role_exist')")
    @GetMapping("/exist")
    public HttpResponse exist(SysRole sysRole) {
        if (sysRole == null) {
            return HttpResponse.errorParams();
        }
        return HttpResponse.resultSuccess(sysRoleService.selectOne(sysRole) != null);
    }

}
