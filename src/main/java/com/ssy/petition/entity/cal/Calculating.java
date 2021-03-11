package com.ssy.petition.entity.cal;

import java.util.List;
import java.util.Set;

/**
 * 计算中
 * @author: jcm
 * @date: 2020/08/26
 */
public class Calculating {

    /**
     * 按期受理告知率 行政
     */
    private CalculateEntity<CycleAccept> adminCycleAccepts;

    /**
     * 按期受理告知率 党委
     */
    private CalculateEntity<CycleAccept> partyCycleAccepts;


    /**
     * 初次信访事项办理效率 行政
     */
    private CalculateEntity<FirstAccept> adminFirstAccepts;

    /**
     * 初次信访事项办理效率 党委
     */
    private CalculateEntity<FirstAccept> partyFirstAccepts;

    /**
     * 初次信访办理联系率 行政
     */
    private CalculateEntity<FirstContact> adminFirstContacts;

    /**
     * 初次信访办理联系率 党委
     */
    private CalculateEntity<FirstContact> partyFirstContacts;

    /**
     * 重复信访率 行政
     */
    private CalculateEntity<RepeatPetition> adminRepeatPetitions;

    /**
     * 重复信访率 党委
     */
    private CalculateEntity<RepeatPetition> partyRepeatPetitions;

    /**
     * 公开回复率 行政
     */
    private CalculateEntity<PublicReply> adminPublicReplies;

    /**
     * 公开回复率 党委
     */
    private CalculateEntity<PublicReply> partyPublicReplies;

    /**
     * 信访工作机构参评满意率 行政
     */
    private CalculateEntity<Satisfaction> adminSatisfactions;

    /**
     * 信访工作机构参评满意率 党委
     */
    private CalculateEntity<Satisfaction> partySatisfactions;

    /**
     * 职能部门参评满意率 党委
     */
    private CalculateEntity<Satisfaction> adminRight;

    /**
     * 职能部门参评满意率 党委
     */
    private CalculateEntity<Satisfaction> partyRight;

    /**
     * 计算结果
     */
    private List<Calculated> calculatedList;

    /**
     * 公司集合
     */
    private Set<String> companyNameSet;

    /**
     * 计算状态
     */
    private CalculatedStatusEnum status;

    /**
     * 总条目
     */
    private Integer recordCount;

    /**
     * 已计算条目
     */
    private Integer calculatedCount;

    /**
     * 当前公司名称
     */
    private String currentCompanyName;

    /**
     * 是否保存
     */
    private boolean saveStatus;

    public CalculateEntity<CycleAccept> getAdminCycleAccepts() {
        return adminCycleAccepts;
    }

    public void setAdminCycleAccepts(CalculateEntity<CycleAccept> adminCycleAccepts) {
        this.adminCycleAccepts = adminCycleAccepts;
    }

    public CalculateEntity<CycleAccept> getPartyCycleAccepts() {
        return partyCycleAccepts;
    }

    public void setPartyCycleAccepts(CalculateEntity<CycleAccept> partyCycleAccepts) {
        this.partyCycleAccepts = partyCycleAccepts;
    }

    public CalculateEntity<FirstAccept> getAdminFirstAccepts() {
        return adminFirstAccepts;
    }

    public void setAdminFirstAccepts(CalculateEntity<FirstAccept> adminFirstAccepts) {
        this.adminFirstAccepts = adminFirstAccepts;
    }

    public CalculateEntity<FirstAccept> getPartyFirstAccepts() {
        return partyFirstAccepts;
    }

    public void setPartyFirstAccepts(CalculateEntity<FirstAccept> partyFirstAccepts) {
        this.partyFirstAccepts = partyFirstAccepts;
    }

    public CalculateEntity<FirstContact> getAdminFirstContacts() {
        return adminFirstContacts;
    }

    public void setAdminFirstContacts(CalculateEntity<FirstContact> adminFirstContacts) {
        this.adminFirstContacts = adminFirstContacts;
    }

    public CalculateEntity<FirstContact> getPartyFirstContacts() {
        return partyFirstContacts;
    }

    public void setPartyFirstContacts(CalculateEntity<FirstContact> partyFirstContacts) {
        this.partyFirstContacts = partyFirstContacts;
    }

    public CalculateEntity<RepeatPetition> getAdminRepeatPetitions() {
        return adminRepeatPetitions;
    }

    public void setAdminRepeatPetitions(CalculateEntity<RepeatPetition> adminRepeatPetitions) {
        this.adminRepeatPetitions = adminRepeatPetitions;
    }

    public CalculateEntity<RepeatPetition> getPartyRepeatPetitions() {
        return partyRepeatPetitions;
    }

    public void setPartyRepeatPetitions(CalculateEntity<RepeatPetition> partyRepeatPetitions) {
        this.partyRepeatPetitions = partyRepeatPetitions;
    }

    public CalculateEntity<PublicReply> getAdminPublicReplies() {
        return adminPublicReplies;
    }

    public void setAdminPublicReplies(CalculateEntity<PublicReply> adminPublicReplies) {
        this.adminPublicReplies = adminPublicReplies;
    }

    public CalculateEntity<PublicReply> getPartyPublicReplies() {
        return partyPublicReplies;
    }

    public void setPartyPublicReplies(CalculateEntity<PublicReply> partyPublicReplies) {
        this.partyPublicReplies = partyPublicReplies;
    }

    public CalculateEntity<Satisfaction> getAdminSatisfactions() {
        return adminSatisfactions;
    }

    public void setAdminSatisfactions(CalculateEntity<Satisfaction> adminSatisfactions) {
        this.adminSatisfactions = adminSatisfactions;
    }

    public CalculateEntity<Satisfaction> getPartySatisfactions() {
        return partySatisfactions;
    }

    public void setPartySatisfactions(CalculateEntity<Satisfaction> partySatisfactions) {
        this.partySatisfactions = partySatisfactions;
    }

    public CalculateEntity<Satisfaction> getAdminRight() {
        return adminRight;
    }

    public void setAdminRight(CalculateEntity<Satisfaction> adminRight) {
        this.adminRight = adminRight;
    }

    public CalculateEntity<Satisfaction> getPartyRight() {
        return partyRight;
    }

    public void setPartyRight(CalculateEntity<Satisfaction> partyRight) {
        this.partyRight = partyRight;
    }

    public List<Calculated> getCalculatedList() {
        return calculatedList;
    }

    public void setCalculatedList(List<Calculated> calculatedList) {
        this.calculatedList = calculatedList;
    }

    public Set<String> getCompanyNameSet() {
        return companyNameSet;
    }

    public void setCompanyNameSet(Set<String> companyNameSet) {
        this.companyNameSet = companyNameSet;
    }

    public CalculatedStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CalculatedStatusEnum status) {
        this.status = status;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getCalculatedCount() {
        return calculatedCount;
    }

    public void setCalculatedCount(Integer calculatedCount) {
        this.calculatedCount = calculatedCount;
    }

    public String getCurrentCompanyName() {
        return currentCompanyName;
    }

    public void setCurrentCompanyName(String currentCompanyName) {
        this.currentCompanyName = currentCompanyName;
    }

    public boolean isSaveStatus() {
        return saveStatus;
    }

    public void setSaveStatus(boolean saveStatus) {
        this.saveStatus = saveStatus;
    }

    @Override
    public String toString() {
        return "Calculating{" +
                "adminCycleAccepts=" + adminCycleAccepts +
                ", partyCycleAccepts=" + partyCycleAccepts +
                ", adminFirstAccepts=" + adminFirstAccepts +
                ", partyFirstAccepts=" + partyFirstAccepts +
                ", adminFirstContacts=" + adminFirstContacts +
                ", partyFirstContacts=" + partyFirstContacts +
                ", adminRepeatPetitions=" + adminRepeatPetitions +
                ", partyRepeatPetitions=" + partyRepeatPetitions +
                ", adminPublicReplies=" + adminPublicReplies +
                ", partyPublicReplies=" + partyPublicReplies +
                ", adminSatisfactions=" + adminSatisfactions +
                ", partySatisfactions=" + partySatisfactions +
                ", adminRight=" + adminRight +
                ", partyRight=" + partyRight +
                ", calculatedList=" + calculatedList +
                ", companyNameSet=" + companyNameSet +
                ", status=" + status +
                ", recordCount=" + recordCount +
                ", calculatedCount=" + calculatedCount +
                ", currentCompanyName='" + currentCompanyName + '\'' +
                ", saveStatus=" + saveStatus +
                '}';
    }
}
