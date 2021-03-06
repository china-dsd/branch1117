package com.casic.oarp.datavisual.api;

import com.casic.oarp.datavisual.Utils.EmptyUtils;
import com.casic.oarp.datavisual.common.ZXFKUtils;
import com.casic.oarp.datavisual.exception.ZXFKException;
import com.casic.oarp.datavisual.model.RestResult;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.model.zxfk.ZhongdjcfxpgtjModel;
import com.casic.oarp.datavisual.po.Zhongdjcfxpgtj;
import com.casic.oarp.datavisual.service.ZhongdjcfxpgtjService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/fengxpg")
@Api(value = "风险评估", tags = "风险评估")
public class ZhongdjcfxpgtjAPI {

    @Autowired
    private ZhongdjcfxpgtjService zhongdjcfxpgtjService;

    /**
     * 表格图
     *
     * @param
     * @return
     */
    @ApiOperation(value = "表格图")
    @RequestMapping(value = "/tableChart", method = {RequestMethod.GET})
    public RestResult<List<ZhongdjcfxpgtjModel>> tableChart(
            @ApiParam(value = "1表示事项风险程度排名")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult<List<ZhongdjcfxpgtjModel>> result = new RestResult<>();
        switch (type) {
            // 重要度排名
            case 1:
                result.setData(zhongdjcfxpgtjService.rankOfEventRisk());
                break;
        }
        return result;
    }

    /**
     * 饼状图
     *
     * @param type 1表示事项类型 2表示事项风险程度，3表示最终审议单位次级，4二级各单位采纳次数情况，5二级各单位未采纳次数情况
     * @return
     */
    @ApiOperation(value = "饼状图")
    @RequestMapping(value = "/pieChart", method = {RequestMethod.GET})
    public RestResult<List<SumModel>> pieChart(
            @ApiParam(value = "1表示事项类型 2表示事项风险程度，3表示最终审议单位次级，4二级各单位采纳次数情况，5二级各单位未采纳次数情况")
            @RequestParam(defaultValue = "1") Integer type,
            @ApiParam(value = "asc表示升序，desc表示降序")
            @RequestParam(defaultValue = "asc") String sort,
            String token) {
        RestResult<List<SumModel>> result = new RestResult<>();
        switch (type) {
            // 审计项目类型
            case 1:
                result.setData(zhongdjcfxpgtjService.getSumCountByProjectType());
                break;
            case 2:
                Date nowDate = ZXFKUtils.nowDate();
                Date startDate = ZXFKUtils.calcDateByNowDate(nowDate, 0, -6, 0);
                result.setData(zhongdjcfxpgtjService.getSumCountByRiskLevel(startDate, nowDate));
                break;
            case 3:
                // 表示最终审议单位次级
                result.setData(zhongdjcfxpgtjService.getSumCountByAdoptions());
                break;
            case 4:
                // 被采纳次数
                result.setData(zhongdjcfxpgtjService.getSumCountByAdoptionsed(sort, true));
                break;
            case 5:
                // 未被采纳次数
                result.setData(zhongdjcfxpgtjService.getSumCountByAdoptionsed(sort, false));
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
            @ApiParam(value = "1表示风险事项的数量及金额,2表示风险事项统计图(堆积图)")
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
                result.setData(zhongdjcfxpgtjService.getSumCountAndAmountBarChart(dates[0], dates[1]));
                break;
            case 2:
                result.setData(zhongdjcfxpgtjService.getSumAmountBarChart());
                break;
        }
        return result;
    }
    /**
     * 重大审计问题
     *
     * @param type
     * @return
     */
    @ApiOperation(value = "字符云")
    @RequestMapping(value = "/wordCloud", method = {RequestMethod.GET})
    public RestResult wordCloudChart(
            @ApiParam(value = "1表示高风险评估事项")
            @RequestParam(defaultValue = "1") Integer type,
            String token) {
        RestResult result = new RestResult();
        switch (type) {
            // 重大审计问题
            case 1:
                result.setData(zhongdjcfxpgtjService.getRiskAmountOver500w());
                break;
        }
        return result;
    }


    @ApiOperation(value="手动查询")
    @RequestMapping(value = "/select",method = {RequestMethod.GET})
    public RestResult selectByName(@RequestParam(required=false)String name){
        Boolean istrue;
        istrue=EmptyUtils.isEmpty(name);
        if (istrue){
            name="";
        }
        List<String> list=new ArrayList<>();
        List<Zhongdjcfxpgtj> result=new ArrayList();
        RestResult Result=new RestResult();
        result =zhongdjcfxpgtjService.selectbyname(name);
        int event=0;
        Map map=new HashMap();
        List list1 = new ArrayList();
        for (Zhongdjcfxpgtj v:result) {
            if (EmptyUtils.isNotEmpty(v.getSanjhsjdwmc()))
            list.add("("+v.getErjdwmc()+")"+v.getSanjhsjdwmc());
            else
                list.add(v.getErjdwmc());
            //审计数量
            event++;
        }
        list1.add(list);
        list1.add(event);
        Result.setData(list1);
        if (EmptyUtils.isEmpty(result)){
            return null;
        }else{
            return Result;
        }

    }
}
