package com.ssy.petition.entity.cal;

import com.ssy.petition.annotation.ExcelBook;
import com.ssy.petition.annotation.ExcelColumn;

import java.math.BigDecimal;

/**
 * 公开回复率
 * @author: jcm
 * @date: 2020/08/24
 */
@ExcelBook(title = "公开回复率", importRowBeginCount = 3, showIndex = false)
public class PublicReply extends ParamsEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 已公开
     */
    @ExcelColumn(text = "已公开", colWidth = 12 * 256, sort=1)
    private Integer publicCount;

    /**
     * 应公开
     */
    @ExcelColumn(text = "应公开", colWidth = 12 * 256, sort=2)
    private Integer count;

    /**
     * 公开率
     */
    @ExcelColumn(text = "公开率", colWidth = 12 * 256, sort=3)
    private BigDecimal rate;

    public Integer getPublicCount() {
        return publicCount;
    }

    public void setPublicCount(Integer publicCount) {
        this.publicCount = publicCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "PublicReply{" +
                ", publicCount=" + publicCount +
                ", count=" + count +
                ", rate=" + rate +
                '}';
    }
}
