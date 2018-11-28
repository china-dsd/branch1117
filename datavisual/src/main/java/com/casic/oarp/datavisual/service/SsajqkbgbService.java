package com.casic.oarp.datavisual.service;

import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SsajqkbgbModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;

import java.util.Date;
import java.util.List;

public interface SsajqkbgbService {

    List<LineChartModel> getSumCountAndAmountBarChart(Date startDate, Date endDate);

    List<SumModel> getSumCountByType1();

    List<SumModel> getSumCountByType2();

    // 法律案件金额统计图
    List<LineChartModel> getSumAmountLineChart();

    // 结案方式
    List<SumModel> getSumCountByFinishType();

    // 诉讼角色
    List<SumModel> getSumCountByRole(String sort, String role);

    // 标的金额排名
    List<LineChartModel> rankOfBidAmount();

    // 两金金额排名
    List<SsajqkbgbModel> rankOfLJJEAmount();

    List<SumModel> getSumCountAndAmountForIndex();

    //新增案件数量和案件存量
    List<LineChartModel> getCaseCount();

    //分组标的金额
    List<SumModel> getGroupAmount();

    //获取诉讼类型
    List<SumModel> getLitigationType();

    //获取标的金额超过5000万的数据
    List<SsajqkbgbModel> getMoreThan5000();

    //法律案件前12个月企业涉案单位前三名未结案的趋势变化
    List<LineChartModel> getTopThreeLineChart();

    //二级单位涉案单位前10名涉案数量和金额
    List<LineChartModel> getTopTenDatas();

    //
    List<LineChartModel> getJieAnCountAndAmount();
}
