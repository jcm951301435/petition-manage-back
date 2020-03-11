package com.ssy.petition.dto.petition.result;

import com.ssy.petition.annotation.ExcelBook;
import com.ssy.petition.annotation.ExcelColumn;
import com.ssy.petition.dto.petition.params.PetitionContradictionEditParams;
import com.ssy.petition.entity.petition.PetitionContradictionContent;
import com.ssy.petition.entity.petition.PetitionContradictionResolveProcess;
import com.ssy.petition.util.CollectionUtils;
import com.ssy.petition.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@ExcelBook(title = "一人一表",importRowBeginCount = 4)
public class PetitionContradictionResult extends PetitionContradictionEditParams {

    private String insertByName;

    @ExcelColumn(text = "责任单位", colWidth = 12 * 256, sort=12)
    private String companyName;

    private String purposeName;

    private String resolveLevelName;

    @ExcelColumn(text = "是否集访", colWidth = 12 * 256, sort=16)
    private String teamPetitionStateStr;

    @ExcelColumn(text = "性别", colWidth = 12 * 256, sort=2)
    private String applySexStr;

    @ExcelColumn(text = "出生年月", colWidth = 12 * 256, sort=3)
    private String applyBirthStr;

    @ExcelColumn(text = "信访类型", colWidth = 12 * 256, sort=4)
    private String petitionTypeStr;

    @ExcelColumn(text = "核查终结", colWidth = 12 * 256, sort=9)
    private String checkTypeStr;

    @ExcelColumn(text = "首次信访时间", colWidth = 12 * 256, sort=18)
    private String firstPetitionTimeStr;

    @ExcelColumn(text = "末次信访时间", colWidth = 12 * 256, sort=19)
    private String lastPetitionTimeStr;

    private String contradictionContentStr;

    private String contradictionResolveProcessStr;

    private String petitionTypePdfStr;

    public String getInsertByName() {
        return insertByName;
    }

    public void setInsertByName(String insertByName) {
        this.insertByName = insertByName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }

    public String getResolveLevelName() {
        return resolveLevelName;
    }

    public void setResolveLevelName(String resolveLevelName) {
        this.resolveLevelName = resolveLevelName;
    }

    public String getTeamPetitionStateStr() {
        return teamPetitionStateStr;
    }

    public void setTeamPetitionStateStr(String teamPetitionStateStr) {
        this.teamPetitionStateStr = teamPetitionStateStr;
    }

    public String getApplySexStr() {
        return applySexStr;
    }

    public void setApplySexStr(String applySexStr) {
        this.applySexStr = applySexStr;
    }

    public String getApplyBirthStr() {
        return applyBirthStr;
    }

    public void setApplyBirthStr(String applyBirthStr) {
        this.applyBirthStr = applyBirthStr;
    }

    public String getPetitionTypeStr() {
        return petitionTypeStr;
    }

    public void setPetitionTypeStr(String petitionTypeStr) {
        this.petitionTypeStr = petitionTypeStr;
    }

    public String getCheckTypeStr() {
        return checkTypeStr;
    }

    public void setCheckTypeStr(String checkTypeStr) {
        this.checkTypeStr = checkTypeStr;
    }

    public String getFirstPetitionTimeStr() {
        return firstPetitionTimeStr;
    }

    public void setFirstPetitionTimeStr(String firstPetitionTimeStr) {
        this.firstPetitionTimeStr = firstPetitionTimeStr;
    }

    public String getLastPetitionTimeStr() {
        return lastPetitionTimeStr;
    }

    public void setLastPetitionTimeStr(String lastPetitionTimeStr) {
        this.lastPetitionTimeStr = lastPetitionTimeStr;
    }

    public String getContradictionContentStr() {
        return contradictionContentStr;
    }

    public void setContradictionContentStr(String contradictionContentStr) {
        this.contradictionContentStr = contradictionContentStr;
    }

    public String getContradictionResolveProcessStr() {
        return contradictionResolveProcessStr;
    }

    public void setContradictionResolveProcessStr(String contradictionResolveProcessStr) {
        this.contradictionResolveProcessStr = contradictionResolveProcessStr;
    }

    public String getPetitionTypePdfStr() {
        return petitionTypePdfStr;
    }

    public void setPetitionTypePdfStr(String petitionTypePdfStr) {
        this.petitionTypePdfStr = petitionTypePdfStr;
    }

    @Override
    public void setPetitionType(String petitionType) {
        if (this.getPetitionTypeStr() == null) {
            if (petitionType != null) {
                List<String> list = CollectionUtils.arrayStrToList(petitionType);
                if (CollectionUtils.isNotEmpty(list)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String temp : list) {
                        if ("1".equals(temp)) {
                            stringBuilder.append("信");
                        } else if ("2".equals(temp)) {
                            stringBuilder.append("访");
                        } else if ("3".equals(temp)) {
                            stringBuilder.append("网");
                        } else {
                            continue;
                        }
                        stringBuilder.append(",");
                    }
                    if (stringBuilder.lastIndexOf(",") == stringBuilder.length() - 1) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    this.setPetitionTypePdfStr(stringBuilder.toString());
                }
            }
        }
        super.setPetitionType(petitionType);
    }

    @Override
    public void setContradictionContent(List<PetitionContradictionContent> contentList) {
        if (this.getContradictionContentStr() == null) {
            if (CollectionUtils.isNotEmpty(contentList)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < contentList.size(); i ++) {
                    PetitionContradictionContent contradictionContent = contentList.get(i);
                    String content = contradictionContent.getContradictionContent();
                    if (StringUtils.isNotEmpty(content)) {
                        stringBuilder.append(i + 1);
                        stringBuilder.append(".");
                        stringBuilder.append(content);
                        stringBuilder.append("\n");
                    }
                }
                if (stringBuilder.length() > 0) {
                    this.setContradictionContentStr(stringBuilder.toString());
                }
            }
        }
        super.setContradictionContent(contentList);
    }

    @Override
    public void setContradictionResolveProcess(List<PetitionContradictionResolveProcess> processList) {
        if (this.getContradictionResolveProcessStr() != null) {
            if (CollectionUtils.isNotEmpty(processList)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < processList.size(); i ++) {
                    PetitionContradictionResolveProcess resolveProcess = processList.get(i);
                    String content = resolveProcess.getResolveContent();
                    if (StringUtils.isNotEmpty(content)) {
                        stringBuilder.append(i + 1);
                        stringBuilder.append(".");
                        stringBuilder.append(content);
                        stringBuilder.append("\n");
                    }
                }
                if (stringBuilder.length() > 0) {
                    this.setContradictionResolveProcessStr(stringBuilder.toString());
                }
            }
        }
        super.setContradictionResolveProcess(processList);
    }

    public PetitionContradictionResult toResult() {
        String contradictionType = this.getContradictionType();
        String petitionType = this.getPetitionType();
        String resolveForm = this.getResolveForm();
        if (contradictionType == null) {
            this.setContradictionTypes(new ArrayList<>());
        } else {
            this.setContradictionTypes(CollectionUtils.arrayStrToList(contradictionType));
        }
        if (petitionType == null) {
            this.setPetitionTypes(new ArrayList<>());
        } else {
            this.setPetitionTypes(CollectionUtils.arrayStrToList(petitionType));
        }
        if (resolveForm == null) {
            this.setResolveForms(new ArrayList<>());
        } else {
            this.setResolveForms(CollectionUtils.arrayStrToList(resolveForm));
        }
        this.setCompanyId(this.getResponsibleCompany());
        //this.setApplySexStr(this.getApplySex().equals("1") ? "男" : "女");
        //this.setApplyBirthStr(DateUtils.formatDate(this.getApplyBirth(), "yyyy-MM"));
        return this;
    }

}
