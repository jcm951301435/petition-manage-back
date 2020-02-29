package com.ssy.petition.dto.petition.params;

public class PetitionTroubleshootParams {

    private String companyName;

    private String status;

    private String contradictionLevel;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
