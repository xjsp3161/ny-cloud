package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysMenu;
import com.nycloud.admin.service.SysMenuService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
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
@RequestMapping(value = "api/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("")
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("name");
        return new HttpResponse().success(sysMenuService.findByPageList(requestDto));
    }

    @PostMapping
    public HttpResponse save(@Validated @RequestBody SysMenu sysMenu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysMenuService.insert(sysMenu);
        return new HttpResponse().success();
    }

    @PutMapping
    public HttpResponse update(@Validated @RequestBody SysMenu sysMenu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysMenuService.updateById(sysMenu);
        return new HttpResponse().success();
    }

    @DeleteMapping
    public HttpResponse delete(@PathVariable Integer id) {
        sysMenuService.deleteById(id);
        return new HttpResponse().success();
    }

    @GetMapping("/tree")
    public HttpResponse tree() {
        return new HttpResponse().success(sysMenuService.loadAllMenuTreeList());
    }

}
