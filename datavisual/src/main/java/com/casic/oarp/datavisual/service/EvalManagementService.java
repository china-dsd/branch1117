package com.casic.oarp.datavisual.service;

import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.po.EvalManagementVO;

import java.util.List;

/**
 * Created by 董胜得 on 2018/9/27.
 */
public interface EvalManagementService {
    List<SumModel> getMaturity();

    //评分前十名数据
    List<EvalManagementVO> selectTopTen();

    //评分前十名数据
    List<EvalManagementVO> selectAllCom();

    //成熟度等级占比
    List<SumModel> selectMaturityCategory(String sort);
    //缺陷类型分布
    List<SumModel> selectDistributeOfDefect();
    //重要缺陷分布
    List<SumModel> selectDistributeImport();
    //重大或者重要缺陷
    List<SumModel> selectImportDefect();
}
