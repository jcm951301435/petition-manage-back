package com.ssy.petition.entity.cal;

import com.ssy.petition.annotation.ExcelBook;
import com.ssy.petition.annotation.ExcelColumn;
import com.ssy.petition.annotation.ExcelFormatEnum;
import com.ssy.petition.entity.base.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 计算结果
 * @author: jcm
 * @date: 2020/08/24
 */
@ExcelBook(title = "信访工作目标责任考核数据通报表", importRowBeginCount = 2)
public class Calculated extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String batchNumber;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 单位名称
     */

    private String companyName;

    @ExcelColumn(text = {"单位"}, colWidth = 12 * 256, sort=0)
    private String shortName;

    /**
     * 按期受理告知率
     */
    @ExcelColumn(text = "按期受理告知率", colWidth = 12 * 256, sort=1, format = ExcelFormatEnum.PERCENTAGE)
    private BigDecimal informRate;

    /**
     * 按期办结率
     */
    @ExcelColumn(text = "按期办结率", colWidth = 12 * 256, sort=2, format = ExcelFormatEnum.PERCENTAGE)
    private BigDecimal finishRate;

    /**
     * 初次信访平均受理周期
     */
    @ExcelColumn(text = "平均受理周期", colWidth = 12 * 256, sort=3, format = ExcelFormatEnum.PERCENTAGE)
    private BigDecimal initialAcceptCycle;

    /**
     * 初次信访平均答复周期
     */
    @ExcelColumn(text = "平均答复周期", colWidth = 12 * 256, sort=4, format = ExcelFormatEnum.PERCENTAGE)
    private BigDecimal initialReplyCycle;

    /**
     * 初次信访办理联系率
     */
    @ExcelColumn(text = "初次信访办理联系率", colWidth = 12 * 256, sort=5, format = ExcelFormatEnum.PERCENTAGE)
    private BigDecimal initialContactRate;

    /**
     * 重复信访率
     */
    @ExcelColumn(text = "重复信访率", colWidth = 12 * 256, sort=6, format = ExcelFormatEnum.PERCENTAGE)
    private BigDecimal repeatPetitionRate;

    /**
     * 公开回复率
     */
    @ExcelColumn(text = "公开回复率", colWidth = 12 * 256, sort=7, format = ExcelFormatEnum.PERCENTAGE)
    private BigDecimal publicReplyRate;

    /**
     * 信访工作机构参评满意率
     */
    @ExcelColumn(text = "信访工作机构参评满意率", colWidth = 12 * 256, sort=8, format = ExcelFormatEnum.PERCENTAGE)
    private BigDecimal satisfactionRate;

    /**
     * 有权处理单位参评满意率
     */
    @ExcelColumn(text = "有权处理单位参评满意率", colWidth = 12 * 256, sort=9, format = ExcelFormatEnum.PERCENTAGE)
    private BigDecimal rightRate;

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public BigDecimal getInformRate() {
        return informRate;
    }

    public void setInformRate(BigDecimal informRate) {
        this.informRate = informRate;
    }

    public BigDecimal getFinishRate() {
        return finishRate;
    }

    public void setFinishRate(BigDecimal finishRate) {
        this.finishRate = finishRate;
    }

    public BigDecimal getInitialAcceptCycle() {
        return initialAcceptCycle;
    }

    public void setInitialAcceptCycle(BigDecimal initialAcceptCycle) {
        this.initialAcceptCycle = initialAcceptCycle;
    }

    public BigDecimal getInitialReplyCycle() {
        return initialReplyCycle;
    }

    public void setInitialReplyCycle(BigDecimal initialReplyCycle) {
        this.initialReplyCycle = initialReplyCycle;
    }

    public BigDecimal getInitialContactRate() {
        return initialContactRate;
    }

    public void setInitialContactRate(BigDecimal initialContactRate) {
        this.initialContactRate = initialContactRate;
    }

    public BigDecimal getRepeatPetitionRate() {
        return repeatPetitionRate;
    }

    public void setRepeatPetitionRate(BigDecimal repeatPetitionRate) {
        this.repeatPetitionRate = repeatPetitionRate;
    }

    public BigDecimal getPublicReplyRate() {
        return publicReplyRate;
    }

    public void setPublicReplyRate(BigDecimal publicReplyRate) {
        this.publicReplyRate = publicReplyRate;
    }

    public BigDecimal getSatisfactionRate() {
        return satisfactionRate;
    }

    public void setSatisfactionRate(BigDecimal satisfactionRate) {
        this.satisfactionRate = satisfactionRate;
    }

    public BigDecimal getRightRate() {
        return rightRate;
    }

    public void setRightRate(BigDecimal rightRate) {
        this.rightRate = rightRate;
    }

    @Override
    public String toString() {
        return "Calculated{" +
                "batchNumber='" + batchNumber + '\'' +
                ", sort=" + sort +
                ", companyName='" + companyName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", informRate=" + informRate +
                ", finishRate=" + finishRate +
                ", initialAcceptCycle=" + initialAcceptCycle +
                ", initialReplyCycle=" + initialReplyCycle +
                ", initialContactRate=" + initialContactRate +
                ", repeatPetitionRate=" + repeatPetitionRate +
                ", publicReplyRate=" + publicReplyRate +
                ", satisfactionRate=" + satisfactionRate +
                ", rightRate=" + rightRate +
                '}';
    }
}
