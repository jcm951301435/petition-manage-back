package com.ssy.petition.util;

import com.ssy.petition.entity.base.BaseTreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {

    public static<T extends  BaseTreeNode> List<T> getTreeList(List<T> list) {
        return findChildList(null, list);
    }

    @SuppressWarnings("unchecked")
    private static<T extends  BaseTreeNode> List<T> findChildList(Long pid, List<T> list) {
        List<T> childList = new ArrayList<>();
        for (T entity : list) {
            Long pidTemp = entity.getPid();
            boolean isChild;
            if (pid == null) {
                isChild = pidTemp == null;
            } else {
                isChild = pid.equals(pidTemp);
            }
            if (isChild) {
                List<T> childListTemp = findChildList(entity.getId(), list);
                entity.setChildList(childListTemp);
                if (CollectionUtils.isNotEmpty(childListTemp)) {
                    entity.setHasChild(true);
                }
                childList.add(entity);
            }
        }
        return childList;
    }

}
