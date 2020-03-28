package com.ssy.petition.dto.sys.result;

import com.ssy.petition.entity.sys.SysPlacard;

public class SysPlacardListResult extends SysPlacard {

    private String insertByName;

    public String getInsertByName() {
        return insertByName;
    }

    public void setInsertByName(String insertByName) {
        this.insertByName = insertByName;
    }

}
