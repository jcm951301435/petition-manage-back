package com.ssy.petition.controller.sys;

import com.ssy.petition.common.CommonPage;
import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.sys.params.SysUserListParams;
import com.ssy.petition.dto.sys.params.SysUserLoginParams;
import com.ssy.petition.dto.sys.result.SysUserListResult;
import com.ssy.petition.entity.sys.SysUser;
import com.ssy.petition.service.sys.SecurityUserService;
import com.ssy.petition.service.sys.SysUserRoleRelationService;
import com.ssy.petition.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制层
 *
 * @author jcm
 */
@Api(tags = "SysUserController", description = "用户管理")
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    private final SysUserService userService;

    private final SysUserRoleRelationService userRoleRelationService;

    private final SecurityUserService securityUserService;

    @Autowired
    public SysUserController(SysUserService userService, SecurityUserService securityUserService, SysUserRoleRelationService userRoleRelationService) {
        this.userService = userService;
        this.securityUserService = securityUserService;
        this.userRoleRelationService = userRoleRelationService;
    }

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody SysUserLoginParams loginParams) {
        SysUser sysUser = securityUserService.login(loginParams.getUsername(), loginParams.getPassword());
        if (sysUser != null) {
            return CommonResult.success(sysUser.getUsername());
        }
        return CommonResult.validateFailed("用户名或密码错误");
    }

    @ApiOperation("登出")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public CommonResult logout() {
        SecurityContextHolder.clearContext();
        return CommonResult.success("登出成功");
    }

    @ApiOperation("用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult list(SysUserListParams params,
                             @RequestParam(value = "pageNum") Integer pageNum,
                             @RequestParam(value = "pageSize") Integer pageSize) {
        List<SysUserListResult> userList  = userService.getUserList(params, pageNum, pageSize);
        CommonPage page = CommonPage.restPage(userList);
        return CommonResult.success(page);
    }

    @ApiOperation("新增用户")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody SysUserListResult params) {
        int existCode = -2;
        Long roleId = params.getRoleId();
        SysUser sysUser = securityUserService.getCreateUser(params);
        int result = userService.create(sysUser);
        if (result == 1) {
            if (roleId != null) {
                result = userRoleRelationService.insertOrUpdate(sysUser.getId(), roleId);
            }
        }
        if (result == 1) {
            return CommonResult.success("添加成功");
        } else if (result == existCode) {
            return CommonResult.failed("该用户名已存在");
        }
        return CommonResult.failed("添加失败，请联系管理员");
    }

    @ApiOperation("修改用户")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@RequestBody SysUserListResult params) {
        if (params.isEditPassword()) {
            boolean checkPasswordResult = securityUserService.checkPassword(params.getId(), params.getOldPassword());
            if (!checkPasswordResult) {
                return CommonResult.failed("原密码错误，请录入正确密码");
            }
        }
        Long roleId = params.getRoleId();
        SysUser sysUser = securityUserService.getUpdateUser(params);
        int result = userService.update(sysUser);
        if (result == 1) {
            if (roleId != null) {
                result = userRoleRelationService.insertOrUpdate(sysUser.getId(), roleId);
            }
        }
        if (result == 1) {
            return CommonResult.success("修改成功");
        }
        return CommonResult.failed("修改失败，请联系管理员");
    }

    @ApiOperation("删除用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {
        int result = userService.disable(id);
        if (result == 1) {
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败，请联系管理员");
    }

}
