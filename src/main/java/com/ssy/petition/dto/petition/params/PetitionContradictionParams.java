package com.ssy.petition.dto.petition.params;

public class PetitionContradictionParams {

    private Long id;

    private String contradictionType;

    private String applyName;

    private String checkType;

    private String responsibleCompany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContradictionType() {
        return contradictionType;
    }

    public void setContradictionType(String contradictionType) {
        this.contradictionType = contradictionType;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getResponsibleCompany() {
        return responsibleCompany;
    }

    public void setResponsibleCompany(String responsibleCompany) {
        this.responsibleCompany = responsibleCompany;
    }
}
