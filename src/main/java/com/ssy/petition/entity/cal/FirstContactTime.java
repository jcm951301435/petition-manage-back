package com.ssy.petition.entity.cal;

import com.ssy.petition.annotation.ExcelBook;
import com.ssy.petition.annotation.ExcelColumn;

/**
 * @author: jcm
 * @date: 2020/09/08
 */
@ExcelBook(title = "初次信访事项办理效率", importRowBeginCount = 3, showIndex = false)
public class FirstContactTime extends ParamsEntity{

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
    private String transferTime;

    /**
     * 平均受理时间
     */
    @ExcelColumn(text = "平均受理时间", colWidth = 12 * 256, sort=3)
    private String acceptTime;

    /**
     * 平均答复时间
     */
    @ExcelColumn(text = "平均答复时间", colWidth = 12 * 256, sort=4)
    private String replyTime;

    /**
     * 平均办理时间
     */
    @ExcelColumn(text = "平均办理时间", colWidth = 12 * 256, sort=5)
    private String finishTime;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
        this.transferTime = transferTime;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
