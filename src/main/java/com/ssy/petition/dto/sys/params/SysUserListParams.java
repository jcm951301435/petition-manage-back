package com.ssy.petition.dto.sys.params;

import java.util.Date;

public class SysUserListParams {

    private String username;

    private String realName;

    private Date insertOnFrom;

    private Date insertOnTo;

    private Long companyId;

    private String companyName;

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
