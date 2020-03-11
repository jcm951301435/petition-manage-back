package com.ssy.petition.excel;

import java.util.List;

public class BaseExcelBook {

    private String title;

    private int importRowBeginCount;

    private List<BaseExcelColumn> columnList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BaseExcelColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<BaseExcelColumn> columnList) {
        this.columnList = columnList;
    }

    public int getImportRowBeginCount() {
        return importRowBeginCount;
    }

    public void setImportRowBeginCount(int importRowBeginCount) {
        this.importRowBeginCount = importRowBeginCount;
    }

    public BaseExcelBook(String title, int importRowBeginCount, List<BaseExcelColumn> columnList) {
        this.title = title;
        this.importRowBeginCount = importRowBeginCount;
        this.columnList = columnList;
    }

    public BaseExcelBook(String title, int importRowBeginCount) {
        this.title = title;
        this.importRowBeginCount = importRowBeginCount;
    }

    public BaseExcelBook() {
    }
}
