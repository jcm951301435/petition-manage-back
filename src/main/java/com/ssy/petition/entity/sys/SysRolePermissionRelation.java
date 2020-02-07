package com.ssy.petition.entity.sys;

import com.ssy.petition.entity.base.BaseEntity;

import java.io.Serializable;

/**
 * 角色-权限
 *
 * @author jcm
 */
public class SysRolePermissionRelation extends BaseEntity implements Serializable {
    private Long roleId;

    private Long permissionId;

    private static final long serialVersionUID = 1L;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}