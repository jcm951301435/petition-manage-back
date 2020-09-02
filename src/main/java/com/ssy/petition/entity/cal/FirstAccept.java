package com.ssy.petition.entity.cal;

import com.ssy.petition.annotation.ExcelBook;
import com.ssy.petition.annotation.ExcelColumn;

import java.math.BigDecimal;

/**
 * 初次信访办理联系率
 * @author: jcm
 * @date: 2020/08/24
 */
@ExcelBook(title = "初次信访办理联系率", importRowBeginCount = 2, showIndex = false, importColBeginCount = 1)
public class FirstAccept extends ParamsEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 序号
     */
    private Integer index;

    /**
     * 总件数
     */
    @ExcelColumn(text = "总件数", colWidth = 12 * 256, sort=1)
    private Integer count;

    /**
     * 有办理联系记录件数
     */
    @ExcelColumn(text = "有办理联系记录件数", colWidth = 12 * 256, sort=2)
    private Integer contactCount;

    /**
     * 未办理联系件数
     */
    @ExcelColumn(text = "未办理联系件数", colWidth = 12 * 256, sort=3)
    private Integer noContactCount;

    /**
     * 比率
     */
    @ExcelColumn(text = "比率", colWidth = 12 * 256, sort=4)
    private BigDecimal contactRate;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getContactCount() {
        return contactCount;
    }

    public void setContactCount(Integer contactCount) {
        this.contactCount = contactCount;
    }

    public Integer getNoContactCount() {
        return noContactCount;
    }

    public void setNoContactCount(Integer noContactCount) {
        this.noContactCount = noContactCount;
    }

    public BigDecimal getContactRate() {
        return contactRate;
    }

    public void setContactRate(BigDecimal contactRate) {
        this.contactRate = contactRate;
    }

    @Override
    public String toString() {
        return "FirstAccept{" +
                "index=" + index +
                ", count=" + count +
                ", contactCount=" + contactCount +
                ", noContactCount=" + noContactCount +
                ", contactRate=" + contactRate +
                '}';
    }
}
