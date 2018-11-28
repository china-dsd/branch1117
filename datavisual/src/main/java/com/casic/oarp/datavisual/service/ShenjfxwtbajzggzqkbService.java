package com.casic.oarp.datavisual.service;

import com.casic.oarp.datavisual.model.zxfk.AuditProjectTableModel;
import com.casic.oarp.datavisual.model.zxfk.AuditVO;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;

import java.util.Date;
import java.util.List;

/**
 * 审计发现问题备案及整改情况统计表
 * */
public interface ShenjfxwtbajzggzqkbService {

    List<AuditProjectTableModel> rankOfImportance();

    List<AuditVO> amountOfAuditIssues();

    List<SumModel> getSumCountByAuditProjectType();

    List<SumModel> getSumAmountByIssueImportance();

    List<SumModel> getSumCountByIssueType();

    List<SumModel> getSumCountByAudit(String sort);

    List<SumModel> getSumCountBuAudited(String sort);

    List<LineChartModel> getSumCountAndAmountBarChart(Date startDate, Date endDate);

    List<LineChartModel> getSumCountAndAmountLineChart();

    List<LineChartModel> getSumCountByStateForIndex();

    // 整改记录
    List<SumModel> getSumCountByZhenggjlForIndex(Date startDate, Date endDate);
    //重大审计事件
    public List<SumModel> getSignificantShenjiEvent();

    List<LineChartModel> getSumNameCountByStateForIndex();
}
