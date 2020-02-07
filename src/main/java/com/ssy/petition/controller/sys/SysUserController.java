package com.ssy.petition.controller.sys;

import com.ssy.petition.common.CommonResult;
import com.ssy.petition.dto.sys.params.SysUserLoginParams;
import com.ssy.petition.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 *
 * @author jcm
 */
@Api(tags = "SysUserController", description = "用户")
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    private final SysUserService service;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public SysUserController(SysUserService service, AuthenticationManager authenticationManager) {
        this.service = service;
        this.authenticationManager = authenticationManager;
    }

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody SysUserLoginParams loginParams) {
        boolean loginResult = login(loginParams.getUsername(), loginParams.getPassword());
        if (loginResult) {
            return CommonResult.success("登录成功");
        }
        return CommonResult.validateFailed("用户名或密码错误");
    }

    @ApiOperation("登出")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public CommonResult logout() {
        SecurityContextHolder.clearContext();
        return CommonResult.success("登出成功");
    }

    @ApiOperation("测试")
    @RequestMapping(value = "/test")
    public CommonResult test() {
        return CommonResult.success("");
    }

    /**
     * 登录
     * 此方法若在 service 层中 会导致 Authentication 依赖死循环
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    private Boolean login(String username, String password) {
        boolean loginResult = false;
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username, password);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            loginResult = true;
        } catch (AuthenticationException e) {
            LOGGER.warn("登录失败 {} {}", username, e.getMessage());
        }
        return loginResult;
    }



}
