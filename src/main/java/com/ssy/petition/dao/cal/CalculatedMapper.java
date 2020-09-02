package com.ssy.petition.dao.cal;


import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.dto.cal.params.CalculatedParams;
import com.ssy.petition.dto.cal.result.CalculatedResult;
import com.ssy.petition.entity.cal.Calculated;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/08/27
 */
@Repository
public interface CalculatedMapper extends BaseMapper<Calculated> {

    List<CalculatedResult> getList(@Param("queryParam")CalculatedParams params);

    List<String> getBatchNumberList(@Param("batchNumber")String batchNumber);

}
