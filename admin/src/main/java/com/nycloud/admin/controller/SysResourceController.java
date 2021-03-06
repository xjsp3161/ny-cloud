package com.nycloud.admin.controller;

import com.nycloud.admin.model.SysResource;
import com.nycloud.admin.service.SysResourceService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
import com.nycloud.security.annotation.PreAuth;
import com.nycloud.security.annotation.ResourcesMapping;
import io.swagger.annotations.Api;
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
@Api(value = "资源管理", tags = {"资源管理接口"})
@RestController
@RequestMapping(value = "api/sysResource")
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    @ApiOperation(value = "资源增加", notes = "根据SysResource对象增加资源")
    @ResourcesMapping(element = "增加", code = "sys_resource_add")
    @PreAuth("hasAuthority('sys_resource_add')")
    @PostMapping
    public HttpResponse add(@Validated @RequestBody SysResource sysResource, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysResourceService.insert(sysResource);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "资源删除", notes = "根据资源id删除资源信息")
    @ResourcesMapping(element = "删除", code = "sys_resource_delete")
    @PreAuth("hasAuthority('sys_resource_delete')")
    @DeleteMapping("/{id}")
    public HttpResponse delete(@PathVariable int id) {
        sysResourceService.deleteById(id);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "资源查询", notes = "可分页并可根据权限名称模糊检索")
    @ResourcesMapping(element = "删除", code = "sys_resource_query")
    @PreAuth("hasAuthority('sys_resource_query')")
    @GetMapping
    public HttpResponse query(RequestDto requestDto) {
        requestDto.setKey("name");
        return HttpResponse.resultSuccess(sysResourceService.findByPageList(requestDto));
    }

    @ApiOperation(value = "资源修改", notes = "根据传递的SysPermission对象来更新, SysPermission对象必须包含id")
    @ResourcesMapping(element = "修改", code = "sys_resource_update")
    @PreAuth("hasAuthority('sys_resource_update')")
    @PutMapping
    public HttpResponse update(@Validated @RequestBody SysResource sysResource, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpResponse.errorParams();
        }
        sysResourceService.updateById(sysResource);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "资源详情查询", notes = "根据id查询资源详细信息")
    @ResourcesMapping(element = "修改", code = "sys_resource_info")
    @PreAuth("hasAuthority('sys_resource_info')")
    @GetMapping("/{id}")
    public HttpResponse info(@PathVariable Long id) {
        return HttpResponse.resultSuccess(sysResourceService.selectById(id));
    }

    @ApiOperation(value = "资源是否已存在", notes = "根据SysResource对象设定的字段值来查询判断")
    @ResourcesMapping(element = "修改", code = "sys_resource_exist")
    @PreAuth("hasAuthority('sys_resource_exist')")
    @GetMapping("/exist")
    public HttpResponse exist(SysResource resource) {
        if (resource == null) {
            return HttpResponse.errorParams();
        }
        return HttpResponse.resultSuccess(sysResourceService.selectOne(resource) != null);
    }

    @ApiOperation(value = "资源树查询", notes = "查询所有的资源并返回父子的树状结构")
    @ResourcesMapping(element = "查询", code = "sys_resource_tree")
    @PreAuth("hasAuthority('sys_resource_tree')")
    @GetMapping("/tree")
    public HttpResponse tree() {
        return HttpResponse.resultSuccess(sysResourceService.selectSysResourceTree());
    }


}
