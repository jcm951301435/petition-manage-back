package com.ssy.petition.dto.sys.result;

import com.ssy.petition.entity.sys.SysPermission;

public class SysPermissionResult extends SysPermission {

    private String insertByName;

    public String getInsertByName() {
        return insertByName;
    }

    public void setInsertByName(String insertByName) {
        this.insertByName = insertByName;
    }

}
