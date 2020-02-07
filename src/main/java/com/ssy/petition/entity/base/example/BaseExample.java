package com.ssy.petition.entity.base.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础 example 查询参数
 *
 * @author jcm
 */
public class BaseExample {

    private String orderByClause;

    private boolean distinct;

    private List<Criteria> orCriteria;

    public BaseExample() {
        orCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOrCriteria() {
        return orCriteria;
    }

    public void or(Criteria criteria) {
        orCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        orCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (orCriteria.size() == 0) {
            orCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        orCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

}
