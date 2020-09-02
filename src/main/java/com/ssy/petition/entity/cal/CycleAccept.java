package com.ssy.petition.entity.cal;

import com.ssy.petition.annotation.ExcelBook;
import com.ssy.petition.annotation.ExcelColumn;

import java.math.BigDecimal;

/**
 * 按期受理告知率
 *
 * @author: jcm
 * @date: 2020/08/24
 */
@ExcelBook(title = "办结率统计结果", importRowBeginCount = 3, showIndex = false)
public class CycleAccept extends ParamsEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 转送总数
     */
    @ExcelColumn(text = "转送总数", colWidth = 12 * 256, sort=1)
    private Integer transferCount;

    /**
     * 转送按期办结
     */
    @ExcelColumn(text = "转送按期办结", colWidth = 12 * 256, sort=2)
    private Integer transferFinishCount;

    /**
     * 转送超期办结
     */
    @ExcelColumn(text = "转送超期办结", colWidth = 12 * 256, sort=3)
    private Integer transferOverdueCount;

    /**
     * 转送办结率
     */
    @ExcelColumn(text = "转送办结率", colWidth = 12 * 256, sort=4)
    private BigDecimal transferRate;

    /**
     * 受理总数
     */
    @ExcelColumn(text = "受理总数", colWidth = 12 * 256, sort=5)
    private Integer acceptCount;

    /**
     * 受理按期办结
     */
    @ExcelColumn(text = "受理按期办结", colWidth = 12 * 256, sort=6)
    private Integer acceptFinishCount;

    /**
     * 受理超期办结
     */
    @ExcelColumn(text = "受理超期办结", colWidth = 12 * 256, sort=7)
    private Integer acceptOverdueCount;

    /**
     * 受理办结率
     */
    @ExcelColumn(text = "受理办结率", colWidth = 12 * 256, sort=8)
    private BigDecimal acceptRate;

    /**
     * 答复总数
     */
    @ExcelColumn(text = "答复总数", colWidth = 12 * 256, sort=9)
    private Integer replyCount;

    /**
     * 答复按期办结
     */
    @ExcelColumn(text = "答复按期办结", colWidth = 12 * 256, sort=10)
    private Integer replyFinishCount;

    /**
     * 答复超期办结
     */
    @ExcelColumn(text = "答复超期办结", colWidth = 12 * 256, sort=11)
    private Integer replyOverdueCount;

    /**
     * 答复办结率
     */
    @ExcelColumn(text = "答复办结率", colWidth = 12 * 256, sort=12)
    private BigDecimal replyRate;

    /**
     * 总办结率
     */
    @ExcelColumn(text = "总办结率", colWidth = 12 * 256, sort=13)
    private BigDecimal rate;

    public Integer getTransferCount() {
        return transferCount;
    }

    public void setTransferCount(Integer transferCount) {
        this.transferCount = transferCount;
    }

    public Integer getTransferFinishCount() {
        return transferFinishCount;
    }

    public void setTransferFinishCount(Integer transferFinishCount) {
        this.transferFinishCount = transferFinishCount;
    }

    public Integer getTransferOverdueCount() {
        return transferOverdueCount;
    }

    public void setTransferOverdueCount(Integer transferOverdueCount) {
        this.transferOverdueCount = transferOverdueCount;
    }

    public BigDecimal getTransferRate() {
        return transferRate;
    }

    public void setTransferRate(BigDecimal transferRate) {
        this.transferRate = transferRate;
    }

    public Integer getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(Integer acceptCount) {
        this.acceptCount = acceptCount;
    }

    public Integer getAcceptFinishCount() {
        return acceptFinishCount;
    }

    public void setAcceptFinishCount(Integer acceptFinishCount) {
        this.acceptFinishCount = acceptFinishCount;
    }

    public Integer getAcceptOverdueCount() {
        return acceptOverdueCount;
    }

    public void setAcceptOverdueCount(Integer acceptOverdueCount) {
        this.acceptOverdueCount = acceptOverdueCount;
    }

    public BigDecimal getAcceptRate() {
        return acceptRate;
    }

    public void setAcceptRate(BigDecimal acceptRate) {
        this.acceptRate = acceptRate;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getReplyFinishCount() {
        return replyFinishCount;
    }

    public void setReplyFinishCount(Integer replyFinishCount) {
        this.replyFinishCount = replyFinishCount;
    }

    public Integer getReplyOverdueCount() {
        return replyOverdueCount;
    }

    public void setReplyOverdueCount(Integer replyOverdueCount) {
        this.replyOverdueCount = replyOverdueCount;
    }

    public BigDecimal getReplyRate() {
        return replyRate;
    }

    public void setReplyRate(BigDecimal replyRate) {
        this.replyRate = replyRate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CycleAccept{" +
                "transferCount=" + transferCount +
                ", transferFinishCount=" + transferFinishCount +
                ", transferOverdueCount=" + transferOverdueCount +
                ", transferRate=" + transferRate +
                ", acceptCount=" + acceptCount +
                ", acceptFinishCount=" + acceptFinishCount +
                ", acceptOverdueCount=" + acceptOverdueCount +
                ", acceptRate=" + acceptRate +
                ", replyCount=" + replyCount +
                ", replyFinishCount=" + replyFinishCount +
                ", replyOverdueCount=" + replyOverdueCount +
                ", replyRate=" + replyRate +
                ", rate=" + rate +
                '}';
    }
}
