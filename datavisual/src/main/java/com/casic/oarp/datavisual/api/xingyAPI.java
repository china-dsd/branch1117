package com.casic.oarp.datavisual.api;

import com.casic.oarp.datavisual.model.RestResult;
import com.casic.oarp.datavisual.model.zxfk.RiskEventTableModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.po.QuantVo;
import com.casic.oarp.datavisual.po.ScCreditQuantVo;
import com.casic.oarp.datavisual.po.warnsVo;
import com.casic.oarp.datavisual.service.xingyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2018/11/19.
 */

@CrossOrigin
@Api(value = "信用", tags = "信用")
@RestController
@RequestMapping("/xiny")
public class xingyAPI {
    //(一)	集团公司信用排名 测试成功(正序排列)
    @Autowired
    private xingyService xyService;
    @ApiOperation(value = "表格图")
    @RequestMapping(value = "/tableChart", method = {RequestMethod.GET})
    public RestResult<List<ScCreditQuantVo>> getTableASC(){
        List<ScCreditQuantVo> list=xyService.getTable();
        RestResult<List<ScCreditQuantVo>> result = new RestResult<>();
        result.setData(list);
        return result;
    }

    //二级单位信用评分 测试完成
    @ApiOperation(value = "表格图")
    @RequestMapping(value = "/line", method = {RequestMethod.GET})
    public RestResult<QuantVo> getline(){
        QuantVo vo=xyService.geterTable();
        RestResult<QuantVo> result = new RestResult<>();
        result.setData(vo);
        return result;
    }
    //信用风险事件预警(返回了200条数据)测试完成
    @ApiOperation(value = "字符云")
    @RequestMapping(value = "/cloudChart", method = {RequestMethod.GET})
    public RestResult<List<warnsVo>> getChart(){
        List<warnsVo> list=xyService.getWarns();
        RestResult<List<warnsVo>> result = new RestResult<>();
        result.setData(list);
        return result;
    }
    //	信用等级
    @ApiOperation(value = "信用等级饼状图")
    @RequestMapping(value = "/pieChart", method = {RequestMethod.GET})
    public RestResult<List> getpie(){
        List<SumModel> map=xyService.getpie();
        RestResult<List> result = new RestResult<>();
        result.setData(map);
        return result;
    }
    //信用风险等级对应评分
    @ApiOperation(value = "信用风险饼状图")
    @RequestMapping(value = "/pieChartWarn", method = {RequestMethod.GET})
    public RestResult<List> getpieWarn(){
        List<SumModel> map=xyService.getpieWarn();
        RestResult<List> result = new RestResult<>();
        result.setData(map);
        return result;
    }
    //负面信息分布
    @ApiOperation(value="负面信息分布")
    @RequestMapping(value="/pieWarn",method={RequestMethod.GET})
    public RestResult<List> pieWarn(){
        List<SumModel> map=xyService.piewarn();
        RestResult<List> result=new RestResult<>();
        result.setData(map);
        return result;
    }
}
