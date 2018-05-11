package com.nycloud.admin.mapper;

import com.nycloud.admin.model.SysMenu;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

public interface SysMenuMapper extends Mapper<SysMenu> {

    List<SysMenu> selectByEnableAll(Integer enable);
}