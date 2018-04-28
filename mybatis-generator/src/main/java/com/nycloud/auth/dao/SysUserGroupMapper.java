package com.nycloud.auth.dao;

import com.nycloud.auth.model.SysUserGroup;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface SysUserGroupMapper {
    @Delete({
        "delete from sys_user_group",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_user_group (id, name, ",
        "parent_name)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{parentName,jdbcType=VARCHAR})"
    })
    int insert(SysUserGroup record);

    @Select({
        "select",
        "id, name, parent_name",
        "from sys_user_group",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_name", property="parentName", jdbcType=JdbcType.VARCHAR)
    })
    SysUserGroup selectByPrimaryKey(Long id);

    @Select({
        "select",
        "id, name, parent_name",
        "from sys_user_group"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_name", property="parentName", jdbcType=JdbcType.VARCHAR)
    })
    List<SysUserGroup> selectAll();

    @Update({
        "update sys_user_group",
        "set name = #{name,jdbcType=VARCHAR},",
          "parent_name = #{parentName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUserGroup record);
}