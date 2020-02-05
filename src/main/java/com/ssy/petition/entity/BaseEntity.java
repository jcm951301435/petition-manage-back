package com.ssy.petition.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ssy.petition.util.DateUtils;
import com.ssy.petition.util.SnowFlakeUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jcm
 */
public class BaseEntity implements Serializable {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private Date insertOn;

    private Long insertBy;

    private Date updateOn;

    private Long updateBy;

    private Boolean deleteFlag;

    private Date deleteOn;

    private Long deleteBy;

    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInsertOn() {
        return insertOn;
    }

    public void setInsertOn(Date insertOn) {
        this.insertOn = insertOn;
    }

    public Long getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(Long insertBy) {
        this.insertBy = insertBy;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getDeleteOn() {
        return deleteOn;
    }

    public void setDeleteOn(Date deleteOn) {
        this.deleteOn = deleteOn;
    }

    public Long getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(Long deleteBy) {
        this.deleteBy = deleteBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BaseEntity() {
    }

    public void init() {
        Date now = DateUtils.now();
        this.id = new SnowFlakeUtils(0, 0).nextId();
        this.insertOn = now;
        this.updateOn = now;
        this.deleteFlag = false;
    }

    public static void main(String[] args) {

    }
}