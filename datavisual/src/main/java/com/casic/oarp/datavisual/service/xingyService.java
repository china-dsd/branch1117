package com.casic.oarp.datavisual.service;

import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.po.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2018/11/19.
 */
/*
信用所有接口
 */
public interface xingyService {
    //集团公司信用排名
    List<ScCreditQuantVo> getTable();
    //二级集团信用展示
    QuantVo geterTable();
    //信用风险事件预警
    List<warnsVo>  getWarns();
    //信用等级
    List<SumModel> getpie();
    //信用风险等级对应评分
    List<SumModel> getpieWarn();
    //集团负面信息类型
    List<SumModel>piewarn();
}
