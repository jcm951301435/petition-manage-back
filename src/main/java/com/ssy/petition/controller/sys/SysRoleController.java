package com.ssy.petition.controller.sys;

import com.ssy.petition.common.CommonPage;
import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.sys.params.SysRoleListParams;
import com.ssy.petition.dto.sys.result.SysRoleListResult;
import com.ssy.petition.entity.sys.SysRole;
import com.ssy.petition.service.sys.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public SysRoleController(SysRoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation("角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult list(SysRoleListParams params,
                             @RequestParam(value = "pageNum", required = false) Integer pageNum,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<SysRoleListResult> list = roleService.getRoleList(params, pageNum, pageSize);
        CommonPage page = CommonPage.restPage(list);
        return CommonResult.success(page);
    }

    @ApiOperation("新增角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody SysRole params) {
        int result = roleService.create(params);
        if (result == 1) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("添加失败，请联系管理员");
    }

    @ApiOperation("修改角色")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@RequestBody SysRole params) {
        int result = roleService.update(params);
        if (result == 1) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failed("修改失败，请联系管理员");
    }

    @ApiOperation("删除角色")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {
        int result = roleService.disable(id);
        if (result == 1) {
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败，请联系管理员");
    }

}
