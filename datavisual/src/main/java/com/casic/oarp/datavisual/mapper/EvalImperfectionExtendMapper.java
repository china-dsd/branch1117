package com.casic.oarp.datavisual.mapper;

import com.casic.oarp.datavisual.model.zxfk.SumModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface EvalImperfectionExtendMapper extends EvalManagementMapper {
    //缺陷类型分布
    List<SumModel> selectDistribute();
    //重要缺陷分布
    List<SumModel> selectDistributeImport();

    //重大或者重要缺陷
    List<String> selectImportDefect();
}