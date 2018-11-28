package com.casic.oarp.datavisual.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EvalImperfectionExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public EvalImperfectionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
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

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(BigDecimal value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(BigDecimal value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(BigDecimal value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(BigDecimal value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<BigDecimal> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<BigDecimal> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCatalogidIsNull() {
            addCriterion("catalogid is null");
            return (Criteria) this;
        }

        public Criteria andCatalogidIsNotNull() {
            addCriterion("catalogid is not null");
            return (Criteria) this;
        }

        public Criteria andCatalogidEqualTo(String value) {
            addCriterion("catalogid =", value, "catalogid");
            return (Criteria) this;
        }

        public Criteria andCatalogidNotEqualTo(String value) {
            addCriterion("catalogid <>", value, "catalogid");
            return (Criteria) this;
        }

        public Criteria andCatalogidGreaterThan(String value) {
            addCriterion("catalogid >", value, "catalogid");
            return (Criteria) this;
        }

        public Criteria andCatalogidGreaterThanOrEqualTo(String value) {
            addCriterion("catalogid >=", value, "catalogid");
            return (Criteria) this;
        }

        public Criteria andCatalogidLessThan(String value) {
            addCriterion("catalogid <", value, "catalogid");
            return (Criteria) this;
        }

        public Criteria andCatalogidLessThanOrEqualTo(String value) {
            addCriterion("catalogid <=", value, "catalogid");
            return (Criteria) this;
        }

        public Criteria andCatalogidLike(String value) {
            addCriterion("catalogid like", value, "catalogid");
            return (Criteria) this;
        }

        public Criteria andCatalogidNotLike(String value) {
            addCriterion("catalogid not like", value, "catalogid");
            return (Criteria) this;
        }

        public Criteria andCatalogidIn(List<String> values) {
            addCriterion("catalogid in", values, "catalogid");
            return (Criteria) this;
        }

        public Criteria andCatalogidNotIn(List<String> values) {
            addCriterion("catalogid not in", values, "catalogid");
            return (Criteria) this;
        }

        public Criteria andCatalogidBetween(String value1, String value2) {
            addCriterion("catalogid between", value1, value2, "catalogid");
            return (Criteria) this;
        }

        public Criteria andCatalogidNotBetween(String value1, String value2) {
            addCriterion("catalogid not between", value1, value2, "catalogid");
            return (Criteria) this;
        }

        public Criteria andManagementidIsNull() {
            addCriterion("managementid is null");
            return (Criteria) this;
        }

        public Criteria andManagementidIsNotNull() {
            addCriterion("managementid is not null");
            return (Criteria) this;
        }

        public Criteria andManagementidEqualTo(String value) {
            addCriterion("managementid =", value, "managementid");
            return (Criteria) this;
        }

        public Criteria andManagementidNotEqualTo(String value) {
            addCriterion("managementid <>", value, "managementid");
            return (Criteria) this;
        }

        public Criteria andManagementidGreaterThan(String value) {
            addCriterion("managementid >", value, "managementid");
            return (Criteria) this;
        }

        public Criteria andManagementidGreaterThanOrEqualTo(String value) {
            addCriterion("managementid >=", value, "managementid");
            return (Criteria) this;
        }

        public Criteria andManagementidLessThan(String value) {
            addCriterion("managementid <", value, "managementid");
            return (Criteria) this;
        }

        public Criteria andManagementidLessThanOrEqualTo(String value) {
            addCriterion("managementid <=", value, "managementid");
            return (Criteria) this;
        }

        public Criteria andManagementidLike(String value) {
            addCriterion("managementid like", value, "managementid");
            return (Criteria) this;
        }

        public Criteria andManagementidNotLike(String value) {
            addCriterion("managementid not like", value, "managementid");
            return (Criteria) this;
        }

        public Criteria andManagementidIn(List<String> values) {
            addCriterion("managementid in", values, "managementid");
            return (Criteria) this;
        }

        public Criteria andManagementidNotIn(List<String> values) {
            addCriterion("managementid not in", values, "managementid");
            return (Criteria) this;
        }

        public Criteria andManagementidBetween(String value1, String value2) {
            addCriterion("managementid between", value1, value2, "managementid");
            return (Criteria) this;
        }

        public Criteria andManagementidNotBetween(String value1, String value2) {
            addCriterion("managementid not between", value1, value2, "managementid");
            return (Criteria) this;
        }

        public Criteria andImperDescrIsNull() {
            addCriterion("imper_descr is null");
            return (Criteria) this;
        }

        public Criteria andImperDescrIsNotNull() {
            addCriterion("imper_descr is not null");
            return (Criteria) this;
        }

        public Criteria andImperDescrEqualTo(String value) {
            addCriterion("imper_descr =", value, "imperDescr");
            return (Criteria) this;
        }

        public Criteria andImperDescrNotEqualTo(String value) {
            addCriterion("imper_descr <>", value, "imperDescr");
            return (Criteria) this;
        }

        public Criteria andImperDescrGreaterThan(String value) {
            addCriterion("imper_descr >", value, "imperDescr");
            return (Criteria) this;
        }

        public Criteria andImperDescrGreaterThanOrEqualTo(String value) {
            addCriterion("imper_descr >=", value, "imperDescr");
            return (Criteria) this;
        }

        public Criteria andImperDescrLessThan(String value) {
            addCriterion("imper_descr <", value, "imperDescr");
            return (Criteria) this;
        }

        public Criteria andImperDescrLessThanOrEqualTo(String value) {
            addCriterion("imper_descr <=", value, "imperDescr");
            return (Criteria) this;
        }

        public Criteria andImperDescrLike(String value) {
            addCriterion("imper_descr like", value, "imperDescr");
            return (Criteria) this;
        }

        public Criteria andImperDescrNotLike(String value) {
            addCriterion("imper_descr not like", value, "imperDescr");
            return (Criteria) this;
        }

        public Criteria andImperDescrIn(List<String> values) {
            addCriterion("imper_descr in", values, "imperDescr");
            return (Criteria) this;
        }

        public Criteria andImperDescrNotIn(List<String> values) {
            addCriterion("imper_descr not in", values, "imperDescr");
            return (Criteria) this;
        }

        public Criteria andImperDescrBetween(String value1, String value2) {
            addCriterion("imper_descr between", value1, value2, "imperDescr");
            return (Criteria) this;
        }

        public Criteria andImperDescrNotBetween(String value1, String value2) {
            addCriterion("imper_descr not between", value1, value2, "imperDescr");
            return (Criteria) this;
        }

        public Criteria andEvaldescrIsNull() {
            addCriterion("evaldescr is null");
            return (Criteria) this;
        }

        public Criteria andEvaldescrIsNotNull() {
            addCriterion("evaldescr is not null");
            return (Criteria) this;
        }

        public Criteria andEvaldescrEqualTo(String value) {
            addCriterion("evaldescr =", value, "evaldescr");
            return (Criteria) this;
        }

        public Criteria andEvaldescrNotEqualTo(String value) {
            addCriterion("evaldescr <>", value, "evaldescr");
            return (Criteria) this;
        }

        public Criteria andEvaldescrGreaterThan(String value) {
            addCriterion("evaldescr >", value, "evaldescr");
            return (Criteria) this;
        }

        public Criteria andEvaldescrGreaterThanOrEqualTo(String value) {
            addCriterion("evaldescr >=", value, "evaldescr");
            return (Criteria) this;
        }

        public Criteria andEvaldescrLessThan(String value) {
            addCriterion("evaldescr <", value, "evaldescr");
            return (Criteria) this;
        }

        public Criteria andEvaldescrLessThanOrEqualTo(String value) {
            addCriterion("evaldescr <=", value, "evaldescr");
            return (Criteria) this;
        }

        public Criteria andEvaldescrLike(String value) {
            addCriterion("evaldescr like", value, "evaldescr");
            return (Criteria) this;
        }

        public Criteria andEvaldescrNotLike(String value) {
            addCriterion("evaldescr not like", value, "evaldescr");
            return (Criteria) this;
        }

        public Criteria andEvaldescrIn(List<String> values) {
            addCriterion("evaldescr in", values, "evaldescr");
            return (Criteria) this;
        }

        public Criteria andEvaldescrNotIn(List<String> values) {
            addCriterion("evaldescr not in", values, "evaldescr");
            return (Criteria) this;
        }

        public Criteria andEvaldescrBetween(String value1, String value2) {
            addCriterion("evaldescr between", value1, value2, "evaldescr");
            return (Criteria) this;
        }

        public Criteria andEvaldescrNotBetween(String value1, String value2) {
            addCriterion("evaldescr not between", value1, value2, "evaldescr");
            return (Criteria) this;
        }

        public Criteria andFjIsNull() {
            addCriterion("fj is null");
            return (Criteria) this;
        }

        public Criteria andFjIsNotNull() {
            addCriterion("fj is not null");
            return (Criteria) this;
        }

        public Criteria andFjEqualTo(String value) {
            addCriterion("fj =", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotEqualTo(String value) {
            addCriterion("fj <>", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjGreaterThan(String value) {
            addCriterion("fj >", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjGreaterThanOrEqualTo(String value) {
            addCriterion("fj >=", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjLessThan(String value) {
            addCriterion("fj <", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjLessThanOrEqualTo(String value) {
            addCriterion("fj <=", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjLike(String value) {
            addCriterion("fj like", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotLike(String value) {
            addCriterion("fj not like", value, "fj");
            return (Criteria) this;
        }

        public Criteria andFjIn(List<String> values) {
            addCriterion("fj in", values, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotIn(List<String> values) {
            addCriterion("fj not in", values, "fj");
            return (Criteria) this;
        }

        public Criteria andFjBetween(String value1, String value2) {
            addCriterion("fj between", value1, value2, "fj");
            return (Criteria) this;
        }

        public Criteria andFjNotBetween(String value1, String value2) {
            addCriterion("fj not between", value1, value2, "fj");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table eval_imperfection
     *
     * @mbg.generated do_not_delete_during_merge Thu Oct 25 17:18:02 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}