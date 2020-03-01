package com.ssy.petition.controller.petition;

import com.ssy.petition.common.CommonPage;
import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.petition.params.PetitionContradictionEditParams;
import com.ssy.petition.dto.petition.params.PetitionContradictionParams;
import com.ssy.petition.dto.petition.result.PetitionContradictionResult;
import com.ssy.petition.service.petition.PetitionContradictionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "PetitionContradictionController", description = "信访管理")
@RestController
@RequestMapping("/petitionContradiction")
public class PetitionContradictionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PetitionContradictionController.class);

    private final PetitionContradictionService service;

    public PetitionContradictionController(PetitionContradictionService service) {
        this.service = service;
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

}
