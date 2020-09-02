package com.ssy.petition.entity.cal;

import com.ssy.petition.annotation.ExcelColumn;
import com.ssy.petition.entity.base.BaseEntity;

/**
 * 参数实体
 * @author: jcm
 * @date: 2020/08/26
 */
public class ParamsEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 公司名称
     */
    @ExcelColumn(text = "公司名称", colWidth = 12 * 256, sort=0)
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "ParamsEntity{" +
                "companyName='" + companyName + '\'' +
                '}';
    }
}
