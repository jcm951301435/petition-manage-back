package com.ssy.petition.dto.sys.result;

import com.ssy.petition.entity.sys.SysUser;

/**
 * 用户列表映射结果，用户操作参数
 *
 * @author jcm
 */
public class SysUserListResult extends SysUser {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private String roleName;

    private boolean editPassword;

    private String oldPassword;

    private String insertByName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isEditPassword() {
        return editPassword;
    }

    public void setEditPassword(boolean editPassword) {
        this.editPassword = editPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getInsertByName() {
        return insertByName;
    }

    public void setInsertByName(String insertByName) {
        this.insertByName = insertByName;
    }

}
