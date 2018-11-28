package com.casic.oarp.datavisual.service;

import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.RiskEventTableModel;
import com.casic.oarp.datavisual.model.zxfk.RiskWanHuiModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.po.RiskEventsTrack;
import com.casic.oarp.datavisual.po.RiskEventsTrackWithBLOBs;

import java.util.Date;
import java.util.List;


public interface RiskEventsTrackService {
    List<LineChartModel> getSumAmountLineChart();
    List<RiskWanHuiModel> getSumAmountBarChart();

    List<LineChartModel> getSumAmountBarChart(Date startDate, Date endDate);

    List<SumModel> getSumCountByEventType();

    List<SumModel> getSumCountByEventProgress();

    List<SumModel> getSumCountByLawsuit();

    List<SumModel> getSumAmountByEndMoney(String sort);
    /*风险事件相关责任部门*/
    List<SumModel> getSumAmountByEndMoney();
    List<SumModel> getDiffAmountByMoney(String sort);

    List<RiskEventTableModel> rankOfAmount();

    List<RiskEventTableModel> rankOfCreateTime();

    List<SumModel> getSumCountAndAmountForIndex();

    List<SumModel> getCharCloud();
    public List<RiskEventsTrack>  findByMoney();
    List<RiskEventsTrack> findByName(String name );

    /**
     * 根据时间查询风险事件数量
     */
    List<LineChartModel> getSumCountAndAmountBarChart(Date startDate, Date endDate);
}
