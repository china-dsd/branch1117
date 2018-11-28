package com.casic.oarp.datavisual.service.impl;

import com.casic.oarp.datavisual.common.ZXFKUtils;
import com.casic.oarp.datavisual.mapper.SsajqkbgbExtendMapper;
import com.casic.oarp.datavisual.mapper.SsajqkbgbMapper;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SsajqkbgbModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.po.SsajqkbgbExample;
import com.casic.oarp.datavisual.po.SsajqkbgbVO;
import com.casic.oarp.datavisual.po.SsajqkbgbWithBLOBs;
import com.casic.oarp.datavisual.service.SsajqkbgbService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SsajqkbgbServiceImpl extends AbsBaseServiceImpl implements SsajqkbgbService {

    @Autowired
    private SsajqkbgbMapper ssajqkbgbMapper;
    @Autowired
    private SsajqkbgbExtendMapper ssajqkbgbExtendMapper;

    /**
     * 根据时间查询数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<SsajqkbgbWithBLOBs> findByDate(Date startDate, Date endDate) {
        SsajqkbgbExample ssajqkbgbExample = new SsajqkbgbExample();
        ssajqkbgbExample.createCriteria().andProsecutionDateBetween(startDate, endDate);
        List<SsajqkbgbWithBLOBs> result = ssajqkbgbMapper.selectByExampleWithBLOBs(ssajqkbgbExample);
        return result;
    }

    // 法律案件的数量及金额
    @Override
    public List<LineChartModel> getSumCountAndAmountBarChart(Date startDate, Date endDate) {
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumCountMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountMap = new TreeMap<>();
        for (SsajqkbgbWithBLOBs item : dataList) {
            // 二级单位名称
            String unitName = item.getSusejdw();
            // 涉及两金金额
            BigDecimal amount = item.getSjljje();
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
            // 计算金额
            if (null == amount) {
                amount = new BigDecimal(0);
            }
            if (sumAmountMap.containsKey(unitName)) {
                sumAmountMap.put(unitName, sumAmountMap.get(unitName).add(amount));
            } else {
                sumAmountMap.put(unitName, amount);
            }
        }

        Iterator<String> it = sumCountMap.keySet().iterator();
        LineChartModel<BigDecimal> amountLineChartModel = new LineChartModel<>();
        LineChartModel<Integer> countLineChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        List<BigDecimal> amountList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            countList.add(sumCountMap.get(key));
            amountList.add(sumAmountMap.get(key));
        }
        countLineChartModel.setTitle("法律案件数量");
        countLineChartModel.setData(countList);
        countLineChartModel.setName(nameList);
        amountLineChartModel.setTitle("涉及两金金额");
        amountLineChartModel.setData(amountList);
        amountLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(countLineChartModel);
        result.add(amountLineChartModel);
        return result;
    }

    @Override
    public List<SumModel> getSumCountByType1() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (SsajqkbgbWithBLOBs item : dataList) {
            // 案件类型
            String type1 = item.getCaseType1();
            if (StringUtils.isEmpty(type1)) {
                type1 = "其他";
            }
            if (sumMap.containsKey(type1)) {
                sumMap.put(type1, sumMap.get(type1) + 1);
            } else {
                sumMap.put(type1, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByType2() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (SsajqkbgbWithBLOBs item : dataList) {
            // 案件类别
            String type2 = item.getCaseType2();
            if (StringUtils.isEmpty(type2)) {
                type2 = "其他";
            }
            if (sumMap.containsKey(type2)) {
                sumMap.put(type2, sumMap.get(type2) + 1);
            } else {
                sumMap.put(type2, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByFinishType() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (SsajqkbgbWithBLOBs item : dataList) {
            // 结案方式
            String finishType = item.getJafs();
            if (StringUtils.isEmpty(finishType)) {
                finishType = "其他";
            }
            if (sumMap.containsKey(finishType)) {
                sumMap.put(finishType, sumMap.get(finishType) + 1);
            } else {
                sumMap.put(finishType, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByRole(String sort, String role) {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (SsajqkbgbWithBLOBs item : dataList) {
            // 二级单位
            String unitName = item.getSusejdw();
            if (StringUtils.isEmpty(unitName)) {
                unitName = "其他";
            }
            // 诉讼角色
            String arbitration = item.getLitigationArbitration();
            // 原告或者被告
            if (role.equalsIgnoreCase(arbitration)) {
                if (sumMap.containsKey(unitName)) {
                    sumMap.put(unitName, sumMap.get(unitName) + 1);
                } else {
                    sumMap.put(unitName, 1);
                }
            }
        }

        List<SumModel> result = assembleSumModelList(sumMap);
        Collections.sort(result, new SumModelIntegerComparator(sort));
        return result;
    }

    /**
     * 本年度每个月未结案数量，每个月的数据都有（包含0）
     * 当月就结案，则不算进未结案数量，如果5月11日结案，则不算进5月未结案中的案件中
     *
     * @return
     */
    @Override
    public List<LineChartModel> rankOfBidAmount() {
        Date endDate = ZXFKUtils.nowMonth();
        Date startDate = ZXFKUtils.getYearStart();
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        LineChartModel lineChartModel = getExtractNotJieAnModel(endDate, startDate, dataList,"未结案数量");
        List<LineChartModel> result = new ArrayList<>();
        result.add(lineChartModel);
        return result;
    }

    private LineChartModel getExtractNotJieAnModel(Date endDate, Date startDate, List<SsajqkbgbWithBLOBs> dataList,String title) {
        Map<String, Integer> notJieAnCount = new TreeMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
        //初始化
        while (startDate.getTime() < endDate.getTime()) {
            String key;
            try {
                key = sdf.format(startDate);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            startDate = ZXFKUtils.calcDateByNowDate(startDate, 0, 1, 0);
            notJieAnCount.put(key, 0);

        }
        for (SsajqkbgbWithBLOBs anjianItem : dataList) {
            Date prosecutionDate = anjianItem.getProsecutionDate();
            Date closingTime = anjianItem.getClosingTime();
            String jieAnMonth = null;
            String liAnMonth;
            try {
                liAnMonth = sdf.format(prosecutionDate);
                if (jieAnMonth != null)
                    jieAnMonth = sdf.format(closingTime);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            if (closingTime == null)
               // closingTime = ZXFKUtils.calcDateByNowDate(endDate, 0, 1, 0);
                jieAnMonth = sdf.format(endDate);
            //如果案件在当月结案了，那就不算当月的未结案数量中，因此加一个月进行比较
            //&& (prosecutionDate.getTime() + 1000l * 60 * 60 * 24 * 30) < closingTime.getTime()
            while (!liAnMonth.equals(jieAnMonth) ) {
                liAnMonth = sdf.format(prosecutionDate);
                if (prosecutionDate.getTime() >= endDate.getTime())
                    break;
                notJieAnCount.put(liAnMonth, notJieAnCount.get(liAnMonth) + 1);
                prosecutionDate = ZXFKUtils.calcDateByNowDate(prosecutionDate, 0, 1, 0);
            }
        }
        List<String> nameList = new ArrayList(notJieAnCount.keySet());
        List<Integer> data = new ArrayList<>();
        Iterator<String> it = notJieAnCount.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            data.add(notJieAnCount.get(key));
        }
        LineChartModel lineChartModel = new LineChartModel();
        lineChartModel.setTitle(title);
        lineChartModel.setData(data);
        lineChartModel.setName(nameList);
        return lineChartModel;
    }

    /**
     * 新增案件数量和案件存量
     *
     * @return
     */
    @Override
    public List<LineChartModel> getCaseCount() {
        Date endDate = ZXFKUtils.nowMonth();
        Date startDate = ZXFKUtils.getYearStart();
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);

        Map<String, Integer> addCaseCount = new TreeMap<>();
        Map<String, Integer> existCaseCount = new TreeMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
        //初始化
        while (startDate.getTime() <= endDate.getTime()) {
            String key;
            try {
                key = sdf.format(startDate);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            startDate = ZXFKUtils.calcDateByNowDate(startDate, 0, 1, 0);
            addCaseCount.put(key, 0);
            existCaseCount.put(key, 0);
        }
        for (SsajqkbgbWithBLOBs anjianItem : dataList) {
            Date prosecutionDate = anjianItem.getProsecutionDate();
            String liAnMonth;
            try {
                liAnMonth = sdf.format(prosecutionDate);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            //计算案件增量
            liAnMonth = sdf.format(prosecutionDate);
            addCaseCount.put(liAnMonth, addCaseCount.get(liAnMonth) + 1);
        }

        List<String> nameList = new ArrayList<>(addCaseCount.keySet());
        List<Integer> addList = new ArrayList<>();
        List<Integer> existList = new ArrayList<>();
        Integer sum = 0;
        Iterator<String> iterator = addCaseCount.keySet().iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            Integer integer = addCaseCount.get(next);
            addList.add(integer);
            sum += integer;
            existList.add(sum);
        }
        LineChartModel addCharModel = new LineChartModel();
        LineChartModel existCharModel = new LineChartModel();
        addCharModel.setTitle("新增案件数量");
        addCharModel.setName(nameList);
        addCharModel.setData(addList);
        existCharModel.setTitle("案件存量");
        existCharModel.setName(nameList);
        existCharModel.setData(existList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(addCharModel);
        result.add(existCharModel);
        return result;
    }

    @Override
    public List<SsajqkbgbModel> rankOfLJJEAmount() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        List<SsajqkbgbModel> result = new ArrayList<>();
        SsajqkbgbModel ssajqkbgbModel = null;
        for (SsajqkbgbWithBLOBs item : dataList) {
            ssajqkbgbModel = new SsajqkbgbModel();
            BeanUtils.copyProperties(item, ssajqkbgbModel);
            result.add(ssajqkbgbModel);
        }
        // 涉及两金金额排名
        Collections.sort(result, new Comparator<SsajqkbgbModel>() {

            @Override
            public int compare(SsajqkbgbModel o1, SsajqkbgbModel o2) {
                try {
                    BigDecimal oldValue = o1.getSjljje();
                    BigDecimal newValue = o2.getSjljje();
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
        return result;
    }

    /**
     * 获取当前年截止目前的结案数量，未结案数量，挽回两金金额，压减两金金额（不是当前年的不考虑）
     *
     * @return
     */
    @Override
    public List<SumModel> getSumCountAndAmountForIndex() {
        Date nowDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.getYearStart();
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, nowDate);
        // 压减两金金额
        BigDecimal yaJianAmount = new BigDecimal(0);
        //挽回两斤金额
        BigDecimal wanHuiAmount = new BigDecimal(0);
        //未结案数量
        Integer doingCount = 0;
        //已结案数量
        Integer finishedCount = 0;

        for (SsajqkbgbWithBLOBs item : dataList) {
            //计算结案数量
            if (item.getJafs() != null && item.getJafs().length() != 0) {
                finishedCount += 1;
            } else {//未结案数量
                doingCount += 1;
            }
            // 计算挽回两金金额
            BigDecimal wanHui = item.getWhjjssje();
            if (wanHui != null) {
                wanHuiAmount = wanHuiAmount.add(wanHui);
            }
            // 计算压减两金金额
            BigDecimal yaJian = item.getHjljje();
            if (yaJian != null) {
                yaJianAmount = yaJianAmount.add(yaJian);
            }
        }

        SumModel<BigDecimal> wanHuiChartModel = new SumModel<>();
        SumModel<BigDecimal> yaJianChartModel = new SumModel<>();
        SumModel<Integer> finishedChartModel = new SumModel<>();
        SumModel<Integer> finishingChartModel = new SumModel<>();
        wanHuiChartModel.setName("挽回两金金额");
        wanHuiChartModel.setValue(wanHuiAmount.divide(new BigDecimal(100000000)).setScale(2, BigDecimal.ROUND_HALF_UP));
        yaJianChartModel.setName("压减两金金额");
        yaJianChartModel.setValue(yaJianAmount.divide(new BigDecimal(100000000)).setScale(2, BigDecimal.ROUND_HALF_UP));
        finishedChartModel.setName("已结案数量");
        finishedChartModel.setValue(finishedCount);
        finishingChartModel.setName("未结案数量");
        finishingChartModel.setValue(doingCount);
        List<SumModel> result = new ArrayList<>();
        result.add(yaJianChartModel);
        result.add(wanHuiChartModel);
        result.add(finishedChartModel);
        result.add(finishingChartModel);
        return result;
    }

    /**
     * 获取涉案单位前三名
     *
     * @param startDate
     * @return
     */
    public List<String> findTopThreeData(Date startDate) {
        List<String> topThreeData = ssajqkbgbExtendMapper.findTopThreeData(startDate);
        return topThreeData;
    }

    /**
     * 获取前三名的涉案信息
     *
     * @param startDate
     * @return
     */
    public List<SsajqkbgbWithBLOBs> findTopThreeChange(Date startDate, List<String> list) {
        SsajqkbgbExample ssajqkbgbExample = new SsajqkbgbExample();
        SsajqkbgbExample.Criteria criteria = ssajqkbgbExample.createCriteria();
        criteria.andProsecutionDateGreaterThan(startDate);
        criteria.andBqysadwIn(list);
        List<SsajqkbgbWithBLOBs> ssajqkbgbWithBLOBs = ssajqkbgbMapper.selectByExampleWithBLOBs(ssajqkbgbExample);
        return ssajqkbgbWithBLOBs;
    }

    /**
     * 法律案件前12个月企业涉案单位前三名未结案的趋势变化
     *
     * @return
     */
    @Override
    public List<LineChartModel> getTopThreeLineChart() {
        Date endDate = ZXFKUtils.nowMonth();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, -1, 0, 0);
        List<String> topThreeData = findTopThreeData(startDate);
        List<SsajqkbgbWithBLOBs> topThreeChange = findTopThreeChange(startDate, topThreeData);
        List<SsajqkbgbWithBLOBs> first = new ArrayList<>();
        List<SsajqkbgbWithBLOBs> second = new ArrayList<>();
        List<SsajqkbgbWithBLOBs> third = new ArrayList<>();
        for (SsajqkbgbWithBLOBs ssajqkbgbWithBLOBs : topThreeChange) {
            if (ssajqkbgbWithBLOBs.getBqysadw().equals(topThreeData.get(0))) {
                first.add(ssajqkbgbWithBLOBs);
            } else if (ssajqkbgbWithBLOBs.getBqysadw().equals(topThreeData.get(1))) {
                second.add(ssajqkbgbWithBLOBs);
            } else if (ssajqkbgbWithBLOBs.getBqysadw().equals(topThreeData.get(2))) {
                third.add(ssajqkbgbWithBLOBs);
            }
        }
        LineChartModel extractNotJieAnModel1 = getExtractNotJieAnModel(endDate, startDate, first,topThreeData.get(0));
        LineChartModel extractNotJieAnModel2 = getExtractNotJieAnModel(endDate, startDate, second,topThreeData.get(1));
        LineChartModel extractNotJieAnModel3 = getExtractNotJieAnModel(endDate, startDate, third,topThreeData.get(2));
        List<LineChartModel> result = new ArrayList<>();
        result.add(extractNotJieAnModel1);
        result.add(extractNotJieAnModel2);
        result.add(extractNotJieAnModel3);
        return result;
    }

    /**
     * 二级单位涉案单位前10名涉案数量和金额
     * @return
     */
    @Override
    public List<LineChartModel> getTopTenDatas() {
        Date startDate = ZXFKUtils.getYearStart();
        List<SsajqkbgbVO> topTenData = ssajqkbgbExtendMapper.findTopTenData(startDate);
        List<String> name = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        List<Integer> sum = new ArrayList<>();
        for (SsajqkbgbVO ssa :topTenData) {
            name.add(ssa.getBqysadw());
            count.add(ssa.getCount());
            sum.add(ssa.getSum());
        }
        LineChartModel countModel = new LineChartModel();
        countModel.setName(name);
        countModel.setData(count);
        countModel.setTitle("前10名涉案数量");
        LineChartModel sumModel = new LineChartModel();
        sumModel.setName(name);
        sumModel.setData(sum);
        sumModel.setTitle("前10名涉案金额");
        List<LineChartModel> result = new ArrayList<>();
        result.add(countModel);
        result.add(sumModel);
        return result;
    }

    @Override
    public List<LineChartModel> getJieAnCountAndAmount() {
        Date endDate = ZXFKUtils.nowMonth();
        Date startDate = ZXFKUtils.getYearStart();
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> jieAnCount = new TreeMap<>();
        Map<String, Long> saveAmout = new TreeMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
        //初始化
        while (startDate.getTime() <= endDate.getTime()) {
            String key;
            try {
                key = sdf.format(startDate);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            startDate = ZXFKUtils.calcDateByNowDate(startDate, 0, 1, 0);
            jieAnCount.put(key, 0);
            saveAmout.put(key, 0l);
        }
        for (SsajqkbgbWithBLOBs anjianItem : dataList) {
            Date closingTime = anjianItem.getClosingTime();
            if(closingTime==null)
                continue;
            Long whjjssje = anjianItem.getWhjjssje().longValue();

            String closingMonth;
            try {
                closingMonth = sdf.format(closingTime);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            //计算已结案数量
            jieAnCount.put(closingMonth,jieAnCount.get(closingMonth)+1);
            saveAmout.put(closingMonth,saveAmout.get(closingMonth)+whjjssje);
        }

        List<String> nameList = new ArrayList<>(jieAnCount.keySet());
        List<Integer> jieAnCountList = new ArrayList<>();
        List<Long> saveAmountList = new ArrayList<>();
        Iterator<String> iterator = jieAnCount.keySet().iterator();
        Integer sumCount = 0;
        Long sumAmount = 0l;
        while (iterator.hasNext()) {
            String next = iterator.next();
            Integer integer = jieAnCount.get(next);
            sumCount+=integer;
            jieAnCountList.add(sumCount);
            Long aLong = saveAmout.get(next);
            sumAmount+=aLong;
            saveAmountList.add(sumAmount);
        }
        LineChartModel addCharModel = new LineChartModel();
        LineChartModel existCharModel = new LineChartModel();
        addCharModel.setTitle("累积办案数");
        addCharModel.setName(nameList);
        addCharModel.setData(jieAnCountList);
        existCharModel.setTitle("累计挽回损失金额");
        existCharModel.setName(nameList);
        existCharModel.setData(saveAmountList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(addCharModel);
        result.add(existCharModel);
        return result;
    }


    // 法律案件金额统计图
    @Override
    public List<LineChartModel> getSumAmountLineChart() {
        Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.calcDateByNowDate(endMonth, 0, -10, 0);
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startMonth, endMonth);
        // 涉及两金金额
        Map<String, BigDecimal> sumSJLJJEAmountMap = new TreeMap<>();
        // 挽回经济所示金额
        Map<String, BigDecimal> sumWHJJSSJEAmountMap = new TreeMap<>();
        for (SsajqkbgbWithBLOBs item : dataList) {
            SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
            String key = "";
            try {
                key = sdf.format(item.getProsecutionDate());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            // 计算涉及两金金额
            BigDecimal SJLJJEAmount = item.getSjljje();
            if (null == SJLJJEAmount) {
                SJLJJEAmount = new BigDecimal(0);
            }
            if (sumSJLJJEAmountMap.containsKey(key)) {
                sumSJLJJEAmountMap.put(key, sumSJLJJEAmountMap.get(key).add(SJLJJEAmount));
            } else {
                sumSJLJJEAmountMap.put(key, SJLJJEAmount);
            }

            // 计算挽回经济所示金额
            BigDecimal WHJJSSJEAmount = item.getWhjjssje();
            if (null == WHJJSSJEAmount) {
                WHJJSSJEAmount = new BigDecimal(0);
            }
            if (sumWHJJSSJEAmountMap.containsKey(key)) {
                sumWHJJSSJEAmountMap.put(key, sumWHJJSSJEAmountMap.get(key).add(WHJJSSJEAmount));
            } else {
                sumWHJJSSJEAmountMap.put(key, WHJJSSJEAmount);
            }
        }

        Iterator<String> it = sumSJLJJEAmountMap.keySet().iterator();
        LineChartModel<BigDecimal> SJLJJEAmountLineChartModel = new LineChartModel<>();
        LineChartModel<BigDecimal> WHJJSSJEAamountLineChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<BigDecimal> SJLJJEAmountList = new ArrayList<>();
        List<BigDecimal> WHJJSSJEAamountList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            SJLJJEAmountList.add(sumSJLJJEAmountMap.get(key));
            WHJJSSJEAamountList.add(sumWHJJSSJEAmountMap.get(key));
        }
        SJLJJEAmountLineChartModel.setData(SJLJJEAmountList);
        SJLJJEAmountLineChartModel.setName(nameList);
        SJLJJEAmountLineChartModel.setTitle("涉及两金金额");
        WHJJSSJEAamountLineChartModel.setTitle("挽回经济损失金额");
        WHJJSSJEAamountLineChartModel.setData(WHJJSSJEAamountList);
        WHJJSSJEAamountLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(SJLJJEAmountLineChartModel);
        result.add(WHJJSSJEAamountLineChartModel);
        return result;
    }

    /**
     * 分组标的金额
     *
     * @return
     */
    @Override
    public List<SumModel> getGroupAmount() {
        Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.getYearStart();
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startMonth, endMonth);
        Integer below1000 = 0;
        Integer below5000 = 0;
        Integer above5000 = 0;
        for (SsajqkbgbWithBLOBs item : dataList) {
            BigDecimal bdje = item.getBdje();
            if (bdje==null)
                bdje = new BigDecimal(0);
            int intValue = bdje.intValue();
            if (intValue > 0 && intValue < 1000) {
                below1000++;
            } else if (intValue >= 1000 && intValue <= 5000) {
                below5000++;
            } else if (intValue > 5000) {
                above5000++;
            }
        }
        SumModel below1000Model = new SumModel("小于1000万", below1000);
        SumModel below5000Model = new SumModel("小于5000万", below5000);
        SumModel above5000Model = new SumModel("大于5000万", above5000);
        List<SumModel> result = new ArrayList<>();
        result.add(below1000Model);
        result.add(below5000Model);
        result.add(above5000Model);
        return result;
    }

    /**
     * 获取诉讼类型
     */
    @Override
    public List<SumModel> getLitigationType() {
        Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.getYearStart();
        List<SsajqkbgbWithBLOBs> dataList = findByDate(startMonth, endMonth);

        Integer accuserCount = 0;
        Integer accusedCount = 0;
        for (SsajqkbgbWithBLOBs item : dataList) {
            String litigationArbitration = item.getLitigationArbitration();
            if (litigationArbitration.contains("原告")) {
                accuserCount++;
            } else if (litigationArbitration.contains("被告")) {
                accusedCount++;
            }
        }
        SumModel accuserCountModel = new SumModel("原告", accuserCount);
        SumModel accusedCountModel = new SumModel("被告", accusedCount);
        List<SumModel> result = new ArrayList<>();
        result.add(accuserCountModel);
        result.add(accusedCountModel);
        return result;
    }

    public List<SsajqkbgbWithBLOBs> findDataMoreThan5000(Date startDate, Date endDate, BigDecimal bigDecimal) {
        SsajqkbgbExample ssajqkbgbExample = new SsajqkbgbExample();
        SsajqkbgbExample.Criteria criteria = ssajqkbgbExample.createCriteria();
        criteria.andProsecutionDateBetween(startDate, endDate);
        criteria.andBdjeGreaterThan(bigDecimal);
        return ssajqkbgbMapper.selectByExampleWithBLOBs(ssajqkbgbExample);
    }

    /**
     * 获取标的金额超过5000万的数据
     */
    @Override
    public List<SsajqkbgbModel> getMoreThan5000() {
        Date endMonth = ZXFKUtils.calcDateByNowDate(ZXFKUtils.nowMonth(), 0, 1, 0);
        Date startMonth = ZXFKUtils.getYearStart();
        BigDecimal bigDecimal = new BigDecimal(5000);
        List<SsajqkbgbWithBLOBs> dataMoreThan5000 = findDataMoreThan5000(startMonth, endMonth, bigDecimal);
        List<SsajqkbgbModel> result = new ArrayList<>();
        for (SsajqkbgbWithBLOBs item : dataMoreThan5000) {
            SsajqkbgbModel ssajqkbgbModel = new SsajqkbgbModel();
            BeanUtils.copyProperties(item, ssajqkbgbModel);
            result.add(ssajqkbgbModel);
        }
        return result;
    }
}
