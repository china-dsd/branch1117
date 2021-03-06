package com.casic.oarp.datavisual.model.zxfk;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 审计项目表模型，
 * 用于重要度排名，审计问题金额
 */
public class AuditVO implements Serializable {

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column y_shenjfxwtbajzggzqkb.susejdw
     * 所属二级单位
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    private String susejdw;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column y_shenjfxwtbajzggzqkb.beisjdw
     * （责任单位）被审计单位
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    private String beisjdw;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column y_shenjfxwtbajzggzqkb.duczgbm
     * 计划完成时间
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    private Date jihwcsj;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column y_shenjfxwtbajzggzqkb.shenjxmlx
     * 审计项目类型
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    private String shenjxmlx;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column y_shenjfxwtbajzggzqkb.danwjc
     * 整改责任人
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    private String zhenggzrr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column y_shenjfxwtbajzggzqkb.danwjc
     * 整改责任部门
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    private String zhenggzrbm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column y_shenjfxwtbajzggzqkb.wentje
     * 问题金额
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    private BigDecimal wentje;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column y_shenjfxwtbajzggzqkb.yuantzgjz
     * 源头整改进展情况
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    private String yuantzgjz;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column y_shenjfxwtbajzggzqkb.danwjc
     * 问题描述
     * @mbg.generated Wed Jun 27 12:41:33 CST 2018
     */
    private String wentms;

    public AuditVO() {
    }

    public AuditVO(String susejdw, String beisjdw, Date jihwcsj, String shenjxmlx, String zhenggzrr, String zhenggzrbm, BigDecimal wentje, String yuantzgjz, String wentms) {
        this.susejdw = susejdw;
        this.beisjdw = beisjdw;
        this.jihwcsj = jihwcsj;
        this.shenjxmlx = shenjxmlx;
        this.zhenggzrr = zhenggzrr;
        this.zhenggzrbm = zhenggzrbm;
        this.wentje = wentje;
        this.yuantzgjz = yuantzgjz;
        this.wentms = wentms;
    }

    public String getSusejdw() {
        return susejdw;
    }

    public void setSusejdw(String susejdw) {
        this.susejdw = susejdw;
    }

    public String getBeisjdw() {
        return beisjdw;
    }

    public void setBeisjdw(String beisjdw) {
        this.beisjdw = beisjdw;
    }

    public Date getJihwcsj() {
        return jihwcsj;
    }

    public void setJihwcsj(Date jihwcsj) {
        this.jihwcsj = jihwcsj;
    }

    public String getShenjxmlx() {
        return shenjxmlx;
    }

    public void setShenjxmlx(String shenjxmlx) {
        this.shenjxmlx = shenjxmlx;
    }

    public String getZhenggzrr() {
        return zhenggzrr;
    }

    public void setZhenggzrr(String zhenggzrr) {
        this.zhenggzrr = zhenggzrr;
    }

    public String getZhenggzrbm() {
        return zhenggzrbm;
    }

    public void setZhenggzrbm(String zhenggzrbm) {
        this.zhenggzrbm = zhenggzrbm;
    }

    public BigDecimal getWentje() {
        return wentje;
    }

    public void setWentje(BigDecimal wentje) {
        this.wentje = wentje;
    }

    public String getYuantzgjz() {
        return yuantzgjz;
    }

    public void setYuantzgjz(String yuantzgjz) {
        this.yuantzgjz = yuantzgjz;
    }

    public String getWentms() {
        return wentms;
    }

    public void setWentms(String wentms) {
        this.wentms = wentms;
    }
}
