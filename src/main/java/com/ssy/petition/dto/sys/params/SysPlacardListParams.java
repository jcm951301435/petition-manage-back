package com.ssy.petition.dto.sys.params;

import com.ssy.petition.entity.sys.SysPlacard;

import java.util.Date;

public class SysPlacardListParams extends SysPlacard {

    private Date insertOnFrom;

    private Date insertOnTo;

    public Date getInsertOnFrom() {
        return insertOnFrom;
    }

    public void setInsertOnFrom(Date insertOnFrom) {
        this.insertOnFrom = insertOnFrom;
    }

    public Date getInsertOnTo() {
        return insertOnTo;
    }

    public void setInsertOnTo(Date insertOnTo) {
        this.insertOnTo = insertOnTo;
    }
}
