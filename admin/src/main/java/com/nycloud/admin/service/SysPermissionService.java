package com.nycloud.admin.service;

import com.nycloud.admin.mapper.SysPermissionMapper;
import com.nycloud.admin.model.SysPermission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/15 0015
 * @modified By:
 * @version: 1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermissionService extends BaseService<SysPermissionMapper, SysPermission> {
}
