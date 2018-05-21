package com.nycloud.admin.mapper;

import com.nycloud.admin.model.SysResource;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/15 0015
 * @modified By:
 * @version: 1.0
 **/
public interface SysResourceMapper extends Mapper<SysResource>{

    List<SysResource> selectPermissionResources(Map<String, Object> map);

    List<SysResource> selectPermissionNoResources(Map<String, Object> map);

    List<SysResource> selectUserResources(Integer userId);
}
