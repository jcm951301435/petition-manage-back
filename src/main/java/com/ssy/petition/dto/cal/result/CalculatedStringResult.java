package com.ssy.petition.dto.cal.result;

import com.ssy.petition.annotation.ExcelBook;
import com.ssy.petition.annotation.ExcelColumn;

/**
 * @author: jcm
 * @date: 2020/09/03
 */
@ExcelBook(title = "信访工作目标责任考核数据通报表", importRowBeginCount = 2)
public class CalculatedStringResult {

    private String batchNumber;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 单位名称
     */
    @ExcelColumn(text = {"单位"}, colWidth = 12 * 256, sort=0)
    private String companyName;

    /**
     * 按期受理告知率
     */
    @ExcelColumn(text = "按期受理告知率", colWidth = 12 * 256, sort=1)
    private String informRate;

    /**
     * 按期办结率
     */
    @ExcelColumn(text = "按期办结率", colWidth = 12 * 256, sort=2)
    private String finishRate;

    /**
     * 初次信访平均受理周期
     */
    @ExcelColumn(text = "平均受理周期", colWidth = 12 * 256, sort=3)
    private String initialAcceptCycle;

    /**
     * 初次信访平均答复周期
     */
    @ExcelColumn(text = "平均答复周期", colWidth = 12 * 256, sort=4)
    private String initialReplyCycle;

    /**
     * 初次信访办理联系率
     */
    @ExcelColumn(text = "初次信访办理联系率", colWidth = 12 * 256, sort=5)
    private String initialContactRate;

    /**
     * 重复信访率
     */
    @ExcelColumn(text = "重复信访率", colWidth = 12 * 256, sort=6)
    private String repeatPetitionRate;

    /**
     * 公开回复率
     */
    @ExcelColumn(text = "公开回复率", colWidth = 12 * 256, sort=7)
    private String publicReplyRate;

    /**
     * 信访工作机构参评满意率
     */
    @ExcelColumn(text = "信访工作机构参评满意率", colWidth = 12 * 256, sort=8)
    private String satisfactionRate;

    /**
     * 有权处理单位参评满意率
     */
    @ExcelColumn(text = "有权处理单位参评满意率", colWidth = 12 * 256, sort=9)
    private String rightRate;

    private String insertByName;

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

    public String getInformRate() {
        return informRate;
    }

    public void setInformRate(String informRate) {
        this.informRate = informRate;
    }

    public String getFinishRate() {
        return finishRate;
    }

    public void setFinishRate(String finishRate) {
        this.finishRate = finishRate;
    }

    public String getInitialAcceptCycle() {
        return initialAcceptCycle;
    }

    public void setInitialAcceptCycle(String initialAcceptCycle) {
        this.initialAcceptCycle = initialAcceptCycle;
    }

    public String getInitialReplyCycle() {
        return initialReplyCycle;
    }

    public void setInitialReplyCycle(String initialReplyCycle) {
        this.initialReplyCycle = initialReplyCycle;
    }

    public String getInitialContactRate() {
        return initialContactRate;
    }

    public void setInitialContactRate(String initialContactRate) {
        this.initialContactRate = initialContactRate;
    }

    public String getRepeatPetitionRate() {
        return repeatPetitionRate;
    }

    public void setRepeatPetitionRate(String repeatPetitionRate) {
        this.repeatPetitionRate = repeatPetitionRate;
    }

    public String getPublicReplyRate() {
        return publicReplyRate;
    }

    public void setPublicReplyRate(String publicReplyRate) {
        this.publicReplyRate = publicReplyRate;
    }

    public String getSatisfactionRate() {
        return satisfactionRate;
    }

    public void setSatisfactionRate(String satisfactionRate) {
        this.satisfactionRate = satisfactionRate;
    }

    public String getRightRate() {
        return rightRate;
    }

    public void setRightRate(String rightRate) {
        this.rightRate = rightRate;
    }

    public String getInsertByName() {
        return insertByName;
    }

    public void setInsertByName(String insertByName) {
        this.insertByName = insertByName;
    }
}
