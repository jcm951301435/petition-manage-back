package com.ssy.petition.dto.petition.result;

import com.ssy.petition.entity.petition.PetitionCompany;

public class PetitionCompanyResult extends PetitionCompany {

    private String insertByName;

    public String getInsertByName() {
        return insertByName;
    }

    public void setInsertByName(String insertByName) {
        this.insertByName = insertByName;
    }
}
