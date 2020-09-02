package com.ssy.petition.dto.cal.result;

import com.ssy.petition.entity.cal.Calculated;

/**
 * @author: jcm
 * @date: 2020/08/27
 */
public class CalculatedResult extends Calculated {

    private String insertByName;

    public String getInsertByName() {
        return insertByName;
    }

    public void setInsertByName(String insertByName) {
        this.insertByName = insertByName;
    }

}
