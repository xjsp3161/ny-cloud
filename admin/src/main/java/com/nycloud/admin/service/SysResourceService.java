package com.nycloud.admin.service;

import com.nycloud.admin.mapper.SysResourceMapper;
import com.nycloud.admin.model.SysResource;
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
public class SysResourceService extends BaseService<SysResourceMapper, SysResource> {

}
