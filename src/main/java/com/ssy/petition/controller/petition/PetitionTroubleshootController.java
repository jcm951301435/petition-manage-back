package com.ssy.petition.controller.petition;

import com.ssy.petition.common.CommonPage;
import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.petition.params.PetitionTroubleshootParams;
import com.ssy.petition.dto.petition.result.PetitionTroubleshootResult;
import com.ssy.petition.entity.petition.PetitionTroubleshoot;
import com.ssy.petition.service.petition.PetitionTroubleshootService;
import com.ssy.petition.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "PetitionTroubleshootController", description = "信访管理")
@RestController
@RequestMapping("/petitionTroubleshoot")
public class PetitionTroubleshootController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PetitionTroubleshootController.class);

    private final PetitionTroubleshootService service;

    public PetitionTroubleshootController(PetitionTroubleshootService service) {
        this.service = service;
    }

    @ApiOperation("信访列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('troubleshoot:list')")
    public CommonResult list(PetitionTroubleshootParams params,
                             @RequestParam(value = "pageNum") Integer pageNum,
                             @RequestParam(value = "pageSize") Integer pageSize) {
        List<PetitionTroubleshootResult> list = service.list(params, pageNum, pageSize);
        CommonPage page = CommonPage.restPage(list);
        return CommonResult.success(page);
    }

    @ApiOperation("新增信访")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('troubleshoot:add')")
    public CommonResult create(@RequestBody PetitionTroubleshoot params) {
        int result = service.create(params);
        if (result == 1) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("添加失败，请联系管理员");
    }

    @ApiOperation("修改信访")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('troubleshoot:update')")
    public CommonResult update(@RequestBody PetitionTroubleshoot params) {
        int result = service.update(params);
        if (result == 1) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failed("修改失败，请联系管理员");
    }

    @ApiOperation("删除信访")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('troubleshoot:delete')")
    public CommonResult delete(@PathVariable Long id) {
        int result = service.delete(id);
        if (result == 1) {
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败，请联系管理员");
    }

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('troubleshoot:export')")
    public CommonResult export(@RequestBody PetitionTroubleshootParams params, HttpServletResponse response) {
        List<PetitionTroubleshootResult> list = service.list(params, null, null);
        ExcelUtils.downLoad(list, PetitionTroubleshootResult.class, response);
        return null;
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('troubleshoot:import')")
    public CommonResult importExcel(@RequestParam("file") MultipartFile file) {
        List<PetitionTroubleshootResult> list;
        try {
            list = ExcelUtils.generateListFromFile(file, PetitionTroubleshootResult.class);
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
