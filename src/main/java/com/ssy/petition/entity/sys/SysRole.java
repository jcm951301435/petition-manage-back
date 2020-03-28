package com.ssy.petition.entity.sys;

import com.ssy.petition.entity.base.BaseEntity;

import java.io.Serializable;

/**
 * 角色
 *
 * @author jcm
 */
public class SysRole extends BaseEntity implements Serializable {
    private String name;

    private String description;

    private Integer sort;

    private Boolean filterData;

    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getFilterData() {
        return filterData;
    }

    public void setFilterData(Boolean filterData) {
        this.filterData = filterData;
    }
}