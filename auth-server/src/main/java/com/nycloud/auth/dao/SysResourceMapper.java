package com.nycloud.auth.dao;

import com.nycloud.auth.model.SysResource;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import java.util.List;

/**
 * @author spuer.wu
 */
@Mapper
public interface SysResourceMapper {

    @Select({
            "select",
            "id, name, url, url_request_type, description",
            "from sys_resource",
            "where id in (select resource_id from sys_permission_resource_pk where permission_id in (select id from sys_permission where id in (select permission_id from sys_role_permission_pk where role_id in (select role_id from sys_user_role_pk where user_id = #{userId,jdbcType=BIGINT}))))"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
            @Result(column="url_request_type", property="urlRequestType", jdbcType=JdbcType.VARCHAR),
            @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    List<SysResource> selectUserResources(Long userId);
}