package com.casic.oarp.datavisual.api;

import com.casic.oarp.datavisual.Utils.EmptyUtils;
import com.casic.oarp.datavisual.exception.ZXFKException;
import com.casic.oarp.datavisual.model.RestResult;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.RiskEventTableModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.po.RiskEventsTrack;
import com.casic.oarp.datavisual.po.Zhongdjcfxpgtj;
import com.casic.oarp.datavisual.service.RiskEventsTrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.math.BigDecimal;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/fengxsj")
@Api(value="风险事件", tags = "风险事件")
public class RiskEventsTrackAPI {

    @Autowired
    private RiskEventsTrackService riskEventsTrackService;

    /**
     * 表格图
     * @param type 1表示重要度排名 2表示审计问题金额
     * @return
     */
    @ApiOperation(value = "表格图")
    @RequestMapping(value = "/tableChart", method = {RequestMethod.GET})
    public RestResult<List<RiskEventTableModel>> tableChart(
            @ApiParam(value = "1表示风险事件金额排名 2表示创建时间排名")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult<List<RiskEventTableModel>> result = new RestResult<>();
        switch (type) {
            // 风险事件金额排名
            case 1:
                result.setData(riskEventsTrackService.rankOfAmount());
                break;
            // 创建时间排名
            case 2:
                result.setData(riskEventsTrackService.rankOfCreateTime());
                break;
        }

        return result;
    }


    /**
     * 审计项目信息饼状图
     * @param type 1表示风险事件类型, 2表示风险事件进展，3表示是否进入诉讼程序占比，4各二级单位统计期末金额占比，5各二级单位避免金额损失占比
     * @return
     */
    @ApiOperation(value = "饼状图")
    @RequestMapping(value = "/pieChart", method = {RequestMethod.GET})
    public RestResult<List<SumModel>> auditProjectInfoPie(
            @ApiParam(value = "1表示风险事件类型, 2表示风险事件进展，3事件诉讼情况占比，4各二级单位统计期末金额占比，5各二级单位避免金额损失占比")
            @RequestParam(defaultValue = "1") Integer type,
            @ApiParam(value = "asc表示升序，desc表示降序")
            @RequestParam(defaultValue = "asc") String sort,
            String token) {
        RestResult<List<SumModel>> result = new RestResult<>();
        switch (type) {
            case 1 :
                result.setData(riskEventsTrackService.getSumCountByEventType());
                break;
            case 2:
                result.setData(riskEventsTrackService.getSumCountByEventProgress());
                break;
            case 3:
                result.setData(riskEventsTrackService.getSumCountByLawsuit());
                break;
            case 4:
                result.setData(riskEventsTrackService.getSumAmountByEndMoney(sort));
               //result.setData(riskEventsTrackService.getSumAmountByEndMoney());
                break;
            case 5:
                result.setData(riskEventsTrackService.getDiffAmountByMoney(sort));
                break;
        }
        return result;
    }

    /**
     * 审计项目信息柱状图
     * @param type
     * @return
     */
    @ApiOperation(value = "柱状图")
    @RequestMapping(value = "/barChart", method = {RequestMethod.GET})
    public RestResult<List<LineChartModel>> barChart (
            @ApiParam(value = "1表示各二级单位风险事件金额，2表示风险事件期末金额和风险事件数量")
            @RequestParam(defaultValue = "1") Integer type,
            @RequestParam(defaultValue = "", required = false) String startDate,
            @RequestParam(defaultValue = "", required = false) String endDate,
            String token) throws ZXFKException {
        RestResult<List<LineChartModel>> result = new RestResult<>();
        switch (type) {
            // 各二级单位风险事件金额
            case 1 :
                // 解析时间对象
                Date[] dates = APIUtils.parseDate(startDate, endDate);
                result.setData(riskEventsTrackService.getSumAmountBarChart(dates[0], dates[1]));
                break;
            case 2 :
                // 解析时间对象
                Date[] datesn = APIUtils.parseDate(startDate, endDate);
                result.setData(riskEventsTrackService.getSumCountAndAmountBarChart(datesn[0], datesn[1]));
                break;
        }
        return result;
    }

    /**
     * 审计项目信息折线图
     *
     * @param type
     * @return
     */
    @ApiOperation(value = "折线图")
    @RequestMapping(value = "/lineChart", method = {RequestMethod.GET})
    public RestResult<List<LineChartModel>> lineChart(
            @ApiParam(value = "1表示风险事件金额对比图")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult<List<LineChartModel>> result = new RestResult<>();
        switch (type) {
            // 风险事件金额对比图(前10月数据，不含本月)
            case 1:
                result.setData(riskEventsTrackService.getSumAmountLineChart());
                break;
        }
        return result;
    }

    @ApiOperation(value = "字符云")
    @RequestMapping(value = "/wordCloud",method = {RequestMethod.GET})
    public RestResult wordCloudChart(
            @ApiParam(value="1高风险评估事项")
                    @RequestParam(defaultValue = "1") Integer type,
            String token){
        RestResult result=new RestResult();
        switch (type){
            // 高风险评估事项
            case 1:
                result.setData(riskEventsTrackService.getCharCloud());
        }
    return result;
    }


    @ApiOperation(value = "风险事件数量及金额查询")
    @RequestMapping(value = "/tableselect",method = {RequestMethod.GET},produces = "application/json")
    public RestResult fxeval(@ApiParam(value="风险事件数量及金额查询")@RequestParam(required=false)String name){
        Boolean istrue;
        istrue= EmptyUtils.isEmpty(name);
        if (istrue){
            name="";
        }

        List<String> list=new ArrayList<>();
        List<RiskEventsTrack> result=new ArrayList();
        result=riskEventsTrackService.findByName(name);
        RestResult Result=new RestResult();
        int event=0;
        BigDecimal money=(new BigDecimal(0));
        List list1=new ArrayList();
        for (RiskEventsTrack v:result) {
            if (EmptyUtils.isNotEmpty(v.getNameThree()))
                list.add("("+v.getNameTwo()+")"+v.getNameThree());
            else
                list.add(v.getNameTwo());
            //风险事件数量
            event++;
            //风险事件金额
            if (EmptyUtils.isNotEmpty(v.getEndMoney()))
            money=v.getEndMoney().add(money);
        }
        list1.add(list);
        list1.add(event);
        list1.add(money);
        Result.setData(list1);
        if (EmptyUtils.isEmpty(result)){
            return null;
        }else{
            return Result;
        }

    }
}
