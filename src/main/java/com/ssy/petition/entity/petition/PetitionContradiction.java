package com.ssy.petition.entity.petition;

import com.ssy.petition.entity.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class PetitionContradiction extends BaseEntity implements Serializable {

    private String contradictionType;

    private String applyName;

    private String applySex;

    private String applyBirth;

    private String applyIdCard;

    private String applyPoliceStation;

    private String applyPoliticalStatus;

    private String petitionType;

    private String purposeType;

    private Boolean checkType;

    private String applyAddress;

    private String applyStreet;

    private Long responsibleCompany;

    private String responsibleContactName;

    private String responsibleContactTelephone;

    private String enterBeijingState;

    private Boolean teamPetitionState;

    private String teamPetitionCount;

    private Date firstPetitionTime;

    private Date lastPetitionTime;

    private String resolveLevel;

    private Boolean resolveMethodSelf;

    private String resolveMethodSelfName;

    private String resolveMethodSelfPosition;

    private Boolean resolveMethodThird;

    private String resolveMethodThirdCompany;

    private String resolveMethodThirdName;

    private Boolean resolveMethodState;

    private String resolveMethodStateName;

    private String resolveMethodStatePosition;

    private String resolveMethodStateCompany;

    private String resolveMethodStateOther;

    private Boolean resolveMethodCity;

    private Boolean resolveMethodCityLeader;

    private String resolveMethodCityLeaderName;

    private String resolveMethodCityLeaderPosition;

    private Boolean resolveMethodCityMeeting;

    private String resolveMethodCityMeetingContent;

    private Boolean resolveMethodCitySeparate;

    private String resolveMethodCitySeparateContent;

    private Boolean resolveMethodCityOther;

    private String resolveMethodCityOtherContent;

    private String resolveForm;

    private String resolveFormOtherContent;

    private static final long serialVersionUID = 1L;

    public String getContradictionType() {
        return contradictionType;
    }

    public void setContradictionType(String contradictionType) {
        this.contradictionType = contradictionType == null ? null : contradictionType.trim();
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName == null ? null : applyName.trim();
    }

    public String getApplySex() {
        return applySex;
    }

    public void setApplySex(String applySex) {
        this.applySex = applySex == null ? null : applySex.trim();
    }

    public String getApplyBirth() {
        return applyBirth;
    }

    public void setApplyBirth(String applyBirth) {
        this.applyBirth = applyBirth == null ? null : applyBirth.trim();
    }

    public String getApplyIdCard() {
        return applyIdCard;
    }

    public void setApplyIdCard(String applyIdCard) {
        this.applyIdCard = applyIdCard == null ? null : applyIdCard.trim();
    }

    public String getApplyPoliceStation() {
        return applyPoliceStation;
    }

    public void setApplyPoliceStation(String applyPoliceStation) {
        this.applyPoliceStation = applyPoliceStation == null ? null : applyPoliceStation.trim();
    }

    public String getApplyPoliticalStatus() {
        return applyPoliticalStatus;
    }

    public void setApplyPoliticalStatus(String applyPoliticalStatus) {
        this.applyPoliticalStatus = applyPoliticalStatus == null ? null : applyPoliticalStatus.trim();
    }

    public String getPetitionType() {
        return petitionType;
    }

    public void setPetitionType(String petitionType) {
        this.petitionType = petitionType == null ? null : petitionType.trim();
    }

    public String getPurposeType() {
        return purposeType;
    }

    public void setPurposeType(String purposeType) {
        this.purposeType = purposeType == null ? null : purposeType.trim();
    }

    public Boolean getCheckType() {
        return checkType;
    }

    public void setCheckType(Boolean checkType) {
        this.checkType = checkType;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress == null ? null : applyAddress.trim();
    }

    public String getApplyStreet() {
        return applyStreet;
    }

    public void setApplyStreet(String applyStreet) {
        this.applyStreet = applyStreet == null ? null : applyStreet.trim();
    }

    public Long getResponsibleCompany() {
        return responsibleCompany;
    }

    public void setResponsibleCompany(Long responsibleCompany) {
        this.responsibleCompany = responsibleCompany;
    }

    public String getResponsibleContactName() {
        return responsibleContactName;
    }

    public void setResponsibleContactName(String responsibleContactName) {
        this.responsibleContactName = responsibleContactName == null ? null : responsibleContactName.trim();
    }

    public String getResponsibleContactTelephone() {
        return responsibleContactTelephone;
    }

    public void setResponsibleContactTelephone(String responsibleContactTelephone) {
        this.responsibleContactTelephone = responsibleContactTelephone == null ? null : responsibleContactTelephone.trim();
    }

    public String getEnterBeijingState() {
        return enterBeijingState;
    }

    public void setEnterBeijingState(String enterBeijingState) {
        this.enterBeijingState = enterBeijingState == null ? null : enterBeijingState.trim();
    }

    public Boolean getTeamPetitionState() {
        return teamPetitionState;
    }

    public void setTeamPetitionState(Boolean teamPetitionState) {
        this.teamPetitionState = teamPetitionState;
    }

    public String getTeamPetitionCount() {
        return teamPetitionCount;
    }

    public void setTeamPetitionCount(String teamPetitionCount) {
        this.teamPetitionCount = teamPetitionCount == null ? null : teamPetitionCount.trim();
    }

    public Date getFirstPetitionTime() {
        return firstPetitionTime;
    }

    public void setFirstPetitionTime(Date firstPetitionTime) {
        this.firstPetitionTime = firstPetitionTime;
    }

    public Date getLastPetitionTime() {
        return lastPetitionTime;
    }

    public void setLastPetitionTime(Date lastPetitionTime) {
        this.lastPetitionTime = lastPetitionTime;
    }

    public String getResolveLevel() {
        return resolveLevel;
    }

    public void setResolveLevel(String resolveLevel) {
        this.resolveLevel = resolveLevel == null ? null : resolveLevel.trim();
    }

    public Boolean getResolveMethodSelf() {
        return resolveMethodSelf;
    }

    public void setResolveMethodSelf(Boolean resolveMethodSelf) {
        this.resolveMethodSelf = resolveMethodSelf;
    }

    public String getResolveMethodSelfName() {
        return resolveMethodSelfName;
    }

    public void setResolveMethodSelfName(String resolveMethodSelfName) {
        this.resolveMethodSelfName = resolveMethodSelfName == null ? null : resolveMethodSelfName.trim();
    }

    public String getResolveMethodSelfPosition() {
        return resolveMethodSelfPosition;
    }

    public void setResolveMethodSelfPosition(String resolveMethodSelfPosition) {
        this.resolveMethodSelfPosition = resolveMethodSelfPosition == null ? null : resolveMethodSelfPosition.trim();
    }

    public Boolean getResolveMethodThird() {
        return resolveMethodThird;
    }

    public void setResolveMethodThird(Boolean resolveMethodThird) {
        this.resolveMethodThird = resolveMethodThird;
    }

    public String getResolveMethodThirdCompany() {
        return resolveMethodThirdCompany;
    }

    public void setResolveMethodThirdCompany(String resolveMethodThirdCompany) {
        this.resolveMethodThirdCompany = resolveMethodThirdCompany == null ? null : resolveMethodThirdCompany.trim();
    }

    public String getResolveMethodThirdName() {
        return resolveMethodThirdName;
    }

    public void setResolveMethodThirdName(String resolveMethodThirdName) {
        this.resolveMethodThirdName = resolveMethodThirdName == null ? null : resolveMethodThirdName.trim();
    }

    public Boolean getResolveMethodState() {
        return resolveMethodState;
    }

    public void setResolveMethodState(Boolean resolveMethodState) {
        this.resolveMethodState = resolveMethodState;
    }

    public String getResolveMethodStateName() {
        return resolveMethodStateName;
    }

    public void setResolveMethodStateName(String resolveMethodStateName) {
        this.resolveMethodStateName = resolveMethodStateName == null ? null : resolveMethodStateName.trim();
    }

    public String getResolveMethodStatePosition() {
        return resolveMethodStatePosition;
    }

    public void setResolveMethodStatePosition(String resolveMethodStatePosition) {
        this.resolveMethodStatePosition = resolveMethodStatePosition == null ? null : resolveMethodStatePosition.trim();
    }

    public String getResolveMethodStateCompany() {
        return resolveMethodStateCompany;
    }

    public void setResolveMethodStateCompany(String resolveMethodStateCompany) {
        this.resolveMethodStateCompany = resolveMethodStateCompany == null ? null : resolveMethodStateCompany.trim();
    }

    public String getResolveMethodStateOther() {
        return resolveMethodStateOther;
    }

    public void setResolveMethodStateOther(String resolveMethodStateOther) {
        this.resolveMethodStateOther = resolveMethodStateOther == null ? null : resolveMethodStateOther.trim();
    }

    public Boolean getResolveMethodCity() {
        return resolveMethodCity;
    }

    public void setResolveMethodCity(Boolean resolveMethodCity) {
        this.resolveMethodCity = resolveMethodCity;
    }

    public Boolean getResolveMethodCityLeader() {
        return resolveMethodCityLeader;
    }

    public void setResolveMethodCityLeader(Boolean resolveMethodCityLeader) {
        this.resolveMethodCityLeader = resolveMethodCityLeader;
    }

    public String getResolveMethodCityLeaderName() {
        return resolveMethodCityLeaderName;
    }

    public void setResolveMethodCityLeaderName(String resolveMethodCityLeaderName) {
        this.resolveMethodCityLeaderName = resolveMethodCityLeaderName == null ? null : resolveMethodCityLeaderName.trim();
    }

    public String getResolveMethodCityLeaderPosition() {
        return resolveMethodCityLeaderPosition;
    }

    public void setResolveMethodCityLeaderPosition(String resolveMethodCityLeaderPosition) {
        this.resolveMethodCityLeaderPosition = resolveMethodCityLeaderPosition == null ? null : resolveMethodCityLeaderPosition.trim();
    }

    public Boolean getResolveMethodCityMeeting() {
        return resolveMethodCityMeeting;
    }

    public void setResolveMethodCityMeeting(Boolean resolveMethodCityMeeting) {
        this.resolveMethodCityMeeting = resolveMethodCityMeeting;
    }

    public String getResolveMethodCityMeetingContent() {
        return resolveMethodCityMeetingContent;
    }

    public void setResolveMethodCityMeetingContent(String resolveMethodCityMeetingContent) {
        this.resolveMethodCityMeetingContent = resolveMethodCityMeetingContent == null ? null : resolveMethodCityMeetingContent.trim();
    }

    public Boolean getResolveMethodCitySeparate() {
        return resolveMethodCitySeparate;
    }

    public void setResolveMethodCitySeparate(Boolean resolveMethodCitySeparate) {
        this.resolveMethodCitySeparate = resolveMethodCitySeparate;
    }

    public String getResolveMethodCitySeparateContent() {
        return resolveMethodCitySeparateContent;
    }

    public void setResolveMethodCitySeparateContent(String resolveMethodCitySeparateContent) {
        this.resolveMethodCitySeparateContent = resolveMethodCitySeparateContent == null ? null : resolveMethodCitySeparateContent.trim();
    }

    public Boolean getResolveMethodCityOther() {
        return resolveMethodCityOther;
    }

    public void setResolveMethodCityOther(Boolean resolveMethodCityOther) {
        this.resolveMethodCityOther = resolveMethodCityOther;
    }

    public String getResolveMethodCityOtherContent() {
        return resolveMethodCityOtherContent;
    }

    public void setResolveMethodCityOtherContent(String resolveMethodCityOtherContent) {
        this.resolveMethodCityOtherContent = resolveMethodCityOtherContent == null ? null : resolveMethodCityOtherContent.trim();
    }

    public String getResolveForm() {
        return resolveForm;
    }

    public void setResolveForm(String resolveForm) {
        this.resolveForm = resolveForm == null ? null : resolveForm.trim();
    }

    public String getResolveFormOtherContent() {
        return resolveFormOtherContent;
    }

    public void setResolveFormOtherContent(String resolveFormOtherContent) {
        this.resolveFormOtherContent = resolveFormOtherContent == null ? null : resolveFormOtherContent.trim();
    }
}