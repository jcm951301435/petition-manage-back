package com.ssy.petition.excel;

import com.ssy.petition.annotation.ExcelFormatEnum;

import java.lang.reflect.Field;

public class BaseExcelColumn {

    private String fieldName;

    private String methodName;

    private String[] text;

    private int colWidth;

    private short color;

    private int sort;

    private Field field;

    private ExcelFormatEnum format;

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public int getColWidth() {
        return colWidth;
    }

    public void setColWidth(int colWidth) {
        this.colWidth = colWidth;
    }

    public short getColor() {
        return color;
    }

    public void setColor(short color) {
        this.color = color;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public ExcelFormatEnum getFormat() {
        return format;
    }

    public void setFormat(ExcelFormatEnum format) {
        this.format = format;
    }

    public BaseExcelColumn() {
    }

    public BaseExcelColumn(String fieldName, String methodName, String[] text, int colWidth, short color, int sort, Field field, ExcelFormatEnum format) {
        this.fieldName = fieldName;
        this.methodName = methodName;
        this.text = text;
        this.colWidth = colWidth;
        this.color = color;
        this.sort = sort;
        this.field = field;
        this.format = format;
    }
}
