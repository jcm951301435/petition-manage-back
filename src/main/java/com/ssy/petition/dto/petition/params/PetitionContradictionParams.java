package com.ssy.petition.dto.petition.params;

import java.util.List;

public class PetitionContradictionParams {

    private Long id;

    private List<Long> idList;

    private List<String> applyName;

    private List<Long> responsibleCompany;

    private String applySex;

    private Boolean checkType;

    private Boolean teamPetitionState;

    private String resolveLevel;

    private String petitionType;

    private List<String> petitionTypes;

    private String purposeType;

    private String contradictionType;

    private Boolean finished;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public List<String> getApplyName() {
        return applyName;
    }

    public void setApplyName(List<String> applyName) {
        this.applyName = applyName;
    }

    public List<Long> getResponsibleCompany() {
        return responsibleCompany;
    }

    public void setResponsibleCompany(List<Long> responsibleCompany) {
        this.responsibleCompany = responsibleCompany;
    }

    public String getApplySex() {
        return applySex;
    }

    public void setApplySex(String applySex) {
        this.applySex = applySex;
    }

    public Boolean getCheckType() {
        return checkType;
    }

    public void setCheckType(Boolean checkType) {
        this.checkType = checkType;
    }

    public Boolean getTeamPetitionState() {
        return teamPetitionState;
    }

    public void setTeamPetitionState(Boolean teamPetitionState) {
        this.teamPetitionState = teamPetitionState;
    }

    public String getResolveLevel() {
        return resolveLevel;
    }

    public void setResolveLevel(String resolveLevel) {
        this.resolveLevel = resolveLevel;
    }

    public String getPetitionType() {
        return petitionType;
    }

    public void setPetitionType(String petitionType) {
        this.petitionType = petitionType;
    }

    public String getPurposeType() {
        return purposeType;
    }

    public void setPurposeType(String purposeType) {
        this.purposeType = purposeType;
    }

    public String getContradictionType() {
        return contradictionType;
    }

    public void setContradictionType(String contradictionType) {
        this.contradictionType = contradictionType;
    }

    public List<String> getPetitionTypes() {
        return petitionTypes;
    }

    public void setPetitionTypes(List<String> petitionTypes) {
        this.petitionTypes = petitionTypes;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
