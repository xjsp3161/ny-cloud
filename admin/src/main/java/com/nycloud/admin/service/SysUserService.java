package com.nycloud.admin.service;

import com.github.pagehelper.PageHelper;
import com.nycloud.admin.mapper.SysUserMapper;
import com.nycloud.admin.model.SysUser;
import com.nycloud.common.dto.RequestDto;
import com.nycloud.common.vo.ResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/9 0009
 * @modified By:
 * @version: 1.0
 **/
@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public ResponsePage<SysUser> findByPageList(RequestDto dto) {
        PageHelper.startPage(dto.getPage(), dto.getSize());
        Example example = new Example(SysUser.class);
        if (!StringUtils.isEmpty(dto.getName())) {
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("username", "%" + dto.getName() + "%");
        }
        List<SysUser> list = sysUserMapper.selectByExample(example);
        return new ResponsePage<>(list);
    }

}
