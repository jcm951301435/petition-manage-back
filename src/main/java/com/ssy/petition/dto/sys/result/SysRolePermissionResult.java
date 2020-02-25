package com.ssy.petition.dto.sys.result;

import java.io.Serializable;
import java.util.List;

public class SysRolePermissionResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<SysPermissionResult> allList;

    private List<Long> checkId;

    public List<SysPermissionResult> getAllList() {
        return allList;
    }

    public void setAllList(List<SysPermissionResult> allList) {
        this.allList = allList;
    }

    public List<Long> getCheckId() {
        return checkId;
    }

    public void setCheckId(List<Long> checkId) {
        this.checkId = checkId;
    }
}
