package com.casic.oarp.datavisual.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class EvalImperfection implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column eval_imperfection.id
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    private BigDecimal id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column eval_imperfection.catalogid
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    private String catalogid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column eval_imperfection.managementid
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    private String managementid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column eval_imperfection.imper_descr
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    private String imperDescr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column eval_imperfection.evaldescr
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    private String evaldescr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column eval_imperfection.fj
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    private String fj;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table eval_imperfection
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column eval_imperfection.id
     *
     * @return the value of eval_imperfection.id
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column eval_imperfection.id
     *
     * @param id the value for eval_imperfection.id
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column eval_imperfection.catalogid
     *
     * @return the value of eval_imperfection.catalogid
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public String getCatalogid() {
        return catalogid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column eval_imperfection.catalogid
     *
     * @param catalogid the value for eval_imperfection.catalogid
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public void setCatalogid(String catalogid) {
        this.catalogid = catalogid == null ? null : catalogid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column eval_imperfection.managementid
     *
     * @return the value of eval_imperfection.managementid
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public String getManagementid() {
        return managementid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column eval_imperfection.managementid
     *
     * @param managementid the value for eval_imperfection.managementid
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public void setManagementid(String managementid) {
        this.managementid = managementid == null ? null : managementid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column eval_imperfection.imper_descr
     *
     * @return the value of eval_imperfection.imper_descr
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public String getImperDescr() {
        return imperDescr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column eval_imperfection.imper_descr
     *
     * @param imperDescr the value for eval_imperfection.imper_descr
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public void setImperDescr(String imperDescr) {
        this.imperDescr = imperDescr == null ? null : imperDescr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column eval_imperfection.evaldescr
     *
     * @return the value of eval_imperfection.evaldescr
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public String getEvaldescr() {
        return evaldescr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column eval_imperfection.evaldescr
     *
     * @param evaldescr the value for eval_imperfection.evaldescr
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public void setEvaldescr(String evaldescr) {
        this.evaldescr = evaldescr == null ? null : evaldescr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column eval_imperfection.fj
     *
     * @return the value of eval_imperfection.fj
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public String getFj() {
        return fj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column eval_imperfection.fj
     *
     * @param fj the value for eval_imperfection.fj
     *
     * @mbg.generated Thu Oct 25 17:18:02 CST 2018
     */
    public void setFj(String fj) {
        this.fj = fj == null ? null : fj.trim();
    }
}