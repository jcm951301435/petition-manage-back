package com.ssy.petition.controller.sys;

import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.sys.params.SysPermissionParams;
import com.ssy.petition.dto.sys.result.SysPermissionResult;
import com.ssy.petition.entity.sys.SysPermission;
import com.ssy.petition.service.sys.SysPermissionService;
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
@Api(tags = "SysPermissionController", description = "菜单管理")
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysPermissionController.class);

    private final SysPermissionService permissionService;

    @Autowired
    public SysPermissionController(SysPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ApiOperation("菜单列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('permission:list')")
    public CommonResult list(SysPermissionParams params) {
        List<SysPermissionResult> list = permissionService.getPermissionTree(params);
        return CommonResult.success(list);
    }

    @ApiOperation("新增菜单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('permission:add')")
    public CommonResult create(@RequestBody SysPermission params) {
        int result = permissionService.create(params);
        if (result == 1) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("添加失败，请联系管理员");
    }

    @ApiOperation("修改菜单")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('permission:update')")
    public CommonResult update(@RequestBody SysPermission params) {
        int result = permissionService.update(params);
        if (result == 1) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failed("修改失败，请联系管理员");
    }

    @ApiOperation("删除菜单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('permission:delete')")
    public CommonResult delete(@PathVariable Long id) {
        int result = permissionService.disable(id);
        if (result == 1) {
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败，请联系管理员");
    }

}
