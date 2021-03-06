package com.ssy.petition.controller.sys;

import com.ssy.petition.common.CommonPage;
import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.sys.params.SysPlacardListParams;
import com.ssy.petition.dto.sys.result.SysPlacardListResult;
import com.ssy.petition.entity.sys.SysPlacard;
import com.ssy.petition.service.sys.SysPlacardService;
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
@RestController
@RequestMapping("/sysPlacard")
public class SysPlacardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysPlacardController.class);

    private final SysPlacardService service;

    @Autowired
    public SysPlacardController(SysPlacardService service) {
        this.service = service;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('placard:list')")
    public CommonResult list(SysPlacardListParams params,
                             @RequestParam(value = "pageNum", required = false) Integer pageNum,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<SysPlacardListResult> list = service.list(params, pageNum, pageSize);
        CommonPage page = CommonPage.restPage(list);
        return CommonResult.success(page);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('placard:add')")
    public CommonResult create(@RequestBody SysPlacard params) {
        int result = service.create(params);
        if (result == 1) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("添加失败，请联系管理员");
    }

    @ApiOperation("修改列选")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('placard:update')")
    public CommonResult update(@RequestBody SysPlacard params) {
        int result = service.update(params);
        if (result == 1) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failed("修改失败，请联系管理员");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('placard:delete')")
    public CommonResult delete(@PathVariable Long id) {
        int result = service.delete(id);
        if (result == 1) {
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败，请联系管理员");
    }

    @RequestMapping(value = "/first", method = RequestMethod.POST)
    public CommonResult first() {
        SysPlacardListResult result = service.getFirst();
        return CommonResult.success(result);
    }

}
