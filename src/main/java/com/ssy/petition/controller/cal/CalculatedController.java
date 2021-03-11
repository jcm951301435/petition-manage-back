package com.ssy.petition.controller.cal;

import com.ssy.petition.common.CommonPage;
import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.cal.params.CalculatedParams;
import com.ssy.petition.dto.cal.result.CalculatedResult;
import com.ssy.petition.dto.cal.result.CalculatedStringResult;
import com.ssy.petition.entity.cal.*;
import com.ssy.petition.provider.CalculateProvider;
import com.ssy.petition.service.cal.CalculatedService;
import com.ssy.petition.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 信访计算
 *
 * @author: jcm
 * @date: 2020/08/26
 */
@Api(tags = "CalculateController", description = "计算信访")
@RestController
@RequestMapping("/calculate")
public class CalculatedController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatedController.class);

    private final CalculatedService service;

    public CalculatedController(CalculatedService service) {
        this.service = service;
    }

    @ApiOperation("列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult list(CalculatedParams params,
                             @RequestParam(value = "pageNum", required = false) Integer pageNum,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<CalculatedResult> list = service.list(params, pageNum, pageSize);
        CommonPage pageCal = CommonPage.restPage(list);
        List<CalculatedStringResult> resultList = service.format(list);
        CommonPage page = CommonPage.restPage(resultList);
        page.setPageNum(pageCal.getPageNum());
        page.setPageSize(pageCal.getPageSize());
        page.setTotal(pageCal.getTotal());
        page.setTotalPage(pageCal.getTotalPage());
        return CommonResult.success(page);
    }

    @ApiOperation("保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResult save() {
        try {
            service.save();
        } catch (Exception e) {
            LOGGER.error("保存失败", e);
            return CommonResult.failed("保存失败，" + e.getMessage());
        }
        return CommonResult.success("保存成功");
    }

    @RequestMapping(value = "/batchNumberList/{batchNumber}", method = RequestMethod.POST)
    public CommonResult batchNumberList(@PathVariable String batchNumber) {
        List<String> list = service.batchNumberList(batchNumber);
        return CommonResult.success(list);
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public CommonResult<String> importExcel(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
        CalculatedTypeEnum calculatedTypeEnum = CalculatedTypeEnum.getByName(type);
        if (calculatedTypeEnum == null) {
            LOGGER.error("找不到此类型, {}", type);
            return CommonResult.failed("导入失败，请联系管理员");
        }
        Class<? extends ParamsEntity> cls = calculatedTypeEnum.getCls();
        try {
            if ("adminFirstContacts".equals(type) || "partyFirstContacts".equals(type)) {
                List<FirstContactTime> timeList = ExcelUtils.generateListFromFile(file, FirstContactTime.class);
                List<? extends ParamsEntity> list = service.formatInt(timeList);
                CalculateProvider.addCalculating(list, file, calculatedTypeEnum);
            } else {
                List<? extends ParamsEntity> list = ExcelUtils.generateListFromFile(file, cls);
                CalculateProvider.addCalculating(list, file, calculatedTypeEnum);
            }
        } catch (Exception e) {
            LOGGER.error("导入失败", e);
            return CommonResult.failed("导入失败，请联系管理员。原因：" + e.getMessage());
        }
        return CommonResult.success("导入成功");
    }
    @RequestMapping(value = "/queryCalculate", method = RequestMethod.POST)
    public CommonResult<Map<String, Object>> queryCalculate() {
        Map<String, Object> result = CalculateProvider.queryCalculate();
        return CommonResult.success(result);
    }

    @RequestMapping(value = "/queryImport", method = RequestMethod.POST)
    public CommonResult<Map<String, Map<String, Object>>> queryImport() {
        Map<String, Map<String, Object>> result = CalculateProvider.queryImport();
        return CommonResult.success(result);
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public CommonResult calculate() {
        Calculating calculating = CalculateProvider.getCalculating();
        if (CalculatedStatusEnum.CALCULATING.equals(calculating.getStatus())) {
            return CommonResult.failed("正在计算中，请刷新页面查看进度");
        }
        try {
            CalculateProvider.calculate(calculating);
        } catch (Exception e) {
            LOGGER.error("计算失败", e);
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success("计算结束");
    }

    @ApiOperation("清空")
    @RequestMapping(value = "/clearCalculate", method = RequestMethod.POST)
    public CommonResult clearCalculate() {
        CalculateProvider.clearCalculating();
        return CommonResult.success("清空成功");
    }

    @RequestMapping(value = "/download/{type}", method = RequestMethod.POST)
    public CommonResult download(@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response) {
        CalculatedTypeEnum calculatedTypeEnum = CalculatedTypeEnum.getByName(type);
        if (calculatedTypeEnum == null) {
            LOGGER.error("找不到此类型, {}", type);
            return CommonResult.failed("导入失败，请联系管理员");
        }
        Calculating calculating = CalculateProvider.getCalculating();
        Map<String, Object> map = CalculateProvider.getFileRecordByType(calculating, calculatedTypeEnum, true);
        Object fileObject = map.get("file");
        Object fileNameObject = map.get("fileName");
        String fileName;
        if (fileObject == null) {
            return CommonResult.failed("找不到此文件，请先上传！");
        }
        if (fileNameObject != null) {
            fileName = (String) fileNameObject;
        } else {
            fileName = "未定义名称";
        }
        MultipartFile file = (MultipartFile) fileObject;
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
        String disposition = "";
        try {
            disposition = "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", disposition);
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        byte[] buffer = new byte[1024];
        try {
            inputStream = file.getInputStream();
            bufferedInputStream = new BufferedInputStream(inputStream);
            OutputStream outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = bufferedInputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public CommonResult<String> export(HttpServletRequest request,
                               HttpServletResponse response) {
        Calculating calculating = CalculateProvider.getCalculating();
        if (CalculatedStatusEnum.CALCULATING.equals(calculating.getStatus())) {
            throw new RuntimeException("正在计算中，请先等待计算完成");
        }
        List<Calculated> list = calculating.getCalculatedList();
        List<Calculated> afterSortList = list.stream().filter(result ->
                !(result.getInformRate() == null && result.getFinishRate() == null
                        && result.getInitialAcceptCycle() == null && result.getInitialReplyCycle() == null
                        && result.getInitialContactRate() == null && result.getRepeatPetitionRate() == null
                        && result.getPublicReplyRate() == null && result.getSatisfactionRate() == null
                        && result.getRightRate() == null
                )).collect(Collectors.toList());
        List<CalculatedStringResult> resultList = service.formatCalculated(afterSortList);
        ExcelUtils.downLoad(resultList, CalculatedStringResult.class, response);
        return null;
    }

}
