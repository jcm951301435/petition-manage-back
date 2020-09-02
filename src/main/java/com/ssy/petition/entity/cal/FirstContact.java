package com.ssy.petition.entity.cal;

import com.ssy.petition.annotation.ExcelBook;
import com.ssy.petition.annotation.ExcelColumn;

import java.math.BigDecimal;

/**
 * 初次信访事项办理效率
 * @author: jcm
 * @date: 2020/08/24
 */
@ExcelBook(title = "初次信访事项办理效率", importRowBeginCount = 3, showIndex = false)
public class FirstContact extends ParamsEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 总数
     */
    @ExcelColumn(text = "总数", colWidth = 12 * 256, sort=1)
    private Integer count;

    /**
     * 平均转送时间
     */
    @ExcelColumn(text = "平均转送时间", colWidth = 12 * 256, sort=2)
    private BigDecimal transferTime;

    /**
     * 平均受理时间
     */
    @ExcelColumn(text = "平均受理时间", colWidth = 12 * 256, sort=3)
    private BigDecimal acceptTime;

    /**
     * 平均答复时间
     */
    @ExcelColumn(text = "平均答复时间", colWidth = 12 * 256, sort=4)
    private BigDecimal replyTime;

    /**
     * 平均办理时间
     */
    @ExcelColumn(text = "平均办理时间", colWidth = 12 * 256, sort=5)
    private BigDecimal finishTime;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(BigDecimal transferTime) {
        this.transferTime = transferTime;
    }

    public BigDecimal getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(BigDecimal acceptTime) {
        this.acceptTime = acceptTime;
    }

    public BigDecimal getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(BigDecimal replyTime) {
        this.replyTime = replyTime;
    }

    public BigDecimal getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(BigDecimal finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "FirstContact{" +
                ", count=" + count +
                ", transferTime=" + transferTime +
                ", acceptTime=" + acceptTime +
                ", replyTime=" + replyTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
