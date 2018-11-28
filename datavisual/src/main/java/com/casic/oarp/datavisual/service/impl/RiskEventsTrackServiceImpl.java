package com.casic.oarp.datavisual.service.impl;

import com.casic.oarp.datavisual.Utils.EmptyUtils;
import com.casic.oarp.datavisual.common.ZXFKUtils;
import com.casic.oarp.datavisual.mapper.RiskEventsTrackMapper;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.RiskEventTableModel;
import com.casic.oarp.datavisual.model.zxfk.RiskWanHuiModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.po.*;
import com.casic.oarp.datavisual.service.RiskEventsTrackService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RiskEventsTrackServiceImpl extends AbsBaseServiceImpl implements RiskEventsTrackService {

    @Autowired
    private RiskEventsTrackMapper riskEventsTrackMapper;

    private List<RiskEventsTrackWithBLOBs> findByDate(Date startDate, Date endDate) {
        RiskEventsTrackExample riskEventsTrackExample = new RiskEventsTrackExample();
        riskEventsTrackExample.createCriteria().andZCreatetimeBetween(startDate, endDate);
        List<RiskEventsTrackWithBLOBs> result = riskEventsTrackMapper.selectByExampleWithBLOBs(
                riskEventsTrackExample);
        return result;
    }

    /**
     * 风险事件挽回金额
     *
     * @return
     */
    @Override
    public List<RiskWanHuiModel> getSumAmountBarChart() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.getYearStart();
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        List<RiskWanHuiModel> result = new ArrayList<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            if (item.getBeginMoney()==null)
                continue;
            BigDecimal beginMoney = item.getBeginMoney().divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP);
            if (beginMoney==null||beginMoney.intValue()==0)
                continue;
            if (item.getEndMoney()==null)
                continue;
            BigDecimal endMoney = item.getEndMoney().divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP);;
            BigDecimal saveAmount = beginMoney.subtract(endMoney);
            Double saveRate = saveAmount.divide(beginMoney,2,BigDecimal.ROUND_HALF_UP).doubleValue();
            String name = item.getNameTwo();
            RiskWanHuiModel riskWanHuiModel = new RiskWanHuiModel(name,saveAmount,beginMoney,saveRate*100);
            result.add(riskWanHuiModel);
        }
        return result;
    }
    @Override
    public List<LineChartModel> getSumAmountLineChart() {
        Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.calcDateByNowDate(endMonth, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startMonth, endMonth);
        Map<String, BigDecimal> sumAmountBeginMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountEndMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
            String key = "";
            try {
                key = sdf.format(item.getzCreatetime());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            assembleAmountMap(item, key, sumAmountBeginMap, sumAmountEndMap);
        }

        Iterator<String> it = sumAmountBeginMap.keySet().iterator();
        LineChartModel<BigDecimal> amountBeginLineChartModel = new LineChartModel<>();
        LineChartModel<BigDecimal> amountEndLineChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<BigDecimal> amountBeginList = new ArrayList<>();
        List<BigDecimal> amountEndList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            amountBeginList.add(sumAmountBeginMap.get(key));
            amountEndList.add(sumAmountEndMap.get(key));
        }
        amountBeginLineChartModel.setData(amountBeginList);
        amountBeginLineChartModel.setName(nameList);
        amountBeginLineChartModel.setTitle("期初金额");
        amountEndLineChartModel.setTitle("期末金额");
        amountEndLineChartModel.setData(amountEndList);
        amountEndLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(amountBeginLineChartModel);
        result.add(amountEndLineChartModel);
        return result;
    }

    @Override
    public List<LineChartModel> getSumAmountBarChart(Date startDate, Date endDate) {
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, BigDecimal> sumAmountBeginMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountEndMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            // 二级单位名称
            assembleAmountMap(item, item.getNameTwo(), sumAmountBeginMap, sumAmountEndMap);
        }

        Iterator<String> it = sumAmountBeginMap.keySet().iterator();
        LineChartModel<BigDecimal> amountBeginLineChartModel = new LineChartModel<>();
        LineChartModel<BigDecimal> amountEndLineChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<BigDecimal> amountEndList = new ArrayList<>();
        List<BigDecimal> amountBeginList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            amountBeginList.add(sumAmountBeginMap.get(key));
            amountEndList.add(sumAmountEndMap.get(key));
        }
        amountBeginLineChartModel.setData(amountBeginList);
        amountBeginLineChartModel.setName(nameList);
        amountBeginLineChartModel.setTitle("期初金额");
        amountEndLineChartModel.setTitle("期末金额");
        amountEndLineChartModel.setData(amountEndList);
        amountEndLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(amountBeginLineChartModel);
        result.add(amountEndLineChartModel);
        return result;
    }


    @Override
    /*风险事件相关责任部门*/
    public List<SumModel> getSumAmountByEndMoney() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String,RiskEventsVO> event=new HashMap<>();/*String:公司名称*/
        Set<String> eventTypeList=new HashSet<>();
        List all=new ArrayList();
        for (RiskEventsTrackWithBLOBs item:dataList) {
            eventTypeList.add(item.getEventsType());
            if (event.containsKey(item.getNameTwo())){
               RiskEventsVO reVO=event.get(item.getNameTwo());
               if (item.getEventsType().equals("高"))
                   reVO.setGao(reVO.getGao()+1);
               else if (item.getEventsType().equals("中"))
                   reVO.setZhong(reVO.getZhong()+1);
               else
                   reVO.setDi(reVO.getDi()+1);
            }else{
                event.put(item.getNameTwo(),new RiskEventsVO());
                RiskEventsVO re2VO=event.get(item.getNameTwo());
                if (item.getEventsType().equals("高"))
                    re2VO.setGao(re2VO.getGao()+1);
                else if (item.getEventsType().equals("中"))
                    re2VO.setZhong(re2VO.getZhong()+1);
                else
                    re2VO.setDi(re2VO.getDi()+1);
            }
        }
        all.add(event);
        all.add(eventTypeList);
        return all;
    }
    private void not(RiskEventsTrackWithBLOBs item,
                     String name,
                     Map<String,Integer> map,
                     Map<String,Map> map2){
        if (!map2.containsKey(name))
            map2.put(name,map);
        else {
            Map type= map2.get(name);
           if (type.containsKey(item.getEventsType()))
               type.put(item.getEventsType(),map.get(item.getEventsType())+1);
           else
               type.put(item.getEventsType(),1);
        }
    }

    @Override
    public List<SumModel> getSumCountByEventType() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            String eventType = item.getEventsType();
            if (StringUtils.isEmpty(eventType)) {
                eventType = "未标明";
            }
            if (sumMap.containsKey(eventType)) {
                sumMap.put(eventType, sumMap.get(eventType) + 1);
            } else {
                sumMap.put(eventType, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByEventProgress() {
        /*Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            String eventProgress = item.getRiskEventsProgress();
            if (StringUtils.isEmpty(eventProgress)) {
                eventProgress = "其他";
            }
            if (sumMap.containsKey(eventProgress)) {
                sumMap.put(eventProgress, sumMap.get(eventProgress) + 1);
            } else {
                sumMap.put(eventProgress, 1);
            }
        }

        return assembleSumModelList(sumMap);*/
        RiskEventsTrackExample rExample = new RiskEventsTrackExample();
        List<RiskEventsTrack> result=riskEventsTrackMapper.selectByExample(rExample);
        Map<String, Integer> sumMap = new TreeMap<>();
        for (RiskEventsTrack r:result){
            String eventsEnd=r.getEventsEnd();
            System.out.println(eventsEnd);
            if (EmptyUtils.isEmpty(eventsEnd)||eventsEnd.equals("否"))
                eventsEnd="未完成";
            if (sumMap.containsKey(eventsEnd)){
                sumMap.put(eventsEnd,sumMap.get(eventsEnd)+1);
            }else{
                sumMap.put(eventsEnd,1);
            }
        }
        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByLawsuit() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            String lawsuitProcedure = item.getLawsuitProcedure();
            if (StringUtils.isEmpty(lawsuitProcedure)) {
                lawsuitProcedure = "其他";
            }
            if (sumMap.containsKey(lawsuitProcedure)) {
                sumMap.put(lawsuitProcedure, sumMap.get(lawsuitProcedure) + 1);
            } else {
                sumMap.put(lawsuitProcedure, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumAmountByEndMoney(String sort) {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, BigDecimal> sumAmountEndMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            String nameTwo = item.getNameTwo();
            if (StringUtils.isEmpty(nameTwo)) {
                nameTwo = "其他";
            }
            BigDecimal endAmount = item.getEndMoney();
            if (null == endAmount) {
                endAmount = new BigDecimal(0);
            }

            if (sumAmountEndMap.containsKey(nameTwo)) {
                sumAmountEndMap.put(nameTwo, sumAmountEndMap.get(nameTwo).add(endAmount));
            } else {
                sumAmountEndMap.put(nameTwo, endAmount);
            }
        }

        List<SumModel> result = assembleSumModelList(sumAmountEndMap);
        Collections.sort(result, new SumModelBigDecimalComparator(sort));
        return result;
    }



    @Override
    public List<SumModel> getDiffAmountByMoney(String sort) {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, BigDecimal> sumAmountBeginMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountEndMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            String nameTwo = item.getNameTwo();
            if (StringUtils.isEmpty(nameTwo)) {
                nameTwo = "其他";
            }
            BigDecimal endAmount = item.getEndMoney();
            if (null == endAmount) {
                endAmount = new BigDecimal(0);
            }

            if (sumAmountEndMap.containsKey(nameTwo)) {
                sumAmountEndMap.put(nameTwo, sumAmountEndMap.get(nameTwo).add(endAmount));
            } else {
                sumAmountEndMap.put(nameTwo, endAmount);
            }

            BigDecimal beginAmount = item.getBeginMoney();
            if (null == beginAmount) {
                beginAmount = new BigDecimal(0);
            }

            if (sumAmountBeginMap.containsKey(nameTwo)) {
                sumAmountBeginMap.put(nameTwo, sumAmountBeginMap.get(nameTwo).add(beginAmount));
            } else {
                sumAmountBeginMap.put(nameTwo, beginAmount);
            }
        }

        Iterator<String> it = sumAmountBeginMap.keySet().iterator();
        List<SumModel> result = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            SumModel sumModel = new SumModel();
            sumModel.setName(key);
            // 差值
            sumModel.setValue(sumAmountEndMap.get(key).subtract(sumAmountBeginMap.get(key)));
            result.add(sumModel);
        }
        Collections.sort(result, new SumModelBigDecimalComparator(sort));
        return result;
    }

    // 风险事件金额排名
    @Override
    public List<RiskEventTableModel> rankOfAmount() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        List<RiskEventTableModel> result = new ArrayList<>();
        RiskEventTableModel riskEventTableModel = null;
        for (RiskEventsTrackWithBLOBs item : dataList) {
            riskEventTableModel = new RiskEventTableModel();
            BeanUtils.copyProperties(item, riskEventTableModel);
            result.add(riskEventTableModel);
        }
        // 根据endMoney降序排序
        Collections.sort(result, new Comparator<RiskEventTableModel>() {
            @Override
            public int compare(RiskEventTableModel o1, RiskEventTableModel o2) {
                try {
                    BigDecimal oldValue = o1.getEndMoney();
                    BigDecimal newValue = o2.getEndMoney();
                    if (oldValue == null) {
                        oldValue = new BigDecimal(0);
                    }
                    if (newValue == null) {
                        newValue = new BigDecimal(0);
                    }
                    return newValue.compareTo(oldValue);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        return result.subList(0,10);
    }

    // 创建时间排名
    @Override
    public List<RiskEventTableModel> rankOfCreateTime() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        List<RiskEventTableModel> result = new ArrayList<>();
        RiskEventTableModel riskEventTableModel = null;
        for (RiskEventsTrackWithBLOBs item : dataList) {
            riskEventTableModel = new RiskEventTableModel();
            BeanUtils.copyProperties(item, riskEventTableModel);
            result.add(riskEventTableModel);
        }
        // 根据创建时间降序排序
        Collections.sort(result, new Comparator<RiskEventTableModel>() {
            @Override
            public int compare(RiskEventTableModel o1, RiskEventTableModel o2) {
                try {
                    Date oldDate = o1.getzCreatetime();
                    Date newDate = o2.getzCreatetime();
                    if (oldDate == null) {
                        oldDate = new Date();
                    }
                    if (newDate == null) {
                        newDate = new Date();
                    }
                    return newDate.compareTo(oldDate);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        return result;
    }

    /**
     * 重大经营风险事件总项数,已完成风险化解项数,涉及风险金额
     *
     * @return
     */
    @Override
    public List<SumModel> getSumCountAndAmountForIndex() {
        Date endMonth = ZXFKUtils.nowDate();
        Date startMonth = ZXFKUtils.getYearStart();
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startMonth, endMonth);
        //重大经营风险事件总项数
        Integer riskCount = 0;
        //已完成风险化解项数
        Integer defusedCount = 0;
        //涉及风险金额
        BigDecimal riskAmountCount = new BigDecimal(0);

        for (RiskEventsTrackWithBLOBs item : dataList) {

            BigDecimal beginMoney = item.getBeginMoney();
            if (beginMoney==null)
                continue;
            if (beginMoney.compareTo(new BigDecimal(5000)) >= 0) {
                riskCount += 1;
                if(item.getEndMoney()==null)
                    continue;
                riskAmountCount = riskAmountCount.add(item.getEndMoney());
                if (item.getEventsEnd() != null && (item.getEventsEnd().equals("是")||item.getEventsEnd().equals("已完成"))) {
                    defusedCount += 1;
                }
            }
        }

        SumModel<Integer> riskCountModel = new SumModel<>();
        SumModel<Integer> defusedCountModel = new SumModel<>();
        SumModel<BigDecimal> riskAmountCountModel = new SumModel<>();
        //风险事件项
        riskCountModel.setName("重大经营风险事件总项数：");
        riskCountModel.setValue(143);
        //已解决风险事件项
        defusedCountModel.setName("已完成风险化验项数：");
        defusedCountModel.setValue(20);
        //涉及的金额
        riskAmountCountModel.setName("涉及风险金额：");
        riskAmountCountModel.setValue(riskAmountCount);
        List<SumModel> result = new ArrayList<>();
        result.add(riskCountModel);
        result.add(defusedCountModel);
        result.add(riskAmountCountModel);
        return result;
    }


    private void assembleAmountMap(RiskEventsTrackWithBLOBs item, String key,
                                   Map<String, BigDecimal> sumAmountBeginMap, Map<String, BigDecimal> sumAmountEndMap) {
        BigDecimal amountBegin = item.getBeginMoney();
        BigDecimal amountEnd = item.getEndMoney();
        // 有可能是null的，这里进行判断
        if (StringUtils.isEmpty(key)) {
            key = "其他";
        }

        // 期初金额
        if (null == amountBegin) {
            amountBegin = new BigDecimal(0);
        }
        if (sumAmountBeginMap.containsKey(key)) {
            sumAmountBeginMap.put(key, sumAmountBeginMap.get(key).add(amountBegin));
        } else {
            sumAmountBeginMap.put(key, amountBegin);
        }

        // 期末金额
        if (null == amountEnd) {
            amountEnd = new BigDecimal(0);
        }
        if (sumAmountEndMap.containsKey(key)) {
            sumAmountEndMap.put(key, sumAmountEndMap.get(key).add(amountEnd));
        } else {
            sumAmountEndMap.put(key, amountEnd);
        }
    }



    public List<RiskEventsTrack>  findByMoney(){
        RiskEventsTrackExample reExample = new RiskEventsTrackExample();
        reExample.createCriteria().andBeginMoneyGreaterThan(new BigDecimal(5000));
        List<RiskEventsTrack> result=riskEventsTrackMapper.selectByExample(reExample);
        return result;
    }

    @Override
    public List<RiskEventsTrack> findByName(String name) {
        RiskEventsTrackExample rExample = new RiskEventsTrackExample();
        rExample.createCriteria().andNameTwoLike("%"+name+"%");
        List<RiskEventsTrack> result=riskEventsTrackMapper.selectByExample(rExample);
        return result;
    }

    @Override
    public List<LineChartModel> getSumCountAndAmountBarChart(Date startDate, Date endDate) {
        List<RiskEventsTrackWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumCountMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountBeginMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountEndMap = new TreeMap<>();
        for (RiskEventsTrackWithBLOBs item : dataList) {
            // 二级单位名称
            String unitName = item.getNameTwo();
            assembleAmountMap(item, item.getNameTwo(), sumAmountBeginMap, sumAmountEndMap);
            // 有可能是null的，这里进行判断
            if (StringUtils.isEmpty(unitName)) {
                unitName = "其他";
            }

            // 计算数量
            if (sumCountMap.containsKey(unitName)) {
                sumCountMap.put(unitName, sumCountMap.get(unitName) + 1);
            } else {
                sumCountMap.put(unitName, 1);
            }
        }
        Iterator<String> it = sumCountMap.keySet().iterator();
        Iterator<String> it2 = sumAmountBeginMap.keySet().iterator();
        LineChartModel<Integer> countLineChartModel = new LineChartModel<>();
        LineChartModel<BigDecimal> amountEndLineChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        List<BigDecimal> amountEndList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            countList.add(sumCountMap.get(key));
        }
        while (it2.hasNext()) {
            String key = it2.next();
            nameList.add(key);
            amountEndList.add(sumAmountEndMap.get(key));
        }
        countLineChartModel.setTitle("风险事件数量");
        countLineChartModel.setData(countList);
        countLineChartModel.setName(nameList);

        amountEndLineChartModel.setTitle("期末金额");
        amountEndLineChartModel.setData(amountEndList);
        amountEndLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(countLineChartModel);
        result.add(amountEndLineChartModel);
        return result;
    }



    @Override
    //重大风险事件字符云
    public List<SumModel> getCharCloud() {
        List<RiskEventsTrack> resul=findByMoney();
        List<SumModel> result=new ArrayList<>();
        for (RiskEventsTrack rs:resul){
            rs.getNameTwo();
            rs.getBeginMoney();
            result.add(new SumModel(rs.getNameTwo()+"涉及金额"+rs.getBeginMoney()+"万元",rs.getBeginMoney()));
        }
        return result;
    }

}
