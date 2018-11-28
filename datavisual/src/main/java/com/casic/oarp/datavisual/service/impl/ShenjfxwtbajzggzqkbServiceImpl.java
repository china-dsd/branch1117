package com.casic.oarp.datavisual.service.impl;

import com.casic.oarp.datavisual.common.ZXFKUtils;
import com.casic.oarp.datavisual.mapper.ShenjfxwtbajzggzqkbMapper;
import com.casic.oarp.datavisual.model.zxfk.AuditProjectTableModel;
import com.casic.oarp.datavisual.model.zxfk.AuditVO;
import com.casic.oarp.datavisual.model.zxfk.LineChartModel;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.po.ShenjfxwtbajzggzqkbExample;
import com.casic.oarp.datavisual.po.ShenjfxwtbajzggzqkbWithBLOBs;
import com.casic.oarp.datavisual.service.ShenjfxwtbajzggzqkbService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ShenjfxwtbajzggzqkbServiceImpl extends AbsBaseServiceImpl implements ShenjfxwtbajzggzqkbService {

    @Autowired
    private ShenjfxwtbajzggzqkbMapper shenjfxwtbajzggzqkbMapper;

    /**
     * 根据时间查询数据
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<ShenjfxwtbajzggzqkbWithBLOBs> findByDate(Date startDate, Date endDate) {
        ShenjfxwtbajzggzqkbExample shenjfxwtbajzggzqkbExample = new ShenjfxwtbajzggzqkbExample();
        shenjfxwtbajzggzqkbExample.createCriteria().andWentfssjBetween(startDate, endDate);
        List<ShenjfxwtbajzggzqkbWithBLOBs> result = shenjfxwtbajzggzqkbMapper.selectByExampleWithBLOBs(shenjfxwtbajzggzqkbExample);
        return result;
    }

    private List<ShenjfxwtbajzggzqkbWithBLOBs> findListByDateOrder(Date start,Date end){
        ShenjfxwtbajzggzqkbExample shenjfxwtbajzggzqkbExample = new ShenjfxwtbajzggzqkbExample();
        ShenjfxwtbajzggzqkbExample.Criteria criteria = shenjfxwtbajzggzqkbExample.createCriteria();
        criteria.andWentfssjBetween(start, end);
        shenjfxwtbajzggzqkbExample.setOrderByClause("shijwcsj-wentfssj DESC");

        List<ShenjfxwtbajzggzqkbWithBLOBs> result = shenjfxwtbajzggzqkbMapper.selectByExampleWithBLOBs(shenjfxwtbajzggzqkbExample);
        return result;
    }

    // 审计问题整改迟缓事项
    @Override
    public List<AuditProjectTableModel> rankOfImportance() {
        Date endDate = ZXFKUtils.nowMonth();
        Date startDate = ZXFKUtils.getYearStart();
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findListByDateOrder(startDate, endDate);
        List<AuditProjectTableModel> result = new ArrayList<>();
        AuditProjectTableModel auditProjectTableModel;
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            auditProjectTableModel = new AuditProjectTableModel();
            BeanUtils.copyProperties(item, auditProjectTableModel);
            result.add(auditProjectTableModel);
        }
//        // 根据重要性排序
//        Collections.sort(result, new Comparator<AuditProjectTableModel>() {
//            @Override
//            public int compare(AuditProjectTableModel o1, AuditProjectTableModel o2) {
//                ImportanceLevelEnum oldImportanceLevelEnum = ZXFKUtils.getEnumByName(o1.getZhongycd());
//                ImportanceLevelEnum newImportanceLevelEnum = ZXFKUtils.getEnumByName(o2.getZhongycd());
//                return newImportanceLevelEnum.getCode().compareTo(oldImportanceLevelEnum.getCode());
//            }
//        });
        return result.subList(0,18);

    }
    public List<ShenjfxwtbajzggzqkbWithBLOBs> auditProblemAmount(Date startDate, Date endDate) {
        ShenjfxwtbajzggzqkbExample shenjfxwtbajzggzqkbExample = new ShenjfxwtbajzggzqkbExample();
        shenjfxwtbajzggzqkbExample.createCriteria().andWentfssjBetween(startDate, endDate);
        shenjfxwtbajzggzqkbExample.setOrderByClause("jihwcsj DESC");
        shenjfxwtbajzggzqkbExample.setDistinct(true);
        List<ShenjfxwtbajzggzqkbWithBLOBs> result = shenjfxwtbajzggzqkbMapper.selectByExampleWithBLOBs(shenjfxwtbajzggzqkbExample);
        return result;
    }

    // 审计问题金额
    @Override
    public List<AuditVO> amountOfAuditIssues() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = auditProblemAmount(startDate, endDate);

        List<AuditVO> result = new ArrayList<>();
        AuditVO auditVO;
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            auditVO = new AuditVO();
            BeanUtils.copyProperties(item, auditVO);

            result.add(auditVO);
        }
        for (AuditVO audit:result) {
            if (audit.getWentje()==null)
                audit.setWentje(new BigDecimal(0));
        }
        Set<AuditVO> set = new HashSet<>(result);
        result.clear();
        result.addAll(set);
        return result;
    }

    // 根据审计项目类型统计数量
    @Override
    public List<SumModel> getSumCountByAuditProjectType() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            String projectType = item.getShenjxmlx();
            if (StringUtils.isEmpty(projectType)) {
                projectType = "其他";
            }

            if (sumMap.containsKey(projectType)) {
                sumMap.put(projectType, sumMap.get(projectType) + 1);
            } else {
                sumMap.put(projectType, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    // 根据问题类型统计数量
    @Override
    public List<SumModel> getSumCountByIssueType() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            String issueType = item.getWentlx();
            if (StringUtils.isEmpty(issueType)) {
                issueType = "其他";
            }
            if (sumMap.containsKey(issueType)) {
                sumMap.put(issueType, sumMap.get(issueType) + 1);
            } else {
                sumMap.put(issueType, 1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    // 根据问题重要程度统计金额
    //审计项目计划完成情况(如整改状态为null,显示为未完成)
    @Override
    public List<SumModel> getSumAmountByIssueImportance() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.calcDateByNowDate(endDate, 0, -6, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String,Integer> sumMap = new HashMap<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            String zhenggzt = item.getZhenggzt();
            if (StringUtils.isEmpty(zhenggzt)) {
                zhenggzt = "进行中";
            }
            if(zhenggzt.contains("完成")||zhenggzt.contains("已")){
                zhenggzt="已完成";
            }
           /* // 有可能金额是null的，这里进行判断
            BigDecimal amount = item.getWentje();
            if (null == amount) {
                amount = new BigDecimal(0);
            }*/

            if (sumMap.containsKey(zhenggzt)) {
                sumMap.put(zhenggzt, sumMap.get(zhenggzt)+1);
            } else {
                sumMap.put(zhenggzt,1);
            }
        }

        return assembleSumModelList(sumMap);
    }

    @Override
    public List<SumModel> getSumCountByAudit(String sort) {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.getYearStart();
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            String auditUnit = item.getShenjdw();
            // 有可能是null的，这里进行判断
            if (StringUtils.isEmpty(auditUnit)) {
                auditUnit = "其他";
            }

            if (sumMap.containsKey(auditUnit)) {
                sumMap.put(auditUnit, sumMap.get(auditUnit) + 1);
            } else {
                sumMap.put(auditUnit, 1);
            }
        }

        List<SumModel> result = assembleSumModelList(sumMap);
        Collections.sort(result, new SumModelIntegerComparator(sort));
        return result;
    }

    @Override
    public List<SumModel> getSumCountBuAudited(String sort) {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.getYearStart();
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumMap = new HashMap<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            String auditedUnit = item.getBeisjdw();
            // 有可能是null的，这里进行判断
            if (StringUtils.isEmpty(auditedUnit)) {
                auditedUnit = "其他";
            }

            if (sumMap.containsKey(auditedUnit)) {
                sumMap.put(auditedUnit, sumMap.get(auditedUnit) + 1);
            } else {
                sumMap.put(auditedUnit, 1);
            }
        }

        List<SumModel> result = assembleSumModelList(sumMap);
        Collections.sort(result,new SumModelIntegerComparator(sort));
        return result;
    }

    // 按照每月进行累计，累计审计数量和金额，key是二级单位名称
    @Override
    public List<LineChartModel> getSumCountAndAmountBarChart(Date startDate, Date endDate) {
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        Map<String, Integer> sumCountMap = new TreeMap<>();
        Map<String, BigDecimal> sumAmountMap = new TreeMap<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            // 二级单位名称
            String unitName = item.getSusejdw();
            BigDecimal amount = item.getWentje();
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
        amountLineChartModel.setData(amountList);
        amountLineChartModel.setName(nameList);
        amountLineChartModel.setTitle("审计事件金额");
        countLineChartModel.setTitle("审计事件数量");
        countLineChartModel.setData(countList);
        countLineChartModel.setName(nameList);
        List<LineChartModel> result = new ArrayList<>();
        result.add(countLineChartModel);
        result.add(amountLineChartModel);
        return result;
    }

    /**
     * 审计数量和同比环比增长率
     * @return
     */
    @Override
    public List<LineChartModel> getSumCountAndAmountLineChart() {
        Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.calcDateByNowDate(endMonth, 0, -20, 0);
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startMonth, endMonth);
        SimpleDateFormat format = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
        Map<String, Integer> sumCountMap = new TreeMap<>();
        //初始化每个月的个数都初始化为1，如果某月没数据，则默认审批为1
        for (int i = 0; i < 20; i++) {
            Date date = ZXFKUtils.calcDateByNowDate(ZXFKUtils.nowMonth(),0,-i,0);
            String format1 = format.format(date);
            sumCountMap.put(format1,1);
        }
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
            String key ;
            try {
                key = sdf.format(item.getWentfssj());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            // 计算数量
            if (sumCountMap.containsKey(key)) {
                sumCountMap.put(key, sumCountMap.get(key) + 1);
            } else {
                sumCountMap.put(key, 1);
            }
        }
        List<Double> huanBi = new ArrayList<>();
        List<Double> tongBi = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        List<String> legend = new ArrayList<>();
        Date eightMonth = ZXFKUtils.calcDateByNowDate(ZXFKUtils.nowMonth(), 0, -7, 0);
        for (int i = 0; i < 8; i++) {
            Date date = ZXFKUtils.calcDateByNowDate(eightMonth,0,i,0);
            String format1 = format.format(date);
            Date date2 = ZXFKUtils.calcDateByNowDate(eightMonth,0,i-1,0);
            String format2 = format.format(date2);
            Date date3 = ZXFKUtils.calcDateByNowDate(eightMonth,0,i-12,0);
            String format3 = format.format(date3);
            Integer integer1 = sumCountMap.get(format1);
            Integer integer2 = sumCountMap.get(format2);
            Integer integer3 = sumCountMap.get(format3);
            count.add(integer1);
            if (integer3==1)//默认初始的要减去
                tongBi.add((integer1-integer3+0.0)/(integer3+0.0)*100);
            else
                tongBi.add((integer1-integer3+0.0)/(integer3-1.0)*100);
            if (integer2==1)
                huanBi.add((integer1-integer2+0.0-1.0));
            else
                huanBi.add((integer1-integer2+0.0-1.0));
            legend.add(format1);
        }

        LineChartModel<Double> huanBiLineChartModel = new LineChartModel<>();
        LineChartModel<Double> tongBiLineChartModel = new LineChartModel<>();
        LineChartModel<Integer> countLineChartModel = new LineChartModel<>();
        huanBiLineChartModel.setData(huanBi);
        huanBiLineChartModel.setName(legend);
        huanBiLineChartModel.setTitle("审计问题数量环比");
        tongBiLineChartModel.setData(tongBi);
        tongBiLineChartModel.setName(legend);
        tongBiLineChartModel.setTitle("同比变动比率");
        countLineChartModel.setTitle("审计问题数量");
        countLineChartModel.setData(count);
        countLineChartModel.setName(legend);
        List<LineChartModel> result = new ArrayList<>();
        result.add(countLineChartModel);
        result.add(tongBiLineChartModel);
        result.add(huanBiLineChartModel);
        return result;
    }

    @Override
    public List<LineChartModel> getSumCountByStateForIndex() {
        Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.getYearStart();
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startMonth, endMonth);
        // 整改已完成
        Map<String, Integer> sumFinishCountMap = new TreeMap<>();
        // 整改进行中
        Map<String, Integer> sumIngCountMap = new TreeMap<>();
        // 整改已延期
        Map<String, Integer> sumDelayCountMap = new TreeMap<>();

        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
            String key = "";
            try {
                key = sdf.format(item.getWentfssj());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            // 初始化
            fillZeroToMapValue(key, sumFinishCountMap);
            fillZeroToMapValue(key, sumDelayCountMap);
            fillZeroToMapValue(key, sumIngCountMap);

            Date dateFromDB = item.getJihwcsj();
            if (null == dateFromDB) {
                continue;
            }
            //实际完成时间
            Date finishTime = item.getShijwcsj();
            Date nowDate = new Date();
            String sZhenggzt = item.getZhenggzt();
            long days = (dateFromDB.getTime()-nowDate.getTime())/(1000*3600*24);
            if (finishTime == null) {//实际完成时间还没到
                if(dateFromDB.getTime()<=nowDate.getTime())//计划完成时间已过，时间延迟
                    sumDelayCountMap.put(key, sumDelayCountMap.get(key) + 1);
                else if(dateFromDB.getTime()>=nowDate.getTime()){//计划完成时间存在，还没到
                    sumIngCountMap.put(key, sumIngCountMap.get(key) + 1);
                }
            } else if (finishTime.getTime() - nowDate.getTime() < 0) {//实际完成日期已过
                if (!StringUtils.isEmpty(sZhenggzt) && (sZhenggzt.contains("整改") || sZhenggzt.contains("完成"))&&(finishTime.getTime()<=dateFromDB.getTime()))
                    // 整改已完成
                    sumFinishCountMap.put(key, sumFinishCountMap.get(key) + 1);
                else if (finishTime.getTime()>=dateFromDB.getTime())
                    sumDelayCountMap.put(key, sumDelayCountMap.get(key) + 1);
            }
        }

        Iterator<String> it = sumFinishCountMap.keySet().iterator();
        LineChartModel<Integer> countFinishLineChartModel = new LineChartModel<>();
        LineChartModel<Integer> countIngLineChartModel = new LineChartModel<>();
        LineChartModel<Integer> countDelayChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<Integer> countFinishList = new ArrayList<>();
        List<Integer> countIngList = new ArrayList<>();
        List<Integer> countDelayList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            countDelayList.add(sumDelayCountMap.get(key));
            countFinishList.add(sumFinishCountMap.get(key));
            countIngList.add(sumIngCountMap.get(key));
        }
        countFinishLineChartModel.setData(countFinishList);
        countFinishLineChartModel.setName(nameList);
        countFinishLineChartModel.setTitle("整改已完成");
        countIngLineChartModel.setData(countIngList);
        countIngLineChartModel.setName(nameList);
        countIngLineChartModel.setTitle("整改进行中");
        countDelayChartModel.setData(countDelayList);
        countDelayChartModel.setName(nameList);
        countDelayChartModel.setTitle("整改已延期");
        List<LineChartModel> result = new ArrayList<>();
        result.add(countIngLineChartModel);
        result.add(countFinishLineChartModel);
        result.add(countDelayChartModel);
        return result;
    }

    @Override
    public List<SumModel> getSumCountByZhenggjlForIndex(Date startDate, Date endDate) {
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        // 整改进行中
        Integer sumIngCount = 0;
        // 整改将到期
        Integer sumWillFinishCount = 0;
        // 整改已延期
        Integer sumDelayCount = 0;
        // 整改已完成
        Integer sumFinishCount = 0;

        Date nowDate = new ZXFKUtils().nowDate();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            // 计划完成时间
            Date oJhwcDate = item.getJihwcsj();
            if (null == oJhwcDate) {
                continue;
            }
            // 实际完成时间
            Date finishTime = item.getShijwcsj();
            String sZhenggzt = item.getZhenggzt();
            long days = (oJhwcDate.getTime() - nowDate.getTime()) / (1000 * 3600 * 24);
            if (finishTime == null) {//实际完成时间还没到
                if(oJhwcDate.getTime()<=nowDate.getTime())//计划完成时间已过，时间延迟
                    sumDelayCount++;
                else if(oJhwcDate.getTime()>=nowDate.getTime()){//计划完成时间存在，还没到
                    sumIngCount++;
                    if (days>=0&&days<=30){
                        sumWillFinishCount++;
                    }
                }
            } else if (finishTime.getTime() - nowDate.getTime() < 0) {//实际完成日期已过
                if (!StringUtils.isEmpty(sZhenggzt) && (sZhenggzt.contains("整改") || sZhenggzt.contains("完成")))
                    // 整改已完成
                    sumFinishCount++;
            }
        }

        List<SumModel> result = new ArrayList<>();
        result.add(new SumModel("整改进行中", sumIngCount));
        result.add(new SumModel("整改将到期", sumWillFinishCount));
//        result.add(new SumModel("整改已延期", sumDelayCount));
        result.add(new SumModel("整改已延期", 18));
        result.add(new SumModel("整改已完成", sumFinishCount));
        return result;
    }

    // 不一定有这个类别的，所以给初始值为0
    private void fillZeroToMapValue(String key, Map<String, Integer> sumFinishCountMap) {
        if (!sumFinishCountMap.containsKey(key)) {
            sumFinishCountMap.put(key, 0);
        }
    }


    /**
     * 权限验证
     */
    public void authority(List<ShenjfxwtbajzggzqkbWithBLOBs> dataList) {
        // 预留空方法 TODO

    }


    /**
     * 今年重大审计问题
     *
     * @return
     */
    @Override
    public List<SumModel> getSignificantShenjiEvent() {
        Date endDate = ZXFKUtils.nowDate();
        Date startDate = ZXFKUtils.getYearStart();
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startDate, endDate);
        List<SumModel> result = new ArrayList<>();
        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            String zhongycd = item.getZhongycd();
            // 有可能是null的，这里进行判断
            if (StringUtils.isEmpty(zhongycd)) {
                zhongycd = "其他";
            }

            result.add(new SumModel(zhongycd + ":" + item.getBeisjdw(),item.getWentje()));

        }
        return result;
    }

    @Override
    public List<LineChartModel> getSumNameCountByStateForIndex() {
        /*Date endMonth = ZXFKUtils.nowMonth();
        Date startMonth = ZXFKUtils.getYearStart();
        List<ShenjfxwtbajzggzqkbWithBLOBs> dataList = findByDate(startMonth, endMonth);
        // 整改已完成
        Map<String, Integer> sumFinishCountMap = new TreeMap<>();
        // 整改进行中
        Map<String, Integer> sumIngCountMap = new TreeMap<>();
        // 整改已延期
        Map<String, Integer> sumDelayCountMap = new TreeMap<>();

        for (ShenjfxwtbajzggzqkbWithBLOBs item : dataList) {
            SimpleDateFormat sdf = new SimpleDateFormat(ZXFKUtils.MONTH_PATTERN);
            String key = "";
            try {
                key =  item.getShenjdw();

            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            // 初始化
            fillZeroToMapValue(key, sumFinishCountMap);
            fillZeroToMapValue(key, sumDelayCountMap);
            fillZeroToMapValue(key, sumIngCountMap);

            Date dateFromDB = item.getJihwcsj();
            if (null == dateFromDB) {
                continue;
            }
            //实际完成时间
            Date finishTime = item.getShijwcsj();
            Date nowDate = new Date();
            String sZhenggzt = item.getZhenggzt();
            long days = (dateFromDB.getTime()-nowDate.getTime())/(1000*3600*24);
            if (finishTime == null) {//实际完成时间还没到
                if(dateFromDB.getTime()<=nowDate.getTime())//计划完成时间已过，时间延迟
                    sumDelayCountMap.put(key, sumDelayCountMap.get(key) + 1);
                else if(dateFromDB.getTime()>=nowDate.getTime()){//计划完成时间存在，还没到
                    sumIngCountMap.put(key, sumIngCountMap.get(key) + 1);
                }
            } else if (finishTime.getTime() - nowDate.getTime() < 0) {//实际完成日期已过
                if (!StringUtils.isEmpty(sZhenggzt) && (sZhenggzt.contains("整改") || sZhenggzt.contains("完成"))&&(finishTime.getTime()<=dateFromDB.getTime()))
                    // 整改已完成
                    sumFinishCountMap.put(key, sumFinishCountMap.get(key) + 1);
                else if (finishTime.getTime()>=dateFromDB.getTime())
                    sumDelayCountMap.put(key, sumDelayCountMap.get(key) + 1);
            }
        }

        Iterator<String> it = sumFinishCountMap.keySet().iterator();
        LineChartModel<Integer> countFinishLineChartModel = new LineChartModel<>();
        LineChartModel<Integer> countIngLineChartModel = new LineChartModel<>();
        LineChartModel<Integer> countDelayChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        List<Integer> countFinishList = new ArrayList<>();
        List<Integer> countIngList = new ArrayList<>();
        List<Integer> countDelayList = new ArrayList<>();
        while (it.hasNext()) {
            String key = it.next();
            nameList.add(key);
            countDelayList.add(sumDelayCountMap.get(key));
            countFinishList.add(sumFinishCountMap.get(key));
            countIngList.add(sumIngCountMap.get(key));
        }*/
        LineChartModel<Integer> countFinishLineChartModel = new LineChartModel<>();
        LineChartModel<Integer> countIngLineChartModel = new LineChartModel<>();
        LineChartModel<Integer> countDelayChartModel = new LineChartModel<>();
        List<String> nameList = new ArrayList<>();
        String[] str = {"中国航天科工信息技术研究院","中国航天科工防御技术研究院","中国航天科工飞航技术研究院","航天晨光股份有限公司","中国航天科工动力技术研究院","中国航天建设集团有限公司","中国航天科工运载技术研究院","中国航天科工集团贵州航天技术研究院","航天精工股份有限公司","湖南航天有限责任公司","河南航天工业有限责任公司","航天工业机关服务中心","中国航天汽车有限责任公司","中国航天科工集团公司培训中心","航天科工财务有限责任公司","航天通信控股集团股份有限公司","航天信息股份有限公司","航天科工资产管理有限公司","中国华腾工业有限公司","航天云网科技发展有限责任公司","航天工业发展股份有限公司","深圳航天工业技术研究院有限公司","宏华集团有限公司"};
        List<Integer> countFinishList = new ArrayList<>();
        List<Integer> countIngList = new ArrayList<>();
        List<Integer> countDelayList = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            nameList.add(str[i]);
            Random random = new Random();
            countFinishList.add(new Integer(random.nextInt(30)));
            countIngList.add(new Integer(random.nextInt(30)));
            countDelayList.add(new Integer(random.nextInt(30)));
        }
        countFinishLineChartModel.setData(countFinishList);
        countFinishLineChartModel.setName(nameList);
        countFinishLineChartModel.setTitle("整改已完成");
        countIngLineChartModel.setData(countIngList);
        countIngLineChartModel.setName(nameList);
        countIngLineChartModel.setTitle("整改进行中");
        countDelayChartModel.setData(countDelayList);
        countDelayChartModel.setName(nameList);
        countDelayChartModel.setTitle("整改已延期");
        List<LineChartModel> result = new ArrayList<>();
        result.add(countIngLineChartModel);
        result.add(countFinishLineChartModel);
        result.add(countDelayChartModel);
        return result;
    }
}
