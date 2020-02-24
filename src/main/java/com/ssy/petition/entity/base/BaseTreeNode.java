package com.ssy.petition.entity.base;

import java.util.ArrayList;
import java.util.List;

public class BaseTreeNode<T extends  BaseTreeNode> extends BaseEntity {

    private Long pid;

    private List<T> childList = new ArrayList<>();

    private boolean hasChild;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<T> getChildList() {
        return childList;
    }

    public void setChildList(List<T> childList) {
        childList = childList != null ? childList : new ArrayList<>();
        this.childList = childList;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

}
