package com.ssy.petition.entity.petition;

import com.ssy.petition.entity.base.BaseEntity;

import java.io.Serializable;

public class PetitionCompany extends BaseEntity implements Serializable {

    private String name;

    private String contactName;

    private String contactTelephone;

    private Integer sort;

    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
