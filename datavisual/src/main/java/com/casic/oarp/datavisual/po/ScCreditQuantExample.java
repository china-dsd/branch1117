package com.casic.oarp.datavisual.po;

import io.swagger.models.auth.In;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScCreditQuantExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
     */
    protected String orderByClause;


    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
     */
    public ScCreditQuantExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
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
     * This method corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
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

        public Criteria andScCreditQuantIdIsNull() {
            addCriterion("sc_credit_quant_id is null");
            return (Criteria) this;
        }

        public Criteria andScCreditQuantIdIsNotNull() {
            addCriterion("sc_credit_quant_id is not null");
            return (Criteria) this;
        }

        public Criteria andScCreditQuantIdEqualTo(String value) {
            addCriterion("sc_credit_quant_id =", value, "scCreditQuantId");
            return (Criteria) this;
        }

        public Criteria andScCreditQuantIdNotEqualTo(String value) {
            addCriterion("sc_credit_quant_id <>", value, "scCreditQuantId");
            return (Criteria) this;
        }

        public Criteria andScCreditQuantIdGreaterThan(String value) {
            addCriterion("sc_credit_quant_id >", value, "scCreditQuantId");
            return (Criteria) this;
        }

        public Criteria andScCreditQuantIdGreaterThanOrEqualTo(String value) {
            addCriterion("sc_credit_quant_id >=", value, "scCreditQuantId");
            return (Criteria) this;
        }

        public Criteria andScCreditQuantIdLessThan(String value) {
            addCriterion("sc_credit_quant_id <", value, "scCreditQuantId");
            return (Criteria) this;
        }

        public Criteria andScCreditQuantIdLessThanOrEqualTo(String value) {
            addCriterion("sc_credit_quant_id <=", value, "scCreditQuantId");
            return (Criteria) this;
        }

        public Criteria andScCreditQuantIdLike(String value) {
            addCriterion("sc_credit_quant_id like", value, "scCreditQuantId");
            return (Criteria) this;
        }

        public Criteria andScCreditQuantIdNotLike(String value) {
            addCriterion("sc_credit_quant_id not like", value, "scCreditQuantId");
            return (Criteria) this;
        }

        public Criteria andScCreditQuantIdIn(List<String> values) {
            addCriterion("sc_credit_quant_id in", values, "scCreditQuantId");
            return (Criteria) this;
        }

        public Criteria andScCreditQuantIdNotIn(List<String> values) {
            addCriterion("sc_credit_quant_id not in", values, "scCreditQuantId");
            return (Criteria) this;
        }

        public Criteria andScCreditQuantIdBetween(String value1, String value2) {
            addCriterion("sc_credit_quant_id between", value1, value2, "scCreditQuantId");
            return (Criteria) this;
        }

        public Criteria andScCreditQuantIdNotBetween(String value1, String value2) {
            addCriterion("sc_credit_quant_id not between", value1, value2, "scCreditQuantId");
            return (Criteria) this;
        }

        public Criteria andOrginidIsNull() {
            addCriterion("orginid is null");
            return (Criteria) this;
        }

        public Criteria andOrginidIsNotNull() {
            addCriterion("orginid is not null");
            return (Criteria) this;
        }

        public Criteria andOrginidEqualTo(String value) {
            addCriterion("orginid =", value, "orginid");
            return (Criteria) this;
        }

        public Criteria andOrginidNotEqualTo(String value) {
            addCriterion("orginid <>", value, "orginid");
            return (Criteria) this;
        }

        public Criteria andOrginidGreaterThan(String value) {
            addCriterion("orginid >", value, "orginid");
            return (Criteria) this;
        }

        public Criteria andOrginidGreaterThanOrEqualTo(String value) {
            addCriterion("orginid >=", value, "orginid");
            return (Criteria) this;
        }

        public Criteria andOrginidLessThan(String value) {
            addCriterion("orginid <", value, "orginid");
            return (Criteria) this;
        }

        public Criteria andOrginidLessThanOrEqualTo(String value) {
            addCriterion("orginid <=", value, "orginid");
            return (Criteria) this;
        }

        public Criteria andOrginidLike(String value) {
            addCriterion("orginid like", value, "orginid");
            return (Criteria) this;
        }

        public Criteria andOrginidNotLike(String value) {
            addCriterion("orginid not like", value, "orginid");
            return (Criteria) this;
        }

        public Criteria andOrginidIn(List<String> values) {
            addCriterion("orginid in", values, "orginid");
            return (Criteria) this;
        }

        public Criteria andOrginidNotIn(List<String> values) {
            addCriterion("orginid not in", values, "orginid");
            return (Criteria) this;
        }

        public Criteria andOrginidBetween(String value1, String value2) {
            addCriterion("orginid between", value1, value2, "orginid");
            return (Criteria) this;
        }

        public Criteria andOrginidNotBetween(String value1, String value2) {
            addCriterion("orginid not between", value1, value2, "orginid");
            return (Criteria) this;
        }

        public Criteria andOrgnameIsNull() {
            addCriterion("orgname is null");
            return (Criteria) this;
        }

        public Criteria andOrgnameIsNotNull() {
            addCriterion("orgname is not null");
            return (Criteria) this;
        }

        public Criteria andOrgnameEqualTo(String value) {
            addCriterion("orgname =", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotEqualTo(String value) {
            addCriterion("orgname <>", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameGreaterThan(String value) {
            addCriterion("orgname >", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameGreaterThanOrEqualTo(String value) {
            addCriterion("orgname >=", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLessThan(String value) {
            addCriterion("orgname <", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLessThanOrEqualTo(String value) {
            addCriterion("orgname <=", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLike(String value) {
            addCriterion("orgname like", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotLike(String value) {
            addCriterion("orgname not like", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameIn(List<String> values) {
            addCriterion("orgname in", values, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotIn(List<String> values) {
            addCriterion("orgname not in", values, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameBetween(String value1, String value2) {
            addCriterion("orgname between", value1, value2, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotBetween(String value1, String value2) {
            addCriterion("orgname not between", value1, value2, "orgname");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("parentid is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("parentid is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(String value) {
            addCriterion("parentid =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(String value) {
            addCriterion("parentid <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(String value) {
            addCriterion("parentid >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(String value) {
            addCriterion("parentid >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(String value) {
            addCriterion("parentid <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(String value) {
            addCriterion("parentid <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLike(String value) {
            addCriterion("parentid like", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotLike(String value) {
            addCriterion("parentid not like", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<String> values) {
            addCriterion("parentid in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<String> values) {
            addCriterion("parentid not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(String value1, String value2) {
            addCriterion("parentid between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(String value1, String value2) {
            addCriterion("parentid not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andCreditscoreIsNull() {
            addCriterion("creditscore is null");
            return (Criteria) this;
        }

        public Criteria andCreditscoreIsNotNull() {
            addCriterion("creditscore is not null");
            return (Criteria) this;
        }

        public Criteria andCreditscoreEqualTo(BigDecimal value) {
            addCriterion("creditscore =", value, "creditscore");
            return (Criteria) this;
        }

        public Criteria andCreditscoreNotEqualTo(BigDecimal value) {
            addCriterion("creditscore <>", value, "creditscore");
            return (Criteria) this;
        }

        public Criteria andCreditscoreGreaterThan(BigDecimal value) {
            addCriterion("creditscore >", value, "creditscore");
            return (Criteria) this;
        }

        public Criteria andCreditscoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("creditscore >=", value, "creditscore");
            return (Criteria) this;
        }

        public Criteria andCreditscoreLessThan(BigDecimal value) {
            addCriterion("creditscore <", value, "creditscore");
            return (Criteria) this;
        }

        public Criteria andCreditscoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("creditscore <=", value, "creditscore");
            return (Criteria) this;
        }

        public Criteria andCreditscoreIn(List<BigDecimal> values) {
            addCriterion("creditscore in", values, "creditscore");
            return (Criteria) this;
        }

        public Criteria andCreditscoreNotIn(List<BigDecimal> values) {
            addCriterion("creditscore not in", values, "creditscore");
            return (Criteria) this;
        }

        public Criteria andCreditscoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("creditscore between", value1, value2, "creditscore");
            return (Criteria) this;
        }

        public Criteria andCreditscoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("creditscore not between", value1, value2, "creditscore");
            return (Criteria) this;
        }

        public Criteria andCreditlevelIsNull() {
            addCriterion("creditlevel is null");
            return (Criteria) this;
        }

        public Criteria andCreditlevelIsNotNull() {
            addCriterion("creditlevel is not null");
            return (Criteria) this;
        }

        public Criteria andCreditlevelEqualTo(String value) {
            addCriterion("creditlevel =", value, "creditlevel");
            return (Criteria) this;
        }

        public Criteria andCreditlevelNotEqualTo(String value) {
            addCriterion("creditlevel <>", value, "creditlevel");
            return (Criteria) this;
        }

        public Criteria andCreditlevelGreaterThan(String value) {
            addCriterion("creditlevel >", value, "creditlevel");
            return (Criteria) this;
        }

        public Criteria andCreditlevelGreaterThanOrEqualTo(String value) {
            addCriterion("creditlevel >=", value, "creditlevel");
            return (Criteria) this;
        }

        public Criteria andCreditlevelLessThan(String value) {
            addCriterion("creditlevel <", value, "creditlevel");
            return (Criteria) this;
        }

        public Criteria andCreditlevelLessThanOrEqualTo(String value) {
            addCriterion("creditlevel <=", value, "creditlevel");
            return (Criteria) this;
        }

        public Criteria andCreditlevelLike(String value) {
            addCriterion("creditlevel like", value, "creditlevel");
            return (Criteria) this;
        }

        public Criteria andCreditlevelNotLike(String value) {
            addCriterion("creditlevel not like", value, "creditlevel");
            return (Criteria) this;
        }

        public Criteria andCreditlevelIn(List<String> values) {
            addCriterion("creditlevel in", values, "creditlevel");
            return (Criteria) this;
        }

        public Criteria andCreditlevelNotIn(List<String> values) {
            addCriterion("creditlevel not in", values, "creditlevel");
            return (Criteria) this;
        }

        public Criteria andCreditlevelBetween(String value1, String value2) {
            addCriterion("creditlevel between", value1, value2, "creditlevel");
            return (Criteria) this;
        }

        public Criteria andCreditlevelNotBetween(String value1, String value2) {
            addCriterion("creditlevel not between", value1, value2, "creditlevel");
            return (Criteria) this;
        }

        public Criteria andAssesstimeIsNull() {
            addCriterion("assesstime is null");
            return (Criteria) this;
        }

        public Criteria andAssesstimeIsNotNull() {
            addCriterion("assesstime is not null");
            return (Criteria) this;
        }

        public Criteria andAssesstimeEqualTo(Date value) {
            addCriterion("assesstime =", value, "assesstime");
            return (Criteria) this;
        }

        public Criteria andAssesstimeNotEqualTo(Date value) {
            addCriterion("assesstime <>", value, "assesstime");
            return (Criteria) this;
        }

        public Criteria andAssesstimeGreaterThan(Date value) {
            addCriterion("assesstime >", value, "assesstime");
            return (Criteria) this;
        }

        public Criteria andAssesstimeGreaterThanOrEqualTo(Date value) {
            addCriterion("assesstime >=", value, "assesstime");
            return (Criteria) this;
        }

        public Criteria andAssesstimeLessThan(Date value) {
            addCriterion("assesstime <", value, "assesstime");
            return (Criteria) this;
        }

        public Criteria andAssesstimeLessThanOrEqualTo(Date value) {
            addCriterion("assesstime <=", value, "assesstime");
            return (Criteria) this;
        }

        public Criteria andAssesstimeIn(List<Date> values) {
            addCriterion("assesstime in", values, "assesstime");
            return (Criteria) this;
        }

        public Criteria andAssesstimeNotIn(List<Date> values) {
            addCriterion("assesstime not in", values, "assesstime");
            return (Criteria) this;
        }

        public Criteria andAssesstimeBetween(Date value1, Date value2) {
            addCriterion("assesstime between", value1, value2, "assesstime");
            return (Criteria) this;
        }

        public Criteria andAssesstimeNotBetween(Date value1, Date value2) {
            addCriterion("assesstime not between", value1, value2, "assesstime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sc_credit_quant
     *
     * @mbg.generated do_not_delete_during_merge Mon Nov 19 10:32:56 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sc_credit_quant
     *
     * @mbg.generated Mon Nov 19 10:32:56 CST 2018
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