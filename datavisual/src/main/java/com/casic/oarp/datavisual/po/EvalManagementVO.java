package com.casic.oarp.datavisual.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class EvalManagementVO implements Serializable {
    private String score;

    private String comname;

    private String erjidanwei;

    private String category;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public String getErjidanwei() {
        return erjidanwei;
    }

    public void setErjidanwei(String erjidanwei) {
        this.erjidanwei = erjidanwei;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}