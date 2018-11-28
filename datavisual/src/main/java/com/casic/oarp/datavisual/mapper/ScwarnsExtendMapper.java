package com.casic.oarp.datavisual.mapper;

import com.casic.oarp.datavisual.po.Scwarns;
import com.casic.oarp.datavisual.po.ScwarnsExample;
import com.casic.oarp.datavisual.po.warnsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ScwarnsExtendMapper {
   List<warnsVo> selectAll();
}