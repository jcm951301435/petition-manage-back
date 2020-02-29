package com.ssy.petition.entity.petition;

import com.ssy.petition.entity.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class PetitionContradictionContent extends BaseEntity implements Serializable {

    private Long contradictionId;

    private String contradictionContent;

    private static final long serialVersionUID = 1L;

    public Long getContradictionId() {
        return contradictionId;
    }

    public void setContradictionId(Long contradictionId) {
        this.contradictionId = contradictionId;
    }

    public String getContradictionContent() {
        return contradictionContent;
    }

    public void setContradictionContent(String contradictionContent) {
        this.contradictionContent = contradictionContent == null ? null : contradictionContent.trim();
    }

}