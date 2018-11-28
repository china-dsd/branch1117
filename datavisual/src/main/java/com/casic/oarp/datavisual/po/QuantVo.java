package com.casic.oarp.datavisual.po;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ASUS on 2018/11/20.
 */
public class QuantVo {
    private List<BigDecimal> score;
    private List<String> name;

    public List<BigDecimal> getScore() {
        return score;
    }

    public void setScore(List<BigDecimal> score) {
        this.score = score;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
