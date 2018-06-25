package com.nycloud.admin.controller;

import com.nycloud.admin.dto.SysUserDto;
import com.nycloud.admin.model.SysUser;
import com.nycloud.admin.security.UserEntity;
import com.nycloud.admin.service.SysPermissionMenuServicePk;
import com.nycloud.admin.service.SysUserService;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.HttpResponse;
import com.nycloud.security.annotation.PreAuth;
import com.nycloud.security.annotation.ResourcesMapping;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
public class SysUserController {

    private final String URL_MAPPING = "api/sysUser";

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermissionMenuServicePk sysPermissionMenuPkService;

    @ApiOperation(value = "用户添加", notes = "根据UserDto保存用户对象")
    @ResourcesMapping(element = "添加", code = "sys_user_add")
    @PreAuth("hasAuthority('sys_user_add')")
    @PostMapping(URL_MAPPING)
    public HttpResponse add(@Validated @RequestBody SysUserDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || StringUtils.isBlank(dto.getPassword())) {
            return HttpResponse.errorParams();
        }
        sysUserService.save(dto);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "角色删除", notes = "根据角色id删除角色信息")
    @ResourcesMapping(element = "删除", code = "sys_user_delete")
    @PreAuth("hasAuthority('sys_user_delete')")
    @DeleteMapping(URL_MAPPING + "/{id}")
    public HttpResponse delete(@PathVariable Long id) {
        sysUserService.delete(id);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "用户查询", notes = "可分页并可根据用户名称模糊检索")
    @ResourcesMapping(element = "查询", code = "sys_user_query")
    @PreAuth("hasAuthority('sys_user_query')")
    @GetMapping(URL_MAPPING)
    public HttpResponse query(RequestDto requestDto) {
        requestDto.setKey("username");
        return HttpResponse.resultSuccess(sysUserService.findByPageList(requestDto));
    }

    @ApiOperation(value = "用户修改", notes = "根据传递的SysUser对象来更新, SysUser对象必须包含id")
    @ResourcesMapping(element = "修改", code = "sys_user_update")
    @PreAuth("hasAuthority('sys_user_update')")
    @PutMapping
    public HttpResponse update(@Validated @RequestBody SysUserDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || StringUtils.isBlank(dto.getId())) {
            return HttpResponse.errorParams();
        }
        sysUserService.update(dto);
        return HttpResponse.resultSuccess();
    }

    @ApiOperation(value = "用户详情查询", notes = "根据id查询用户详细信息")
    @ResourcesMapping(element = "查询详情", code = "sys_user_detail")
    @PreAuth("hasAuthority('sys_user_detail')")
    @GetMapping(URL_MAPPING + "/{id}")
    public HttpResponse info(@PathVariable String id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(Long.valueOf(id));
        return HttpResponse.resultSuccess(sysUserService.selectOne(sysUser));
    }

    @ApiOperation(value = "用户编辑信息查询", notes = "根据id查询用户修改信息")
    @PreAuth("hasAuthority('sys_user_edit_info')")
    @ResourcesMapping(element = "查询详情", code = "sys_user_edit_info")
    @GetMapping(URL_MAPPING + "/edit/{id}")
    public HttpResponse editInfo(@PathVariable String id) {
        return HttpResponse.resultSuccess(sysUserService.selectUserGroupInfo(Long.valueOf(id)));
    }

    @ApiOperation(value = "获取登陆授权后的用户信息", notes = "根据授权Authentication中UserEntity中的userId获取")
    @ResourcesMapping(element = "查询", code = "sys_user_info")
    @PreAuth("hasAuthority('sys_user_info')")
    @GetMapping(URL_MAPPING + "/userInfo")
    public HttpResponse userInfo(Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        SysUser sysUser = new SysUser();
        sysUser.setId(userEntity.getUserId());
        return HttpResponse.resultSuccess(sysUserService.selectOne(sysUser));
    }

    @ApiOperation(value = "用户可用菜单树查询", notes = "根据用户权限查询已分配好的菜单")
    @ResourcesMapping(element = "查询", code = "sys_user_menu_tree")
    @PreAuth("hasAuthority('sys_user_menu_tree')")
    @GetMapping(URL_MAPPING + "/userMenuTree")
    public HttpResponse userMenuTree() {
        return HttpResponse.resultSuccess(sysPermissionMenuPkService.loadPermissionMenuTree(1));
    }

    @ApiOperation(value = "查询用户名是否存在", notes = "根据用户Id查询分配的角色权限下面的资源列表")
    @ResourcesMapping(element = "查询", code = "sys_user_name_exist")
    @PreAuth("hasAuthority('sys_user_name_exist')")
    @GetMapping(URL_MAPPING + "/checkUserNameIsExist")
    public HttpResponse checkUserNameIsExist(@RequestParam String username) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        return HttpResponse.resultSuccess(sysUserService.selectOne(sysUser) != null ? true : false);
    }

    @ApiOperation(value = "用户所有可用资源查询", notes = "根据用户Id查询分配的角色权限下面的资源列表")
    @ResourcesMapping(element = "查询", code = "sys_user_resources")
    @PreAuth("hasAuthority('sys_user_resources')")
    @GetMapping("public/" + URL_MAPPING + "/userResources")
    public HttpResponse userResources(Long userId) {
        return HttpResponse.resultSuccess(sysUserService.selectUserResources(userId));
    }

}
