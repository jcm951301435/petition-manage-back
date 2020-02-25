package com.ssy.petition.dao.sys;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.entity.sys.SysRolePermissionRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色-权限持久化层
 *
 * @author jcm
 */
@Repository
public interface SysRolePermissionRelationMapper extends BaseMapper<SysRolePermissionRelation> {

    int deleteRolePermissionRelationNotIn(@Param("roleId")Long roleId, @Param("list")List<Long> list);

    int insertList(@Param("list")List<SysRolePermissionRelation> list);

}