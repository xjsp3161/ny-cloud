package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysMenu;
import com.nycloud.admin.service.SysMenuService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/10 0010
 * @modified By:
 * @version: 1.0
 **/
@RestController
@RequestMapping(value = "api/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "菜单列表查询", notes = "可分页并可根据菜单名称模糊检索")
    @GetMapping
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("name");
        return new HttpResponse().success(sysMenuService.findByPageList(requestDto));
    }

    @ApiOperation(value = "菜单保存", notes = "根据SysMenu对象创建用户")
    @PostMapping
    public HttpResponse save(@Validated @RequestBody SysMenu sysMenu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysMenuService.insert(sysMenu);
        return new HttpResponse().success();
    }

    @ApiOperation(value = "菜单更新", notes = "根据传递的SysMenu对象来更新, SysMenu对象必须包含id")
    @PutMapping
    public HttpResponse update(@Validated @RequestBody SysMenu sysMenu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysMenuService.updateById(sysMenu);
        return new HttpResponse().success();
    }

    @ApiOperation(value = "菜单详情查询", notes = "根据id查询菜单详细信息")
    @DeleteMapping("/info")
    public HttpResponse delete(@RequestParam Integer id) {
        sysMenuService.deleteById(id);
        return new HttpResponse().success();
    }

    @ApiOperation(value = "菜单树查询",  notes = "查询所有可用菜单并返回树状结构")
    @GetMapping("/tree")
    public HttpResponse tree() {
        return new HttpResponse().success(sysMenuService.loadAllMenuTree());
    }

}
