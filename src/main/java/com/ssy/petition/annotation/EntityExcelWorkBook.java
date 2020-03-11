package com.ssy.petition.annotation;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EntityExcelWorkBook {

    private Class<?> cls;

    private XSSFWorkbook workbook;

    private String title;

    private int importRowBeginCount;

    private XSSFSheet sheet;

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    public int getImportRowBeginCount() {
        return importRowBeginCount;
    }

    public void setImportRowBeginCount(int importRowBeginCount) {
        this.importRowBeginCount = importRowBeginCount;
    }

    public EntityExcelWorkBook(Class<?> cls, XSSFWorkbook workbook, String title, int importRowBeginCount) {
        this.cls = cls;
        this.workbook = workbook;
        this.title = title;
        this.importRowBeginCount = importRowBeginCount;
        init();
    }

    private void init() {
        this.sheet = workbook.createSheet(title);
    }

    public EntityExcelWorkBook() {
    }
}
