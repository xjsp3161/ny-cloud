package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysResource;
import com.nycloud.admin.service.SysResourceService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
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
@RequestMapping(value = "api/sysResource")
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    @GetMapping("")
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("name");
        return new HttpResponse().success(sysResourceService.findByPageList(requestDto));
    }

    @PostMapping
    public HttpResponse save(@Validated @RequestBody SysResource sysResource, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysResourceService.insert(sysResource);
        return new HttpResponse().success();
    }

    @GetMapping("/info")
    public HttpResponse info(@RequestParam Integer id) {
        return new HttpResponse().success(sysResourceService.selectById(id));
    }

    @GetMapping("/nameExist")
    public HttpResponse nameExist(@RequestParam String name) {
        SysResource sysResource = new SysResource();
        sysResource.setName(name);
        return new HttpResponse().success(sysResourceService.selectOne(sysResource) != null);
    }

    @GetMapping("/urlExist")
    public HttpResponse urlExist(@RequestParam String url) {
        SysResource sysResource = new SysResource();
        sysResource.setName(url);
        return new HttpResponse().success(sysResourceService.selectOne(sysResource) != null);
    }

    @GetMapping("/exist")
    public HttpResponse exist(SysResource resource) {
        if (resource == null) {
            return HttpResponse.errorParams();
        }
        return new HttpResponse().success(sysResourceService.selectOne(resource) != null);
    }

}
