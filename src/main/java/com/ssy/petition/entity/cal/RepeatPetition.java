package com.ssy.petition.entity.cal;

import com.ssy.petition.annotation.ExcelBook;
import com.ssy.petition.annotation.ExcelColumn;

import java.math.BigDecimal;

/**
 * 重复信访率
 * @author: jcm
 * @date: 2020/08/24
 */
@ExcelBook(title = "重复信访率", importRowBeginCount = 3, showIndex = false)
public class RepeatPetition extends ParamsEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 办结初件
     */
    @ExcelColumn(text = "办结初件", colWidth = 12 * 256, sort=1)
    private Integer finishCount;

    /**
     * 首次初件
     */
    @ExcelColumn(text = "首次初件", colWidth = 12 * 256, sort=2)
    private Integer firstCount;

    /**
     * 比率
     */
    @ExcelColumn(text = "比率", colWidth = 12 * 256, sort=3)
    private BigDecimal rate;

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public Integer getFirstCount() {
        return firstCount;
    }

    public void setFirstCount(Integer firstCount) {
        this.firstCount = firstCount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "RepeatPetition{" +
                ", finishCount=" + finishCount +
                ", firstCount=" + firstCount +
                ", rate=" + rate +
                '}';
    }
}
