package com.ssy.petition.entity.cal;

import com.ssy.petition.annotation.ExcelBook;
import com.ssy.petition.annotation.ExcelColumn;

import java.math.BigDecimal;

/**
 * 信访工作机构参评满意率
 * @author: jcm
 * @date: 2020/08/24
 */
@ExcelBook(title = "信访工作机构参评满意率", importRowBeginCount = 3, showIndex = false)
public class Satisfaction extends ParamsEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 总数
     */
    @ExcelColumn(text = "总数", colWidth = 12 * 256, sort=1)
    private Integer allCount;

    /**
     * 未评价
     */
    @ExcelColumn(text = "未评价", colWidth = 12 * 256, sort=2)
    private Integer noAssess;

    /**
     * 已评价总数
     */
    @ExcelColumn(text = "已评价总数", colWidth = 12 * 256, sort=3)
    private Integer assessCount;

    /**
     * 已评价满意数
     */
    @ExcelColumn(text = "已评价满意数", colWidth = 12 * 256, sort=4)
    private Integer assessSatisAllCount;

    /**
     * 已评价基本满意数
     */
    @ExcelColumn(text = "已评价基本满意数", colWidth = 12 * 256, sort=5)
    private Integer assessSatisSomeCount;

    /**
     * 已评价不满意数
     */
    @ExcelColumn(text = "已评价不满意数", colWidth = 12 * 256, sort=6)
    private Integer assessNoSatisCount;

    /**
     * 超期未评价数
     */
    @ExcelColumn(text = "超期未评价数", colWidth = 12 * 256, sort=7)
    private Integer overdueNoAssessCount;

    /**
     * 满意率
     */
    @ExcelColumn(text = "满意率", colWidth = 12 * 256, sort=8)
    private BigDecimal satisRate;

    /**
     * 参评率
     */
    @ExcelColumn(text = "参评率", colWidth = 12 * 256, sort=9)
    private BigDecimal assessRate;

    /**
     * 参评满意率
     */
    @ExcelColumn(text = "参评满意率", colWidth = 12 * 256, sort=10)
    private BigDecimal assessSatisRate;

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

    public Integer getNoAssess() {
        return noAssess;
    }

    public void setNoAssess(Integer noAssess) {
        this.noAssess = noAssess;
    }

    public Integer getAssessCount() {
        return assessCount;
    }

    public void setAssessCount(Integer assessCount) {
        this.assessCount = assessCount;
    }

    public Integer getAssessSatisAllCount() {
        return assessSatisAllCount;
    }

    public void setAssessSatisAllCount(Integer assessSatisAllCount) {
        this.assessSatisAllCount = assessSatisAllCount;
    }

    public Integer getAssessSatisSomeCount() {
        return assessSatisSomeCount;
    }

    public void setAssessSatisSomeCount(Integer assessSatisSomeCount) {
        this.assessSatisSomeCount = assessSatisSomeCount;
    }

    public Integer getAssessNoSatisCount() {
        return assessNoSatisCount;
    }

    public void setAssessNoSatisCount(Integer assessNoSatisCount) {
        this.assessNoSatisCount = assessNoSatisCount;
    }

    public Integer getOverdueNoAssessCount() {
        return overdueNoAssessCount;
    }

    public void setOverdueNoAssessCount(Integer overdueNoAssessCount) {
        this.overdueNoAssessCount = overdueNoAssessCount;
    }

    public BigDecimal getSatisRate() {
        return satisRate;
    }

    public void setSatisRate(BigDecimal satisRate) {
        this.satisRate = satisRate;
    }

    public BigDecimal getAssessRate() {
        return assessRate;
    }

    public void setAssessRate(BigDecimal assessRate) {
        this.assessRate = assessRate;
    }

    public BigDecimal getAssessSatisRate() {
        return assessSatisRate;
    }

    public void setAssessSatisRate(BigDecimal assessSatisRate) {
        this.assessSatisRate = assessSatisRate;
    }

    @Override
    public String toString() {
        return "Satisfaction{" +
                ", allCount=" + allCount +
                ", noAssess=" + noAssess +
                ", assessCount=" + assessCount +
                ", assessSatisAllCount=" + assessSatisAllCount +
                ", assessSatisSomeCount=" + assessSatisSomeCount +
                ", assessNoSatisCount=" + assessNoSatisCount +
                ", overdueNoAssessCount=" + overdueNoAssessCount +
                ", satisRate=" + satisRate +
                ", assessRate=" + assessRate +
                ", assessSatisRate=" + assessSatisRate +
                '}';
    }
}
