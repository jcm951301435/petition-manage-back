package com.ssy.petition.controller.sys;

import com.ssy.petition.common.CommonPage;
import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.sys.params.SysRoleListParams;
import com.ssy.petition.dto.sys.params.SysRolePermissionAddParams;
import com.ssy.petition.dto.sys.result.SysRoleListResult;
import com.ssy.petition.dto.sys.result.SysRolePermissionResult;
import com.ssy.petition.entity.sys.SysRole;
import com.ssy.petition.service.sys.SysPermissionService;
import com.ssy.petition.service.sys.SysRoleService;
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
@Api(tags = "SysRoleController", description = "角色管理")
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleController.class);

    private final SysRoleService roleService;

    private final SysPermissionService permissionService;

    @Autowired
    public SysRoleController(SysRoleService roleService, SysPermissionService permissionService) {
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @ApiOperation("角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('role:list')")
    public CommonResult list(SysRoleListParams params,
                             @RequestParam(value = "pageNum", required = false) Integer pageNum,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<SysRoleListResult> list = roleService.getRoleList(params, pageNum, pageSize);
        CommonPage page = CommonPage.restPage(list);
        return CommonResult.success(page);
    }

    @ApiOperation("新增角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('role:add')")
    public CommonResult create(@RequestBody SysRole params) {
        int result = roleService.create(params);
        if (result == 1) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("添加失败，请联系管理员");
    }

    @ApiOperation("修改角色")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('role:update')")
    public CommonResult update(@RequestBody SysRole params) {
        int result = roleService.update(params);
        if (result == 1) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failed("修改失败，请联系管理员");
    }

    @ApiOperation("删除角色")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('role:delete')")
    public CommonResult delete(@PathVariable Long id) {
        int result = roleService.disable(id);
        if (result == 1) {
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败，请联系管理员");
    }

    @ApiOperation("角色对应菜单列表")
    @RequestMapping(value = "/rolePermissionList/{id}", method = RequestMethod.POST)
    public CommonResult rolePermissionList(@PathVariable Long id) {
        SysRolePermissionResult result = permissionService.getPermissionByRole(id);
        return CommonResult.success(result);
    }

    @ApiOperation("角色修改菜单列表")
    @RequestMapping(value = "/roleAddPermissions", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('role:addPermission')")
    public CommonResult roleAddPermissions(@RequestBody SysRolePermissionAddParams params) {
        int result = permissionService.roleAddPermissions(params);
        if (result >= 0) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failed("修改失败，请联系管理员");
    }

}
