package com.ssy.petition.dto.petition.params;

import java.util.List;

public class PetitionTroubleshootParams {

    private List<Long> idList;

    private String companyName;

    private Long companyId;

    private String status;

    private String contradictionLevel;

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContradictionLevel() {
        return contradictionLevel;
    }

    public void setContradictionLevel(String contradictionLevel) {
        this.contradictionLevel = contradictionLevel;
    }
}
