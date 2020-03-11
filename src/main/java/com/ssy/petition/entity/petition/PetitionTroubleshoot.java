package com.ssy.petition.entity.petition;

import com.ssy.petition.annotation.ExcelColumn;
import com.ssy.petition.entity.base.BaseEntity;

import java.io.Serializable;

public class PetitionTroubleshoot extends BaseEntity implements Serializable {

    private Long companyId;

    private Boolean teamPetitionState;

    @ExcelColumn(text = "矛盾级别", colWidth = 10 * 256, sort=3)
    private String contradictionLevel;

    @ExcelColumn(text = "涉及人数", colWidth = 11 * 256, sort=4)
    private Integer teamPetitionCount;

    @ExcelColumn(text = "责任企业", colWidth = 11 * 256, sort=5)
    private String responsibleCompany;

    @ExcelColumn(text = "信访主要诉求", colWidth = 46 * 256, sort=6)
    private String content;

    @ExcelColumn(text = "矛盾类型", colWidth = 10 * 256, sort=7)
    private String contradictionType;

    @ExcelColumn(text = "主要措施", colWidth = 40 * 256, sort=8)
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