package com.ssy.petition.dto.petition.result;

import com.ssy.petition.dto.petition.params.PetitionContradictionEditParams;
import com.ssy.petition.util.CollectionUtils;

import java.util.ArrayList;

public class PetitionContradictionResult extends PetitionContradictionEditParams {

    private String insertByName;

    private String companyName;

    private String purposeName;

    private String resolveLevelName;

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
        return this;
    }

}
