package com.ssy.petition.dto.petition.params;

import com.ssy.petition.entity.petition.PetitionContradiction;
import com.ssy.petition.entity.petition.PetitionContradictionContent;
import com.ssy.petition.entity.petition.PetitionContradictionResolveProcess;
import com.ssy.petition.entity.sys.SysFile;
import com.ssy.petition.util.CollectionUtils;

import java.util.List;

public class PetitionContradictionEditParams extends PetitionContradiction {

    private List<String> contradictionTypes;

    private List<String> petitionTypes;

    private List<String> resolveForms;

    private Long companyId;

    //@ExcelColumn(text = "信访人诉求概述", colWidth = 12 * 256, sort=21)
    private List<PetitionContradictionContent> contradictionContent;

    //@ExcelColumn(text = "化解过程简述", colWidth = 12 * 256, sort=22)
    private List<PetitionContradictionResolveProcess> contradictionResolveProcess;

    private List<SysFile> fileList;

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

    public List<PetitionContradictionContent> getContradictionContent() {
        return contradictionContent;
    }

    public void setContradictionContent(List<PetitionContradictionContent> contradictionContent) {
        this.contradictionContent = contradictionContent;
    }

    public List<PetitionContradictionResolveProcess> getContradictionResolveProcess() {
        return contradictionResolveProcess;
    }

    public void setContradictionResolveProcess(List<PetitionContradictionResolveProcess> contradictionResolveProcess) {
        this.contradictionResolveProcess = contradictionResolveProcess;
    }

    public List<SysFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<SysFile> fileList) {
        this.fileList = fileList;
    }

    public PetitionContradiction toParams() {
        this.setContradictionType(CollectionUtils.listToString(this.contradictionTypes));
        this.setPetitionType(CollectionUtils.listToString(this.petitionTypes));
        this.setResolveForm(CollectionUtils.listToString(this.resolveForms));
        this.setResponsibleCompany(this.companyId);
        return this;
    }

}
