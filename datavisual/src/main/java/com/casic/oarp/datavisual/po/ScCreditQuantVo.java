package com.casic.oarp.datavisual.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ASUS on 2018/11/19.
 */
public class ScCreditQuantVo {
 // 二级单位名称、单位名称、信用评分、
// 信用等级（A、B、C等级以及ABC下各个子等级）、评价日期，
// 以及各单位、、、、
// 五维度数据；

 private String orgname;//公司名称

 private BigDecimal creditscore;//信用评分


 private String creditlevel;//信用等级


 private String assesstime;//评价时间


 private BigDecimal promisescore;//践约评估


 private BigDecimal managescore;//经营能力


 private BigDecimal specialityscore;//企业特质


 private BigDecimal chainscore;//供应链健壮

 private BigDecimal taxscore;//涉税信用


 public String getOrgname() {
  return orgname;
 }

 public void setOrgname(String orgname) {
  this.orgname = orgname;
 }

 public BigDecimal getCreditscore() {
  return creditscore;
 }

 public void setCreditscore(BigDecimal creditscore) {
  this.creditscore = creditscore;
 }

 public String getCreditlevel() {
  return creditlevel;
 }

 public void setCreditlevel(String creditlevel) {
  this.creditlevel = creditlevel;
 }

 public String getAssesstime() {
  return assesstime;
 }

 public void setAssesstime(String assesstime) {
  this.assesstime = assesstime;
 }

 public BigDecimal getPromisescore() {
  return promisescore;
 }

 public void setPromisescore(BigDecimal promisescore) {
  this.promisescore = promisescore;
 }

 public BigDecimal getManagescore() {
  return managescore;
 }

 public void setManagescore(BigDecimal managescore) {
  this.managescore = managescore;
 }

 public BigDecimal getSpecialityscore() {
  return specialityscore;
 }

 public void setSpecialityscore(BigDecimal specialityscore) {
  this.specialityscore = specialityscore;
 }

 public BigDecimal getChainscore() {
  return chainscore;
 }

 public void setChainscore(BigDecimal chainscore) {
  this.chainscore = chainscore;
 }

 public BigDecimal getTaxscore() {
  return taxscore;
 }

 public void setTaxscore(BigDecimal taxscore) {
  this.taxscore = taxscore;
 }
}
