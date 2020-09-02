package com.ssy.petition.dto.cal.params;

/**
 * @author: jcm
 * @date: 2020/08/27
 */
public class CalculatedParams {

    private String companyName;

    private String batchNumber;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    @Override
    public String toString() {
        return "CalculatedParams{" +
                "companyName='" + companyName + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                '}';
    }
}
