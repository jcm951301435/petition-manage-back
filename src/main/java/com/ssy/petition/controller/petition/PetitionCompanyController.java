package com.ssy.petition.controller.petition;

import com.ssy.petition.common.CommonPage;
import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.petition.params.PetitionCompanyParams;
import com.ssy.petition.dto.petition.result.PetitionCompanyResult;
import com.ssy.petition.entity.petition.PetitionCompany;
import com.ssy.petition.service.petition.PetitionCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "PetitionCompanyController", description = "公司管理")
@RestController
@RequestMapping("/petitionCompany")
public class PetitionCompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PetitionCompanyController.class);

    private final PetitionCompanyService companyService;

    public PetitionCompanyController(PetitionCompanyService companyService) {
        this.companyService = companyService;
    }

    @ApiOperation("公司列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult list(PetitionCompanyParams params,
                             @RequestParam(value = "pageNum", required = false) Integer pageNum,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<PetitionCompanyResult> list = companyService.list(params, pageNum, pageSize);
        CommonPage page = CommonPage.restPage(list);
        return CommonResult.success(page);
    }

    @ApiOperation("新增公司")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('company:add')")
    public CommonResult create(@RequestBody PetitionCompany company) {
        int result = companyService.create(company);
        if (result == 1) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("添加失败，请联系管理员");
    }

    @ApiOperation("修改公司")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('company:update')")
    public CommonResult update(@RequestBody PetitionCompany company) {
        int result = companyService.update(company);
        if (result == 1) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failed("修改失败，请联系管理员");
    }

    @ApiOperation("删除公司")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('company:delete')")
    public CommonResult delete(@PathVariable Long id) {
        int result = companyService.delete(id);
        if (result == 1) {
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败，请联系管理员");
    }

}
