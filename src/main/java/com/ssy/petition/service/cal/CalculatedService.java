package com.ssy.petition.service.cal;

import com.ssy.petition.dto.cal.params.CalculatedParams;
import com.ssy.petition.dto.cal.result.CalculatedResult;
import com.ssy.petition.dto.cal.result.CalculatedStringResult;
import com.ssy.petition.entity.cal.Calculated;
import com.ssy.petition.entity.cal.FirstContact;
import com.ssy.petition.entity.cal.FirstContactTime;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/08/26
 */
public interface CalculatedService {

    List<CalculatedResult> list(@Param("queryParam") CalculatedParams params, Integer pageNum, Integer pageSize);

    int save();

    List<String> batchNumberList(String batchNumber);

    List<CalculatedStringResult> format(List<CalculatedResult> list);

    List<CalculatedStringResult> formatCalculated(List<Calculated> list);

    CalculatedStringResult format(CalculatedResult result);

    FirstContactTime formatTime(FirstContact firstContact);

    FirstContact formatInt(FirstContactTime firstContactTime);

    List<FirstContactTime> formatTime(List<FirstContact> firstContact);

    List<FirstContact> formatInt(List<FirstContactTime> firstContactTime);

}
