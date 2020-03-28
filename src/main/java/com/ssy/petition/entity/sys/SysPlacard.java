package com.ssy.petition.entity.sys;

import com.ssy.petition.entity.base.BaseEntity;

import java.io.Serializable;

public class SysPlacard extends BaseEntity implements Serializable {
    private String placardTitle;

    private String placardContent;

    private static final long serialVersionUID = 1L;

    public String getPlacardTitle() {
        return placardTitle;
    }

    public void setPlacardTitle(String placardTitle) {
        this.placardTitle = placardTitle == null ? null : placardTitle.trim();
    }

    public String getPlacardContent() {
        return placardContent;
    }

    public void setPlacardContent(String placardContent) {
        this.placardContent = placardContent == null ? null : placardContent.trim();
    }

}