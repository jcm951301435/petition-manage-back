package com.ssy.petition.dto.sys.result;

import com.ssy.petition.entity.sys.SysRole;

public class SysRoleListResult extends SysRole {

    private String insertByName;

    public String getInsertByName() {
        return insertByName;
    }

    public void setInsertByName(String insertByName) {
        this.insertByName = insertByName;
    }

}
