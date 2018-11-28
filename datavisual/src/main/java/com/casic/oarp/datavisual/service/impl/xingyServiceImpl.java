package com.casic.oarp.datavisual.service.impl;

import com.casic.oarp.datavisual.Utils.EmptyUtils;
import com.casic.oarp.datavisual.mapper.ScCreditFivescoreMapper;
import com.casic.oarp.datavisual.mapper.ScCreditQuantMapper;
import com.casic.oarp.datavisual.mapper.ScwarnsExtendMapper;
import com.casic.oarp.datavisual.mapper.ScwarnsMapper;
import com.casic.oarp.datavisual.model.zxfk.SumModel;
import com.casic.oarp.datavisual.po.*;
import com.casic.oarp.datavisual.service.xingyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ASUS on 2018/11/19.
 */
@Service
public class xingyServiceImpl extends AbsBaseServiceImpl implements xingyService {
    @Autowired
    private ScCreditFivescoreMapper fiveMapper;//五维分值
    @Autowired
    private ScCreditQuantMapper quantMapper;//信用分值表

    @Autowired
    private ScwarnsMapper warnMapper;//预警消息
    @Autowired
    private ScwarnsExtendMapper wanExtendMapper;

    @Override
    //按照升序排列
    public List<ScCreditQuantVo> getTable() {
        ScCreditQuantExample QuantExample=new ScCreditQuantExample();
        List<ScCreditQuantVo> listvo=new ArrayList<>();
        QuantExample.setOrderByClause("creditscore Desc  limit 0,10 ");
        List<ScCreditQuant> result=quantMapper.selectByExample(QuantExample);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (ScCreditQuant quant:result) {
            ScCreditQuantVo vo=new ScCreditQuantVo();
            ScCreditFivescoreExample fiveExample=new ScCreditFivescoreExample();
            fiveExample.createCriteria().andOrginidEqualTo(quant.getOrginid());
            List<ScCreditFivescore> fiveList=fiveMapper.selectByExample(fiveExample);//查询出来对应的五维评分
            BeanUtils.copyProperties(quant,vo);//属性赋值
            BeanUtils.copyProperties(fiveList.get(0),vo);
            vo.setAssesstime(format.format(quant.getAssesstime()));
            listvo.add(vo);
        }
        return listvo;
    }

    @Override
    public QuantVo geterTable() {
        ScCreditQuantExample QuantExample=new ScCreditQuantExample();
        QuantExample.setOrderByClause("'creditscore' ASC" );
        QuantExample.createCriteria().andParentidEqualTo("3");
        List<ScCreditQuant> result=quantMapper.selectByExample(QuantExample);
        QuantVo vo=new QuantVo();
        List<String> name=new ArrayList<>();
        List<BigDecimal> Score=new ArrayList<>();
        for (ScCreditQuant quant:result) {
                name.add(quant.getOrgname());
                Score.add(quant.getCreditscore());
        }
        vo.setName(name);
        vo.setScore(Score);
        return vo;
    }


    @Override
    public List<warnsVo> getWarns() {
        List<warnsVo> list= wanExtendMapper.selectAll();
        return list;
    }

    @Override
    public List<SumModel> getpie() {
        ScCreditQuantExample QuantExample=new ScCreditQuantExample();
        Map<String,Integer> map=new HashMap();
        List<ScCreditQuant> result=quantMapper.selectByExample(QuantExample);
        for (ScCreditQuant quant: result) {
            if (map.containsKey(quant.getCreditlevel())){
                map.put(quant.getCreditlevel(),map.get(quant.getCreditlevel())+1);
            }else{
                map.put(quant.getCreditlevel(),1);
            }
        }
        return assembleSumModelList(map);
    }

    @Override
    public List<SumModel> getpieWarn() {
        ScCreditQuantExample QuantExample = new ScCreditQuantExample();
        Map<String, Integer> map = new HashMap<>();
        List<ScCreditQuant> result = quantMapper.selectByExample(QuantExample);
//        BigDecimal first=new BigDecimal(600);
//        BigDecimal second=new BigDecimal(670);
//        BigDecimal third=new BigDecimal(740);
//        BigDecimal fouth=new BigDecimal(810);
//        BigDecimal five=new BigDecimal(880);
//        BigDecimal six=new BigDecimal(950);
        map.put("一级", 0);
        map.put("二级", 0);
        map.put("三级", 0);
        map.put("四级", 0);
        map.put("五级", 0);
        for (ScCreditQuant quant : result) {
            Integer score = quant.getCreditscore().intValue();
            if (score >= 600 && score <= 670) {
                map.put("一级", map.get("一级") + 1);
            } else if (score > 670 && score <= 740) {
                map.put("二级", map.get("二级") + 1);
            } else if (score > 740 && score <= 810) {
                map.put("三级", map.get("三级") + 1);
            } else if (score > 810 && score <= 880) {
                map.put("四级", map.get("四级") + 1);
            } else if (score > 880 && score <= 950) {
                map.put("五级", map.get("五级") + 1);
            }

        }
        return assembleSumModelList(map);
    }

    @Override
    public List<SumModel> piewarn() {
        ScwarnsExample warnsExample=new ScwarnsExample();
        Map<String,Integer> map=new HashMap();
        List<Scwarns> result=warnMapper.selectByExample(warnsExample);
        for (Scwarns warns: result) {
            if (warns.getWarnname().equals("案件流程"))
                warns.setWarnname("案件流程变更");
            if (map.containsKey(warns.getWarnname())){
                map.put(warns.getWarnname(),map.get(warns.getWarnname())+1);
            }else{
                map.put(warns.getWarnname(),1);
            }
        }
        return assembleSumModelList(map);
    }

}
