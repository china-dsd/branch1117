package com.casic.oarp.datavisual.model.zxfk;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by 董胜得 on 2018/9/28.
 */
public class RiskWanHuiModel implements Serializable {
    //二级单位名称
    private String name;
    //挽回金额
    private BigDecimal saveAmount;
    //期初金额
    private BigDecimal startAmount;
    //挽回比例
    private Double saveRate;

    public RiskWanHuiModel(String name,BigDecimal saveAmount, BigDecimal startAmount, Double saveRate) {
        this.name = name;
        this.saveAmount = saveAmount;
        this.startAmount = startAmount;
        this.saveRate = saveRate;
    }

    public BigDecimal getSaveAmount() {
        return saveAmount;
    }

    public void setSaveAmount(BigDecimal saveAmount) {
        this.saveAmount = saveAmount;
    }

    public BigDecimal getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(BigDecimal startAmount) {
        this.startAmount = startAmount;
    }

    public Double getSaveRate() {
        return saveRate;
    }

    public void setSaveRate(Double saveRate) {
        this.saveRate = saveRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
