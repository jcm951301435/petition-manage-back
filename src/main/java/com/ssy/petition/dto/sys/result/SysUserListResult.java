package com.ssy.petition.dto.sys.result;

import com.ssy.petition.entity.base.BaseEntity;

import java.io.Serializable;

/**
 * 用户列表映射结果
 *
 * @author jcm
 */
public class SysUserListResult extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String realName;

    private String password;

    private Long roleId;

    private String roleName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

}
