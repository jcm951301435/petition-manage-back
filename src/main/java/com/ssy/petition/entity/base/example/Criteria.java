package com.ssy.petition.entity.base.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 规则列表
 *
 * @author jcm
 */
public class Criteria {

    private List<Criterion> criteria;

    public Criteria() {
        super();
        criteria = new ArrayList<>();
    }

    public boolean isValid() {
        return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
        return criteria;
    }

    public List<Criterion> getCriteria() {
        return criteria;
    }

    private void addCriterion(String condition) {
        if (condition == null) {
            throw new RuntimeException("Value for condition cannot be null");
        }
        criteria.add(new Criterion(condition));
    }

    private void addCriterion(String condition, Object value, String property) {
        if (value == null) {
            throw new RuntimeException("Value for " + property + " cannot be null");
        }
        criteria.add(new Criterion(condition, value));
    }

    private void addCriterion(String condition, Object value1, Object value2, String property) {
        if (value1 == null || value2 == null) {
            throw new RuntimeException("Between values for " + property + " cannot be null");
        }
        criteria.add(new Criterion(condition, value1, value2));
    }

    public Criteria andFieldIsNull(String fieldName) {
        addCriterion(fieldName + " is null");
        return this;
    }

    public Criteria andFieldIsNotNull(String fieldName) {
        addCriterion(fieldName + " is not null");
        return this;
    }

    public Criteria andFieldEqualTo(String fieldName, Object value) {
        addCriterion(fieldName + " =", value, fieldName);
        return this;
    }

    public Criteria andFieldNotEqualTo(String fieldName, Object value) {
        addCriterion(fieldName + " <>", value, fieldName);
        return this;
    }

    public Criteria andFieldGreaterThan(String fieldName, Object value) {
        addCriterion(fieldName + " >", value, fieldName);
        return this;
    }

    public Criteria andFieldGreaterThanOrEqualTo(String fieldName, Object value) {
        addCriterion(fieldName + " >=", value, fieldName);
        return this;
    }

    public Criteria andFieldLessThan(String fieldName, Object value) {
        addCriterion(fieldName + " <", value, fieldName);
        return this;
    }

    public Criteria andFieldLessThanOrEqualTo(String fieldName, Object value) {
        addCriterion(fieldName + " <=", value, fieldName);
        return this;
    }

    public Criteria andFieldIn(String fieldName, List<Object> values) {
        addCriterion(fieldName + " in", values, fieldName);
        return this;
    }

    public Criteria andFieldNotIn(String fieldName, List<Object> values) {
        addCriterion(fieldName + " not in", values, fieldName);
        return this;
    }

    public Criteria andFieldBetween(String fieldName, Object value1, Object value2) {
        addCriterion(" between", value1, value2, fieldName);
        return this;
    }

    public Criteria andFieldNotBetween(String fieldName, Object value1, Object value2) {
        addCriterion(fieldName + " not between", value1, value2, fieldName);
        return this;
    }

    public Criteria andFieldLike(String fieldName, String value) {
        addCriterion(fieldName +" like", value, fieldName);
        return (Criteria) this;
    }

    public Criteria andFieldNotLike(String fieldName, String value) {
        addCriterion(fieldName + " not like", value, fieldName);
        return (Criteria) this;
    }

}
