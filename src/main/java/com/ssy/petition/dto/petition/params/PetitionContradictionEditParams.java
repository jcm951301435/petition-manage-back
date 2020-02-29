package com.ssy.petition.dto.petition.params;

import com.ssy.petition.entity.petition.PetitionContradiction;
import com.ssy.petition.util.CollectionUtils;

import java.util.List;

public class PetitionContradictionEditParams extends PetitionContradiction {

    private List<String> contradictionTypes;

    private List<String> petitionTypes;

    private List<String> resolveForms;

    private Long companyId;

    private static final long serialVersionUID = 1L;

    public List<String> getContradictionTypes() {
        return contradictionTypes;
    }

    public void setContradictionTypes(List<String> contradictionTypes) {
        this.contradictionTypes = contradictionTypes;
    }

    public List<String> getPetitionTypes() {
        return petitionTypes;
    }

    public void setPetitionTypes(List<String> petitionTypes) {
        this.petitionTypes = petitionTypes;
    }

    public List<String> getResolveForms() {
        return resolveForms;
    }

    public void setResolveForms(List<String> resolveForms) {
        this.resolveForms = resolveForms;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public PetitionContradiction toParams() {
        this.setContradictionType(CollectionUtils.listToString(this.contradictionTypes));
        this.setPetitionType(CollectionUtils.listToString(this.petitionTypes));
        this.setResolveForm(CollectionUtils.listToString(this.resolveForms));
        this.setResponsibleCompany(this.companyId);
        return this;
    }

}
