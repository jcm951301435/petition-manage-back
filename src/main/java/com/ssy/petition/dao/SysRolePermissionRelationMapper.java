package com.ssy.petition.dao;

import com.ssy.petition.entity.SysRolePermissionRelation;

public interface SysRolePermissionRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRolePermissionRelation record);

    int insertSelective(SysRolePermissionRelation record);

    SysRolePermissionRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRolePermissionRelation record);

    int updateByPrimaryKey(SysRolePermissionRelation record);
}