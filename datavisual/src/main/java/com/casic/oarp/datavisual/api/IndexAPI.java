package com.casic.oarp.datavisual.api;

import com.casic.oarp.datavisual.common.ZXFKUtils;
import com.casic.oarp.datavisual.model.RestResult;
import com.casic.oarp.datavisual.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/index")
@CrossOrigin
@Api(value = "首页", tags = "首页")
public class IndexAPI {

    // 风险事件
    @Autowired
    private RiskEventsTrackService riskEventsTrackService;
    // 审计问题3+1
    @Autowired
    private ShenjfxwtbajzggzqkbService shenjfxwtbajzggzqkbService;
    // 风险评估
    @Autowired
    private ZhongdjcfxpgtjService zhongdjcfxpgtjService;
    // 法务系统
    @Autowired
    private SsajqkbgbService ssajqkbgbService;
    //成熟度等级
    @Autowired
    private EvalManagementService evalManagementService;
    //征信公司
    @Autowired
    private HtywOrgizationService htywOrgizationService;

    /**
     * 返回公司个数
     */
    @ApiOperation(value = "字数显示")
    @RequestMapping(value = "/totalCount", method = {RequestMethod.GET})
    public RestResult totalCount(
            @ApiParam(value = "1表示公司个数")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult result = new RestResult();
        switch (type) {
            case 1:
                result.setData(htywOrgizationService.selectCount());
                break;
        }
        return result;
    }
    /**
     * 柱状图
     *
     * @param type 1表示风险事件数量及金额，2表示法律案件的数量及金额，3审计问题状态记录，4风险事件挽回金额
     * @return
     */
    @ApiOperation(value = "柱状图")
    @RequestMapping(value = "/barChart", method = {RequestMethod.GET})
    public RestResult barChart(
            @ApiParam(value = "1表示风险事件数量及金额，2表示法律案件，3审计问题状态记录，4风险事件挽回金额，6审计问题整改情况")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult result = new RestResult();
        switch (type) {
            case 1:
                result.setData(riskEventsTrackService.getSumCountAndAmountForIndex());
                break;
            case 2:
                result.setData(ssajqkbgbService.getSumCountAndAmountForIndex());
                break;
            case 3:
                result.setData(shenjfxwtbajzggzqkbService.getSumCountByStateForIndex());
                break;
            case 4:
                result.setData(riskEventsTrackService.getSumAmountLineChart());
                break;
            case 5:
                result.setData(riskEventsTrackService.getSumAmountBarChart());
                break;
            case 6:
                result.setData(shenjfxwtbajzggzqkbService.getSumNameCountByStateForIndex());
                break;
        }
        return result;
    }

    /**
     * 饼状图
     *
     * @param
     * @return
     */
    @ApiOperation(value = "饼状图")
    @RequestMapping(value = "/pieChart", method = {RequestMethod.GET})
    public RestResult pieChart(
            @ApiParam(value = "1表示风险评估, 2表示审计问题整改记录")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult result = new RestResult();
        Date nowDate = ZXFKUtils.nowDate();
        Date startDate = null;
        switch (type) {
            case 1:
                startDate = ZXFKUtils.getYearStart();
                result.setData(zhongdjcfxpgtjService.getSumCountByRiskLevel(startDate, nowDate));
                break;
            case 2:
                startDate = ZXFKUtils.getYearStart();
                result.setData(shenjfxwtbajzggzqkbService.getSumCountByZhenggjlForIndex(startDate, nowDate));
                break;
            case 3:
                result.setData(evalManagementService.getMaturity());
        }
        return result;
    }

    /**
     * 字符云
     * 重大风险事件
     * @param
     * @return
     */
    @ApiOperation(value = "字符云")
    @RequestMapping(value = "/wordCloud", method = {RequestMethod.GET})
    public RestResult wordCloud(
            @ApiParam(value = "1重大风险事件")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult result = new RestResult();
        switch (type) {
            case 1:
                result.setData(zhongdjcfxpgtjService.getRiskAmountOver500w());
                break;
        }
        return result;
    }

    @ApiOperation(value = "重大经营风险事件事件总体情况")
    @RequestMapping(value = "/bigevent", method = {RequestMethod.GET})
    public RestResult bigevent(){
        RestResult result = new RestResult();
       result.setData(riskEventsTrackService.getSumCountAndAmountForIndex());
       return result;
    }
}
