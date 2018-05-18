package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysResource;
import com.nycloud.admin.service.SysResourceService;
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
 * @date: Created in 2018/5/2 0002
 * @modified By:
 * @version: 1.0
 **/
@RestController
@RequestMapping(value = "api/sysResource")
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    @ApiOperation(value = "资源列表查询", notes = "可分页并可根据权限名称模糊检索")
    @GetMapping
    public HttpResponse index(RequestDto requestDto) {
        requestDto.setKey("name");
        return new HttpResponse().success(sysResourceService.findByPageList(requestDto));
    }

    @ApiOperation(value = "资源保存", notes = "根据SysResource对象创建资源")
    @PostMapping
    public HttpResponse save(@Validated @RequestBody SysResource sysResource, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysResourceService.insert(sysResource);
        return new HttpResponse().success();
    }

    @ApiOperation(value = "资源详情查询", notes = "根据id查询资源详细信息")
    @GetMapping("/info")
    public HttpResponse info(@RequestParam Integer id) {
        return new HttpResponse().success(sysResourceService.selectById(id));
    }

    @ApiOperation(value = "资源是否已存在", notes = "根据SysResource对象设定的字段值来查询判断")
    @GetMapping("/exist")
    public HttpResponse exist(SysResource resource) {
        if (resource == null) {
            return HttpResponse.errorParams();
        }
        return new HttpResponse().success(sysResourceService.selectOne(resource) != null);
    }

}
