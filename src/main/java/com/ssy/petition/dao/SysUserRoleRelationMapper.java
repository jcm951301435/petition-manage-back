package com.ssy.petition.dao;

import com.ssy.petition.entity.SysUserRoleRelation;

public interface SysUserRoleRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRoleRelation record);

    int insertSelective(SysUserRoleRelation record);

    SysUserRoleRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRoleRelation record);

    int updateByPrimaryKey(SysUserRoleRelation record);
}