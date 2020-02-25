package com.ssy.petition.dto.sys.params;

import java.io.Serializable;
import java.util.List;

public class SysRolePermissionAddParams implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private List<Long> checkId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getCheckId() {
        return checkId;
    }

    public void setCheckId(List<Long> checkId) {
        this.checkId = checkId;
    }
}
