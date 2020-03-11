package com.ssy.petition.dto.petition.result;

import com.ssy.petition.annotation.ExcelBook;
import com.ssy.petition.annotation.ExcelColumn;
import com.ssy.petition.entity.petition.PetitionTroubleshoot;

@ExcelBook(title = "矛盾排查表")
public class PetitionTroubleshootResult extends PetitionTroubleshoot {

    private String insertByName;

    @ExcelColumn(text = "归口集团", colWidth = 12 * 256, sort=1)
    private String companyName;

    @ExcelColumn(text = "群体或个体", colWidth = 12 * 256, sort=2)
    private String teamPetitionStateStr;

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

    public String getTeamPetitionStateStr() {
        return teamPetitionStateStr;
    }

    public void setTeamPetitionStateStr(String teamPetitionStateStr) {
        if (this.getTeamPetitionState() == null) {
            this.setTeamPetitionState("群体".equals(teamPetitionStateStr));
        }
        this.teamPetitionStateStr = teamPetitionStateStr;
    }

    @Override
    public void setTeamPetitionState(Boolean teamPetitionState) {
        if (this.getTeamPetitionStateStr() == null) {
            this.teamPetitionStateStr = teamPetitionState ? "群体" : "单体";
        }
        super.setTeamPetitionState(teamPetitionState);
    }
}
