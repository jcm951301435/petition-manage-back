package com.ssy.petition.controller.sys;

import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.sys.params.SysPermissionParams;
import com.ssy.petition.dto.sys.result.SysPermissionResult;
import com.ssy.petition.dto.sys.result.SysUserListResult;
import com.ssy.petition.service.sys.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限控制层
 *
 * @author jcm
 */
@Api(tags = "SysPermissionController", description = "权限管理")
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysPermissionController.class);

    private final SysPermissionService permissionService;

    @Autowired
    public SysPermissionController(SysPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ApiOperation("权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult list(SysPermissionParams params) {
        List<SysPermissionResult> list = permissionService.getPermissionTree(params);
        return CommonResult.success(list);
    }

    @ApiOperation("新增权限")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody SysUserListResult params) {
        return null;
    }

    @ApiOperation("修改权限")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@RequestBody SysUserListResult params) {
        return null;
    }

}
