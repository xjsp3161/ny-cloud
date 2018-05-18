package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysRole;
import com.nycloud.admin.service.SysRoleService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
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

    @ApiOperation(value = "角色列表查询", notes = "可分页并可根据角色名称模糊检索")
    @GetMapping
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("name");
        return new HttpResponse().success(sysRoleService.findByPageList(requestDto));
    }

    @ApiOperation(value = "角色保存", notes = "根据SysRole对象创建角色")
    @PostMapping
    public HttpResponse save(@Validated @RequestBody SysRole role, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysRoleService.insert(role);
        return new HttpResponse().success();
    }

}
