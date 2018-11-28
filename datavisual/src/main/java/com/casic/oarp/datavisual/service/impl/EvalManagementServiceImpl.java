package com.casic.oarp.datavisual.service.impl;

import com.casic.oarp.datavisual.common.ZXFKUtils;
import com.casic.oarp.datavisual.mapper.EvalImperfectionExtendMapper;
import com.casic.oarp.datavisual.mapper.EvalManagementExtendMapper;
import com.casic.oarp.datavisual.mapper.EvalManagementMapper;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.po.EvalManagement;
import com.casic.oarp.datavisual.po.EvalManagementExample;
import com.casic.oarp.datavisual.po.EvalManagementVO;
import com.casic.oarp.datavisual.service.EvalManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 董胜得 on 2018/9/27.
 */
@Service
public class EvalManagementServiceImpl extends AbsBaseServiceImpl implements EvalManagementService {

    @Autowired
    private EvalManagementMapper evalManagementMapper;
    @Autowired
    private EvalManagementExtendMapper evalManagementExtendMapper;
    @Autowired
    private EvalImperfectionExtendMapper evalImperfectionExtendMapper;

    /**
     * 查询成熟度
     *
     * @param startDate
     * @param endDate
     * @return
     */
    private List<EvalManagement> findByDate(Date startDate, Date endDate) {
        EvalManagementExample evalManagementExample = new EvalManagementExample();
        evalManagementExample.createCriteria().andCreatedateLike("%2018%");
        List<EvalManagement> evalManagements = evalManagementMapper.selectByExample(evalManagementExample);
        return evalManagements;
    }

    /**
     * 获取公司运营的成熟度
     *
     * @return
     */
    @Override
    public List<SumModel> getMaturity() {
        Date endMonth = ZXFKUtils.nowDate();
        Date startMonth = ZXFKUtils.getYearStart();
        List<EvalManagement> byDate = findByDate(startMonth, endMonth);
        //初始级
        Integer startClass = 0;
        //基础级
        Integer baseClass = 0;
        //受控级
        Integer innerControlClass = 0;
        //规范级
        Integer normClass = 0;
        //成熟级
        Integer matureClass = 0;
        for (EvalManagement evalManagement : byDate) {
            //已经评分机构的数量
            int count = 0;
            Double score = 0.0;
            if (evalManagement.getScore() != null)
                score = Double.valueOf(evalManagement.getScore());
            Double score1 = 0.0;
            if (evalManagement.getScore1() != null)
                score1 = Double.valueOf(evalManagement.getScore1());
            Double score2 = 0.0;
            if (evalManagement.getScore2() != null)
                score2 = Double.valueOf(evalManagement.getScore2());
            Double score3 = 0.0;
            if (evalManagement.getScore3() != null)
                score3 = Double.valueOf(evalManagement.getScore3());
            Double score4 = 0.0;
            if (evalManagement.getScore4() != null)
                score4 = Double.valueOf(evalManagement.getScore4());
            if (score != null && score != 0)
                count++;
            if (score1 != null && score1 != 0)
                count++;
            if (score2 != null && score2 != 0)
                count++;
            if (score3 != null && score3 != 0)
                count++;
            if (score4 != null && score4 != 0)
                count++;
            if (count == 0)
                continue;
            //平均分
            Double averageScore = 0.0;
            averageScore = (score + score1 + score2 + score3 + score4) / count;
            if (averageScore >= 0 && averageScore < 240) {
                startClass++;
            } else if (averageScore >= 240 && averageScore < 280) {
                baseClass++;
            } else if (averageScore >= 280 && averageScore < 320) {
                innerControlClass++;
            } else if (averageScore >= 320 && averageScore < 360) {
                normClass++;
            } else if (averageScore >= 360 && averageScore < 500) {
                matureClass++;
            } else {
                continue;
            }
        }
        SumModel<Integer> startClassModel = new SumModel<>("初始级", startClass);
        SumModel<Integer> baseClassModel = new SumModel<>("基础级", baseClass);
        SumModel<Integer> InnerControlClassModel = new SumModel<>("受控级", innerControlClass);
        SumModel<Integer> normClassModel = new SumModel<>("规范级", normClass);
        SumModel<Integer> matureClassModel = new SumModel<>("成熟级", matureClass);
        List<SumModel> result = new ArrayList<>();
        result.add(startClassModel);
        result.add(baseClassModel);
        result.add(InnerControlClassModel);
        result.add(normClassModel);
        result.add(matureClassModel);
        return result;
    }

    @Override
    public List<EvalManagementVO> selectTopTen() {
        List<EvalManagementVO> evalManagementVOS = evalManagementExtendMapper.selectTopTen();
        for (EvalManagementVO item : evalManagementVOS) {
            String averageScore = item.getScore();
            if (averageScore == null)
                continue;
            Float valueOf = Float.valueOf(averageScore);
            if (valueOf >= 0 && valueOf < 240) {
                item.setCategory("初始级");
            } else if (valueOf >= 240 && valueOf < 280) {
                item.setCategory("基础级");
            } else if (valueOf >= 280 && valueOf < 320) {
                item.setCategory("受控级");
            } else if (valueOf >= 320 && valueOf < 360) {
                item.setCategory("规范级");
            } else if (valueOf >= 360 && valueOf < 500) {
                item.setCategory("成熟级");
            } else {
                item.setCategory(null);
            }
        }
        return evalManagementVOS;
    }

    @Override
    public List<EvalManagementVO> selectAllCom() {
        List<EvalManagementVO> evalManagementVOS = evalManagementExtendMapper.selectAllCom();
        return evalManagementVOS;
    }

    @Override
    public List<SumModel> selectMaturityCategory(String sort) {
        List<EvalManagementVO> evalManagementVOS = evalManagementExtendMapper.selectAllCom();
        //初始级
        Integer startClass = 0;
        //基础级
        Integer baseClass = 0;
        //受控级
        Integer innerControlClass = 0;
        //规范级
        Integer normClass = 0;
        //成熟级
        Integer matureClass = 0;
        for (EvalManagementVO evalManagementVO : evalManagementVOS) {
            String score = evalManagementVO.getScore();
            if (score == null)
                continue;
            Float aFloat = Float.valueOf(score);
            if (aFloat >= 0 && aFloat < 240) {
                startClass++;
            } else if (aFloat >= 240 && aFloat < 280) {
                baseClass++;
            } else if (aFloat >= 280 && aFloat < 320) {
                innerControlClass++;
            } else if (aFloat >= 320 && aFloat < 360) {
                normClass++;
            } else if (aFloat >= 360 && aFloat < 500) {
                matureClass++;
            } else {
                continue;
            }
        }
        Map<String, Integer> category = new HashMap<>();
        category.put("初始级", startClass);
        category.put("基础级", baseClass);
        category.put("受控级", innerControlClass);
        category.put("规范级", normClass);
        category.put("成熟级", matureClass);
//        Comparator<Map.Entry<String,Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                return o1.getValue()-o2.getValue();
//            }
//        };
        List<SumModel> list = assembleSumModelList(category);
        Collections.sort(list, new SumModelIntegerComparator(sort));

        return list;
    }

    @Override
    public List<SumModel> selectDistributeOfDefect() {
        List<SumModel> sumModels = evalImperfectionExtendMapper.selectDistribute();
        return sumModels;
    }

    @Override
    public List<SumModel> selectDistributeImport() {
        List<SumModel> sumModels = evalImperfectionExtendMapper.selectDistributeImport();
        return sumModels;
    }

    @Override
    public List<SumModel> selectImportDefect() {
        List<String> strings = evalImperfectionExtendMapper.selectImportDefect();
        List<SumModel> sum = new ArrayList<>();
        for (String str : strings) {
            SumModel s = new SumModel();
            s.setName(str);
            s.setValue(0);
            sum.add(s);
        }
        return sum;
    }
}
