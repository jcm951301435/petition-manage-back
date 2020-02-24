package com.ssy.petition.controller.sys;

import com.ssy.petition.common.CommonPage;
import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.sys.params.SysUserListParams;
import com.ssy.petition.dto.sys.params.SysUserLoginParams;
import com.ssy.petition.dto.sys.result.SysUserListResult;
import com.ssy.petition.entity.sys.SysUser;
import com.ssy.petition.service.sys.SecurityUserService;
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

    private final SecurityUserService securityUserService;

    @Autowired
    public SysUserController(SysUserService userService, SecurityUserService securityUserService) {
        this.userService = userService;
        this.securityUserService = securityUserService;
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
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<SysUserListResult> userList  = userService.getUserList(params, pageNum, pageSize);
        CommonPage page = CommonPage.restPage(userList);
        return CommonResult.success(page);
    }

    @ApiOperation("创建用户")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody SysUserListResult params) {
        SysUser sysUser = securityUserService.getCreateUser(params);
        int result = userService.create(sysUser);
        if (result == 1) {
            return CommonResult.success("添加成功");
        }
        return CommonResult.failed("添加失败，请联系管理员");
    }

}
