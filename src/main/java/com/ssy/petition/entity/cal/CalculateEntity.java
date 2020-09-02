package com.ssy.petition.entity.cal;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 *
 * 计算实体
 * @author: jcm
 * @date: 2020/08/26
 */
public class CalculateEntity<T extends ParamsEntity> {

    private MultipartFile file;

    private List<T> list;

    private Map<String, T> map;

    private List<String> companyNameList;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Map<String, T> getMap() {
        return map;
    }

    public void setMap(Map<String, T> map) {
        this.map = map;
    }

    public List<String> getCompanyNameList() {
        return companyNameList;
    }

    public void setCompanyNameList(List<String> companyNameList) {
        this.companyNameList = companyNameList;
    }

    @Override
    public String toString() {
        return "CalculateEntity{" +
                "file=" + file +
                ", list=" + list +
                ", map=" + map +
                ", companyNameList=" + companyNameList +
                '}';
    }
}
