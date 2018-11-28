package com.casic.oarp.datavisual.service.impl;

import com.casic.oarp.datavisual.mapper.HtywOrgizationMapper;
import com.casic.oarp.datavisual.po.HtywOrgization;
import com.casic.oarp.datavisual.po.HtywOrgizationExample;
import com.casic.oarp.datavisual.service.HtywOrgizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HtywOrgizationServiceImpl implements HtywOrgizationService {

    @Autowired
    private HtywOrgizationMapper htywOrgizationMapper;
    @Override
    public Integer selectCount() {
//        HtywOrgizationExample example = new HtywOrgizationExample();
//        example.setDistinct(true);
//        List<HtywOrgization> htywOrgizations = htywOrgizationMapper.selectByExample(example);
//
//        return htywOrgizations.size();
        return new Integer(495);
    }
}
