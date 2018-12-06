package com.example.demo.dao;

import com.example.demo.entity.SysRoleResourceExample;
import com.example.demo.entity.SysRoleResourceKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleResourceMapper {
    int countByExample(SysRoleResourceExample example);

    int deleteByExample(SysRoleResourceExample example);

    int deleteByPrimaryKey(SysRoleResourceKey key);

    int insert(SysRoleResourceKey record);

    int insertSelective(SysRoleResourceKey record);

    List<SysRoleResourceKey> selectByExample(SysRoleResourceExample example);

    int updateByExampleSelective(@Param("record") SysRoleResourceKey record, @Param("example") SysRoleResourceExample example);

    int updateByExample(@Param("record") SysRoleResourceKey record, @Param("example") SysRoleResourceExample example);
}