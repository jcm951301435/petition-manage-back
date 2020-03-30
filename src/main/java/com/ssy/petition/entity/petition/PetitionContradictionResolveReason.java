package com.ssy.petition.entity.petition;

import com.ssy.petition.entity.base.BaseEntity;

import java.io.Serializable;

public class PetitionContradictionResolveReason extends BaseEntity implements Serializable {

    private Long contradictionId;

    private String reason;

    private static final long serialVersionUID = 1L;

    public Long getContradictionId() {
        return contradictionId;
    }

    public void setContradictionId(Long contradictionId) {
        this.contradictionId = contradictionId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

}