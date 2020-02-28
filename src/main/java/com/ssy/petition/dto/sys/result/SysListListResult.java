package com.ssy.petition.dto.sys.result;

import com.ssy.petition.entity.sys.SysList;

public class SysListListResult extends SysList {

    private String insertByName;

    public String getInsertByName() {
        return insertByName;
    }

    public void setInsertByName(String insertByName) {
        this.insertByName = insertByName;
    }
}
