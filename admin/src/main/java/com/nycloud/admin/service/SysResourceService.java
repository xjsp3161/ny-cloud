package com.nycloud.admin.service;

import com.nycloud.admin.mapper.SysResourceMapper;
import com.nycloud.admin.model.SysResource;
import com.nycloud.common.utils.ListUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public List<SysResource> selectSysResourceTree() {
        List<SysResource> allSysResources = this.mapper.selectAllResources();
        List<SysResource> sysResources = new ArrayList<>();
        SysResource parent = null;
        for (int i = 0; i < allSysResources.size(); i ++) {
            SysResource item = allSysResources.get(i);
            if (item.getLevel() == 1) {
                sysResources.add(item);
            } else {
                if (parent == null || !item.getParentId().equals(parent.getId())) {
                    for (int j = 0; j < sysResources.size(); j ++) {
                        SysResource parentItem = sysResources.get(j);
                        if (item.getParentId().equals(parentItem.getId())) {
                            parent = parentItem;
                            parent.setChildren(new ArrayList<>());
                            break;
                        }
                    }
                }
                parent.getChildren().add(item);
            }
        }
        return sysResources;
    }


}
