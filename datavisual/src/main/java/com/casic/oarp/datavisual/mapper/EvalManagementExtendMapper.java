package com.casic.oarp.datavisual.mapper;

import com.casic.oarp.datavisual.po.EvalManagementVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface EvalManagementExtendMapper extends EvalManagementMapper{
    List<EvalManagementVO> selectTopTen();

    List<EvalManagementVO> selectAllCom();
}
