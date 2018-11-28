package com.casic.oarp.datavisual.mapper;

import com.casic.oarp.datavisual.po.SsajqkbgbVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface SsajqkbgbExtendMapper extends SsajqkbgbMapper {
    /**
     * 法律案件前12个月企业涉案单位前三名未结案的趋势变化
     * @param date
     * @return
     */
    List<String> findTopThreeData(Date date);
    /**
     * 法律案件前12个月企业涉案单位前三名未结案的趋势变化
     * @param date
     * @return
     */
    List<SsajqkbgbVO> findTopTenData(Date date);
}
