package com.ssy.petition.controller.petition;

import com.ssy.petition.common.CommonPage;
import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.petition.params.PetitionContradictionEditParams;
import com.ssy.petition.dto.petition.params.PetitionContradictionParams;
import com.ssy.petition.dto.petition.result.PetitionContradictionResult;
import com.ssy.petition.service.petition.PetitionContradictionService;
import com.ssy.petition.util.ExcelUtils;
import com.ssy.petition.util.JasperUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "PetitionContradictionController", description = "信访管理")
@RestController
@RequestMapping("/petitionContradiction")
public class PetitionContradictionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PetitionContradictionController.class);

    private final PetitionContradictionService service;

    public PetitionContradictionController(PetitionContradictionService service) {
        this.service = service;
    }

    @RequestMapping(value = "/applyNameList", method = RequestMethod.POST)
    public CommonResult applyNameList() {
        List<String> list = service.applyNameList();
        return CommonResult.success(list);
    }


    @ApiOperation("信访列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('contradiction:list')")
    public CommonResult list(PetitionContradictionParams params,
                             @RequestParam(value = "pageNum", required = false) Integer pageNum,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<PetitionContradictionResult> list = service.list(params, pageNum, pageSize);
        CommonPage page = CommonPage.restPage(list);
        return CommonResult.success(page);
    }

    @ApiOperation("新增信访")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('contradiction:add')")
    public CommonResult create(@RequestBody PetitionContradictionEditParams params) {
        Long id = service.create(params);
        if (id == null) {
            return CommonResult.failed("添加失败，请联系管理员");
        }
        return CommonResult.success(id, "添加成功");
    }

    @ApiOperation("修改信访")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('contradiction:update')")
    public CommonResult update(@RequestBody PetitionContradictionEditParams params) {
        int result = service.update(params);
        if (result == 1) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failed("修改失败，请联系管理员");
    }

    @ApiOperation("删除信访")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('contradiction:delete')")
    public CommonResult delete(@PathVariable Long id) {
        int result = service.delete(id);
        if (result == 1) {
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败，请联系管理员");
    }

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public CommonResult export(@RequestBody PetitionContradictionParams params, @RequestParam String type,HttpServletRequest request,
                               HttpServletResponse response) {
        List<PetitionContradictionResult> list = service.list(params, null, null);
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            if ("pdf".equalsIgnoreCase(type)) {
                JasperUtils.showPdf("PetitionContradiction", list, paramsMap, request, response);
            } else if ("doc".equalsIgnoreCase(type)) {
                JasperUtils.exportWord("PetitionContradiction", list, paramsMap, request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("导出失败，原因：" + e.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('contradiction:import')")
    public CommonResult importExcel(@RequestParam("file") MultipartFile file) {
        List<PetitionContradictionResult> list;
        try {
            list = ExcelUtils.generateListFromFile(file, PetitionContradictionResult.class);
            if (list == null) {
                return CommonResult.failed("导入失败，请对比模板文件与上传文件");
            }
            int result = service.insertResultList(list);
            if (result > 0) {
                return CommonResult.success("导入成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("导入失败，原因：" + e.getMessage());
        }
        return CommonResult.failed("导入失败，请联系管理员");
    }

}
