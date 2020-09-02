package com.ssy.petition.service.cal.impl;

import com.github.pagehelper.PageHelper;
import com.ssy.petition.dao.cal.CalculatedMapper;
import com.ssy.petition.dto.cal.params.CalculatedParams;
import com.ssy.petition.dto.cal.result.CalculatedResult;
import com.ssy.petition.dto.cal.result.CalculatedStringResult;
import com.ssy.petition.entity.cal.Calculated;
import com.ssy.petition.entity.cal.CalculatedStatusEnum;
import com.ssy.petition.entity.cal.Calculating;
import com.ssy.petition.provider.CalculateProvider;
import com.ssy.petition.service.cal.CalculatedService;
import com.ssy.petition.util.CollectionUtils;
import com.ssy.petition.util.DateUtils;
import com.ssy.petition.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: jcm
 * @date: 2020/08/26
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CalculatedServiceImpl implements CalculatedService {

    private final CalculatedMapper calculatedMapper;

    private final static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00%");

    public CalculatedServiceImpl(CalculatedMapper calculatedMapper) {
        this.calculatedMapper = calculatedMapper;
    }

    @Override
    public List<CalculatedResult> list(CalculatedParams params, Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        return calculatedMapper.getList(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save() {
        Calculating calculating = CalculateProvider.getCalculating();
        if (calculating != null) {
            if (CalculatedStatusEnum.CALCULATED.equals(calculating.getStatus())) {
                if (calculating.isSaveStatus()) {
                    throw new RuntimeException("已保存，请重新导入计算！");
                }
                List<Calculated> calculatedList = calculating.getCalculatedList();
                for (Calculated calculated : calculatedList) {
                    EntityUtils.initInsertEntity(calculated);
                    calculated.setBatchNumber(DateUtils.generateBatchNumber());
                    calculatedMapper.insertSelective(calculated);
                }
            } else {
                throw new RuntimeException("未计算，请先计算！");
            }
        }
        calculating.setSaveStatus(true);
        return 0;
    }

    @Override
    public List<String> batchNumberList(String batchNumber) {
        return calculatedMapper.getBatchNumberList(batchNumber);
    }

    @Override
    public List<CalculatedStringResult> format(List<CalculatedResult> list) {
        List<CalculatedStringResult> results = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return results;
        }
        return list.stream().map(this::format).collect(Collectors.toList());
    }

    @Override
    public CalculatedStringResult format(CalculatedResult result) {
        CalculatedStringResult stringResult = format(result);
        stringResult.setInsertByName(result.getInsertByName());
        return stringResult;
    }

    @Override
    public List<CalculatedStringResult> formatCalculated(List<Calculated> list) {
        List<CalculatedStringResult> results = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return results;
        }
        return list.stream().map(this::format).collect(Collectors.toList());
    }

    private CalculatedStringResult format(Calculated result) {
        CalculatedStringResult stringResult = new CalculatedStringResult();
        stringResult.setBatchNumber(result.getBatchNumber());
        stringResult.setSort(result.getSort());
        stringResult.setCompanyName(result.getCompanyName());
        stringResult.setInformRate(formatRate(result.getInformRate()));
        stringResult.setFinishRate(formatRate(result.getFinishRate()));
        stringResult.setInitialAcceptCycle(formatRate(result.getInitialAcceptCycle()));
        stringResult.setInitialReplyCycle(formatRate(result.getInitialReplyCycle()));
        stringResult.setInitialContactRate(formatRate(result.getInitialContactRate()));
        stringResult.setRepeatPetitionRate(formatRate(result.getRepeatPetitionRate()));
        stringResult.setPublicReplyRate(formatRate(result.getPublicReplyRate()));
        stringResult.setSatisfactionRate(formatRate(result.getSatisfactionRate()));
        stringResult.setRightRate(formatRate(result.getRightRate()));
        return stringResult;
    }

    private String formatRate(BigDecimal rate) {
        if (rate == null) {
            return "-";
        }
        return DECIMAL_FORMAT.format(rate);
    }
}
