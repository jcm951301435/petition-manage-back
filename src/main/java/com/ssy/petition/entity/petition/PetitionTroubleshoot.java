package com.ssy.petition.entity.petition;

import com.ssy.petition.entity.base.BaseEntity;

import java.io.Serializable;

public class PetitionTroubleshoot extends BaseEntity implements Serializable {

    private Long companyId;

    private Boolean teamPetitionState;

    private String contradictionLevel;

    private Integer teamPetitionCount;

    private String responsibleCompany;

    private String content;

    private String contradictionType;

    private String resolveProcess;

    private String status;

    private static final long serialVersionUID = 1L;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Boolean getTeamPetitionState() {
        return teamPetitionState;
    }

    public void setTeamPetitionState(Boolean teamPetitionState) {
        this.teamPetitionState = teamPetitionState;
    }

    public String getContradictionLevel() {
        return contradictionLevel;
    }

    public void setContradictionLevel(String contradictionLevel) {
        this.contradictionLevel = contradictionLevel == null ? null : contradictionLevel.trim();
    }

    public Integer getTeamPetitionCount() {
        return teamPetitionCount;
    }

    public void setTeamPetitionCount(Integer teamPetitionCount) {
        this.teamPetitionCount = teamPetitionCount;
    }

    public String getResponsibleCompany() {
        return responsibleCompany;
    }

    public void setResponsibleCompany(String responsibleCompany) {
        this.responsibleCompany = responsibleCompany == null ? null : responsibleCompany.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getContradictionType() {
        return contradictionType;
    }

    public void setContradictionType(String contradictionType) {
        this.contradictionType = contradictionType == null ? null : contradictionType.trim();
    }

    public String getResolveProcess() {
        return resolveProcess;
    }

    public void setResolveProcess(String resolveProcess) {
        this.resolveProcess = resolveProcess == null ? null : resolveProcess.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

}