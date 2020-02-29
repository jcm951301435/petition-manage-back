package com.ssy.petition.dto.petition.result;

import com.ssy.petition.entity.petition.PetitionTroubleshoot;

public class PetitionTroubleshootResult extends PetitionTroubleshoot {

    private String insertByName;

    private String companyName;

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
}
