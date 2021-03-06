package com.casic.oarp.datavisual.api;

import com.casic.oarp.datavisual.common.ZXFKConsts;
import com.casic.oarp.datavisual.exception.ZXFKException;
import com.casic.oarp.datavisual.model.RestResult;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SsajqkbgbModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.service.SsajqkbgbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/faw")
@CrossOrigin
@Api(value = "法务系统", tags = "法务系统")
public class SsajqkbgbAPI {

    @Autowired
    private SsajqkbgbService ssajqkbgbService;

    /**
     * 表格图
     *
     * @param
     * @return
     */
    @ApiOperation(value = "表格图")
    @RequestMapping(value = "/tableChart", method = {RequestMethod.GET})
    public RestResult tableChart(
            @ApiParam(value = "1表示未结案数量趋势图, 2表示法律案件涉及两金金额排名，3表示新增案件数量和案件存量,4表示累积办案数及挽回损失金额")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult result = new RestResult<>();
        switch (type) {
            case 1:
                // 表示未结案数量趋势图
                result.setData(ssajqkbgbService.rankOfBidAmount());
                break;
            case 2:
                // 法律案件涉及两金金额排名
                result.setData(ssajqkbgbService.rankOfLJJEAmount());
                break;
            case 3:
                // 新增案件数量和案件存量
                result.setData(ssajqkbgbService.getCaseCount());
                break;
            case 4:
                // 新增案件数量和案件存量
                result.setData(ssajqkbgbService.getJieAnCountAndAmount());
                break;
        }
        return result;
    }

    /**
     * 饼状图
     *
     * @param type 1表示案件类型 2表示案件类别，3表示结案方式，4二级各单位被告次数情况，5二级各单位原告次数情况
     * @return
     */
    @ApiOperation(value = "饼状图")
    @RequestMapping(value = "/pieChart", method = {RequestMethod.GET})
    public RestResult<List<SumModel>> pieChart(
            @ApiParam(value = "1表示案件类型 2表示案件类别，3表示结案方式，4二级各单位被告次数情况，5二级各单位原告次数情况,6标的金额进行分组,7获取诉讼类型,8二级单位涉案单位前10名涉案数量和金额")
            @RequestParam(defaultValue = "1") Integer type,
            @ApiParam(value = "asc表示升序，desc表示降序")
            @RequestParam(defaultValue = "asc") String sort,
            String token) {
        RestResult<List<SumModel>> result = new RestResult<>();
        switch (type) {
            case 1:
                result.setData(ssajqkbgbService.getSumCountByType1());
                break;
            case 2:
                result.setData(ssajqkbgbService.getSumCountByType2());
                break;
            case 3:
                // 结案方式
                result.setData(ssajqkbgbService.getSumCountByFinishType());
                break;
            case 4:
                // 二级各单位诉讼角色，被告
                result.setData(ssajqkbgbService.getSumCountByRole(sort, ZXFKConsts.ROLE_DEFENDANT));
                break;
            case 5:
                // 二级各单位诉讼角色，原告
                result.setData(ssajqkbgbService.getSumCountByRole(sort, ZXFKConsts.ROLE_PLAINTIFF));
                break;
            case 6:
                // 标的金额进行分组
                result.setData(ssajqkbgbService.getGroupAmount());
                break;
            case 7:
                //获取诉讼类型
                result.setData(ssajqkbgbService.getLitigationType());
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
    public RestResult<List<LineChartModel>> barChart(
            @ApiParam(value = "1表示法律案件的数量及涉及两金金额")
            @RequestParam(defaultValue = "1") Integer type,
            @ApiParam(value = "格式必须是yyyy-MM-dd")
            @RequestParam(defaultValue = "", required = false) String startDate,
            @ApiParam(value = "格式必须是yyyy-MM-dd")
            @RequestParam(defaultValue = "", required = false) String endDate,
            String token) throws ZXFKException {
        RestResult<List<LineChartModel>> result = new RestResult<>();
        switch (type) {
            // 风险事项的数量及金额
            case 1:
                Date[] dates = APIUtils.parseDate(startDate, endDate);
                result.setData(ssajqkbgbService.getSumCountAndAmountBarChart(dates[0], dates[1]));
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
    public RestResult<List<LineChartModel>> lineChart(
            @ApiParam(value = "1表示法律案件金额统计图,2法律案件前12个月企业涉案单位前三名未结案的趋势变化,3二级单位涉案单位前10名涉案数量和金额")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult<List<LineChartModel>> result = new RestResult<>();
        switch (type) {
            // 法律案件金额统计图(前10月数据，不含本月)
            case 1:
                result.setData(ssajqkbgbService.getSumAmountLineChart());
                break;
            //法律案件前12个月企业涉案单位前三名未结案的趋势变化
            case 2:
                result.setData(ssajqkbgbService.getTopThreeLineChart());
                break;
            case 3:
                result.setData(ssajqkbgbService.getTopTenDatas());
                break;
        }
        return result;
    }

    /**
     * 子浮云
     * @param type
     * @return
     */
    @ApiOperation(value = "字符云")
    @RequestMapping(value = "/wordCloud", method = {RequestMethod.GET})
    public RestResult<List<SsajqkbgbModel>> wordCloud(
            @ApiParam(value = "1表示法律案件标的金额超过5000万的数据")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult<List<SsajqkbgbModel>> result = new RestResult<>();
        switch (type) {
            // 法律案件标的金额超过5000万的数据
            case 1:
                result.setData(ssajqkbgbService.getMoreThan5000());
                break;
        }
        return result;
    }
}
