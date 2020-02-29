package com.ssy.petition.entity.petition;

import com.ssy.petition.entity.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class PetitionContradictionResolveProcess extends BaseEntity implements Serializable {

    private Long contradictionId;

    private String resolveThematic;

    private String resolveContent;

    private static final long serialVersionUID = 1L;

    public Long getContradictionId() {
        return contradictionId;
    }

    public void setContradictionId(Long contradictionId) {
        this.contradictionId = contradictionId;
    }

    public String getResolveThematic() {
        return resolveThematic;
    }

    public void setResolveThematic(String resolveThematic) {
        this.resolveThematic = resolveThematic == null ? null : resolveThematic.trim();
    }

    public String getResolveContent() {
        return resolveContent;
    }

    public void setResolveContent(String resolveContent) {
        this.resolveContent = resolveContent == null ? null : resolveContent.trim();
    }

}