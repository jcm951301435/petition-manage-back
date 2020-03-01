package com.ssy.petition.entity.sys;

import com.ssy.petition.entity.base.BaseEntity;

import java.io.Serializable;

public class SysFile extends BaseEntity implements Serializable {
    private Long contradictionId;

    private String filePath;

    private String fileName;

    private String fileOldName;

    private static final long serialVersionUID = 1L;

    public Long getContradictionId() {
        return contradictionId;
    }

    public void setContradictionId(Long contradictionId) {
        this.contradictionId = contradictionId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileOldName() {
        return fileOldName;
    }

    public void setFileOldName(String fileOldName) {
        this.fileOldName = fileOldName;
    }
}