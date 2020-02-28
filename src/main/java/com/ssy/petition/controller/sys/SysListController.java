package com.ssy.petition.controller.sys;

import com.ssy.petition.common.CommonPage;
import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.sys.params.SysListListParams;
import com.ssy.petition.dto.sys.result.SysListListResult;
import com.ssy.petition.entity.sys.SysList;
import com.ssy.petition.service.sys.SysListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单控制层
 *
 * @author jcm
 */
@Api(tags = "SysListController", description = "列选管理")
@RestController
@RequestMapping("/sysList")
public class SysListController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysListController.class);

    private final SysListService listService;

    @Autowired
    public SysListController(SysListService listService) {
        this.listService = listService;
    }

    @ApiOperation("列选列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('list:list')")
    public CommonResult list(SysListListParams params,
                             @RequestParam(value = "pageNum", required = false) Integer pageNum,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<SysListListResult> list = listService.list(params, pageNum, pageSize);
        CommonPage page = CommonPage.restPage(list);
        return CommonResult.success(page);
    }

    @ApiOperation("新增列选")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('list:add')")
    public CommonResult create(@RequestBody SysList params) {
        int result = listService.create(params);
        if (result == 1) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("添加失败，请联系管理员");
    }

    @ApiOperation("修改列选")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('list:update')")
    public CommonResult update(@RequestBody SysList params) {
        int result = listService.update(params);
        if (result == 1) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failed("修改失败，请联系管理员");
    }

    @ApiOperation("删除列选")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('list:delete')")
    public CommonResult delete(@PathVariable Long id) {
        int result = listService.disable(id);
        if (result == 1) {
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败，请联系管理员");
    }

}
