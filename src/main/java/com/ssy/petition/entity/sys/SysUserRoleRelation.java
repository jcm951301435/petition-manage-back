package com.ssy.petition.entity.sys;

import com.ssy.petition.entity.base.BaseEntity;

import java.io.Serializable;

/**
 * 用户-角色
 *
 * @author jcm
 */
public class SysUserRoleRelation extends BaseEntity implements Serializable {
    private Long userId;

    private Long roleId;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}