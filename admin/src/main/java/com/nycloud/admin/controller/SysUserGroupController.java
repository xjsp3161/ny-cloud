package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysUserGroup;
import com.nycloud.admin.service.SysUserGroupService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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

    @ApiOperation(value = "用户组列表查询", notes = "可分页并可根据用户组名称模糊检索")
    @GetMapping
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("name");
        return new HttpResponse().success(sysUserGroupService.findByPageList(requestDto));
    }

    @ApiOperation(value = "用户组保存", notes = "根据SysUserGroup对象创建用户组")
    @PostMapping
    public HttpResponse save(@RequestBody SysUserGroup sysUserGroup, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysUserGroupService.insert(sysUserGroup);
        return new HttpResponse().success();
    }

}
