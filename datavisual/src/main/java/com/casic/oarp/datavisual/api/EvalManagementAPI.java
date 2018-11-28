package com.casic.oarp.datavisual.api;

import com.casic.oarp.datavisual.exception.ZXFKException;
import com.casic.oarp.datavisual.model.RestResult;
import com.casic.oarp.datavisual.service.EvalManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eval")
@CrossOrigin
@Api(value = "评分系统", tags = "评分系统")
public class EvalManagementAPI {

    @Autowired
    private EvalManagementService evalManagementService;

    /**
     * 表格图
     *
     * @param
     * @return
     */
    @ApiOperation(value = "表格图")
    @RequestMapping(value = "/tableChart", method = {RequestMethod.GET})
    public RestResult tableChart(
            @ApiParam(value = "1表示前10名评分信息")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult result = new RestResult<>();
        switch (type) {
            case 1:
                // 表示未结案数量趋势图
                result.setData(evalManagementService.selectTopTen());
                break;
        }
        return result;
    }

    /**
     * 饼状图
     *
     * @param type 1表示成熟度等级占比
     * @return
     */
    @ApiOperation(value = "饼状图")
    @RequestMapping(value = "/pieChart", method = {RequestMethod.GET})
    public RestResult pieChart(
            @ApiParam(value = "1表示成熟度等级占比")
            @RequestParam(defaultValue = "1") Integer type,
            @ApiParam(value = "asc表示升序，desc表示降序")
            @RequestParam(defaultValue = "asc") String sort,
            String token) {
        RestResult result = new RestResult<>();
        switch (type) {
            case 1:
                result.setData(evalManagementService.selectMaturityCategory(sort));
                break;
            case 2:
                result.setData(evalManagementService.selectDistributeOfDefect());
                break;
            case 3:
                result.setData(evalManagementService.selectDistributeImport());
                break;
        }
        return result;
    }

    /**
     * 柱状图
     *
     * @param type
     * @return
     */
    @ApiOperation(value = "柱状图")
    @RequestMapping(value = "/barChart", method = {RequestMethod.GET})
    public RestResult barChart(
            @ApiParam(value = "1表示所有公司成熟度评分")
            @RequestParam(defaultValue = "1") Integer type,
            @ApiParam(value = "格式必须是yyyy-MM-dd")
            @RequestParam(defaultValue = "", required = false) String startDate,
            @ApiParam(value = "格式必须是yyyy-MM-dd")
            @RequestParam(defaultValue = "", required = false) String endDate,
            String token) throws ZXFKException {
        RestResult result = new RestResult<>();
        switch (type) {
            // 所有公司成熟度评分
            case 1:
                result.setData(evalManagementService.selectAllCom());
                break;
        }
        return result;
    }

    /**
     * 折线图
     *
     * @param type
     * @return
     */
    @ApiOperation(value = "折线图")
    @RequestMapping(value = "/lineChart", method = {RequestMethod.GET})
    public RestResult lineChart(
            @ApiParam(value = "1表示法律案件金额统计图,2法律案件前12个月企业涉案单位前三名未结案的趋势变化,3二级单位涉案单位前10名涉案数量和金额")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult result = new RestResult<>();
        switch (type) {
            // 法律案件金额统计图(前10月数据，不含本月)
            case 1:
                result.setData(evalManagementService.selectTopTen());
                break;
        }
        return result;
    }

    /**
     * 重大或者重要缺陷
     *
     * @param type
     * @return
     */
    @ApiOperation(value = "字符云")
    @RequestMapping(value = "/wordCloud", method = {RequestMethod.GET})
    public RestResult wordCloud(
            @ApiParam(value = "1表示重大或者重要缺陷")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult result = new RestResult<>();
        switch (type) {
            // 重大或者重要缺陷
            case 1:
                result.setData(evalManagementService.selectImportDefect());
                break;
        }
        return result;
    }
}
