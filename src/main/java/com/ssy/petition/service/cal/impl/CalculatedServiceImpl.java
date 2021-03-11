package com.ssy.petition.service.cal.impl;

import com.github.pagehelper.PageHelper;
import com.ssy.petition.dao.cal.CalculatedMapper;
import com.ssy.petition.dto.cal.params.CalculatedParams;
import com.ssy.petition.dto.cal.result.CalculatedResult;
import com.ssy.petition.dto.cal.result.CalculatedStringResult;
import com.ssy.petition.entity.cal.*;
import com.ssy.petition.provider.CalculateProvider;
import com.ssy.petition.service.cal.CalculatedService;
import com.ssy.petition.util.CollectionUtils;
import com.ssy.petition.util.DateUtils;
import com.ssy.petition.util.EntityUtils;
import com.ssy.petition.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    private final static Pattern INT_PATTERN = Pattern.compile("[^0-9]");

    private final static String DAY_STR = "天";

    private final static String HOUR_STR = "小时";

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
        CalculatedStringResult stringResult = formatCalculated(result);
        stringResult.setInsertByName(result.getInsertByName());
        return stringResult;
    }

    @Override
    public List<CalculatedStringResult> formatCalculated(List<Calculated> list) {
        List<CalculatedStringResult> results = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return results;
        }
        return list.stream().map(this::formatCalculated).collect(Collectors.toList());
    }

    private CalculatedStringResult formatCalculated(Calculated result) {
        CalculatedStringResult stringResult = new CalculatedStringResult();
        stringResult.setBatchNumber(result.getBatchNumber());
        stringResult.setSort(result.getSort());
        stringResult.setCompanyName(result.getCompanyName());
        stringResult.setShortName(result.getShortName());
        stringResult.setInformRate(formatRate(result.getInformRate()));
        stringResult.setFinishRate(formatRate(result.getFinishRate()));
        String initialAcceptCycle = transString(result.getInitialAcceptCycle());
        String initialReplyCycle = transString(result.getInitialReplyCycle());
        if (StringUtils.isEmpty(initialAcceptCycle)) {
            initialAcceptCycle = "-";
        }
        if (StringUtils.isEmpty(initialReplyCycle)) {
            initialReplyCycle = "-";
        }
        stringResult.setInitialAcceptCycle(initialAcceptCycle);
        stringResult.setInitialReplyCycle(initialReplyCycle);
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

    @Override
    public FirstContactTime formatTime(FirstContact firstContact) {
        FirstContactTime firstContactTime = new FirstContactTime();
        firstContactTime.setCompanyName(firstContact.getCompanyName());
        firstContactTime.setCount(firstContact.getCount());
        firstContactTime.setTransferTime(transString(firstContact.getTransferTime()));
        firstContactTime.setAcceptTime(transString(firstContact.getAcceptTime()));
        firstContactTime.setReplyTime(transString(firstContact.getReplyTime()));
        firstContactTime.setFinishTime(transString(firstContact.getFinishTime()));
        return firstContactTime;
    }

    @Override
    public FirstContact formatInt(FirstContactTime firstContactTime) {
        FirstContact firstContact = new FirstContact();
        firstContact.setCompanyName(firstContactTime.getCompanyName());
        firstContact.setCount(firstContactTime.getCount());
        firstContact.setTransferTime(transBigDecimal(firstContactTime.getTransferTime()));
        firstContact.setAcceptTime(transBigDecimal(firstContactTime.getAcceptTime()));
        firstContact.setReplyTime(transBigDecimal(firstContactTime.getReplyTime()));
        firstContact.setFinishTime(transBigDecimal(firstContactTime.getFinishTime()));
        return firstContact;
    }

    private BigDecimal transBigDecimal(String time) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        int hourCount = 0;
        if (time.contains(DAY_STR)) {
            String[] dayArray = time.split(DAY_STR);
            Matcher dayMatcher = INT_PATTERN.matcher(dayArray[0]);
            String dayIntStr = dayMatcher.replaceAll("").trim();
            int day = Integer.parseInt(dayIntStr);
            hourCount = day * 24;
            if (dayArray.length > 1) {
                time = dayArray[1];
            }
        }
        if (time.contains(HOUR_STR)) {
            String[] hourArray = time.split(HOUR_STR);
            Matcher hourMatcher = INT_PATTERN.matcher(hourArray[0]);
            String hourIntStr = hourMatcher.replaceAll("").trim();
            int hour = Integer.parseInt(hourIntStr);
            hourCount = hourCount + hour;
        }
        return new BigDecimal(hourCount);
    }

    private String transString(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        }
        int hourAllCount = bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        String result;
        if (hourAllCount >= 24) {
            int dayCount = hourAllCount / 24;
            int hourCount = hourAllCount - (dayCount * 24);
            result = dayCount + DAY_STR;
            if (hourCount > 0) {
                result = result + hourCount + HOUR_STR;
            }
        } else {
            result = hourAllCount + HOUR_STR;
        }
        return result;
    }

    @Override
    public List<FirstContactTime> formatTime(List<FirstContact> firstContact) {
        List<FirstContactTime> results = new ArrayList<>();
        if (CollectionUtils.isEmpty(firstContact)) {
            return results;
        }
        return firstContact.stream().map(this::formatTime).collect(Collectors.toList());
    }

    @Override
    public List<FirstContact> formatInt(List<FirstContactTime> firstContactTime) {
        List<FirstContact> results = new ArrayList<>();
        if (CollectionUtils.isEmpty(firstContactTime)) {
            return results;
        }
        return firstContactTime.stream().map(this::formatInt).collect(Collectors.toList());
    }
}
