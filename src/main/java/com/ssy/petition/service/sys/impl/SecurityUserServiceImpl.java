package com.ssy.petition.service.sys.impl;

import com.ssy.petition.config.entity.SysUserDetails;
import com.ssy.petition.dto.sys.result.SysUserListResult;
import com.ssy.petition.entity.sys.SysUser;
import com.ssy.petition.service.sys.SecurityUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 处理 Security 用户业务
 *
 * @author jcm
 */
@Service
public class SecurityUserServiceImpl implements SecurityUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUserServiceImpl.class);

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityUserServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SysUser login(String username, String password) {
        SysUser sysUser = null;
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username, password);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
            sysUser = sysUserDetails.getUser();
        } catch (AuthenticationException e) {
            LOGGER.warn("登录失败 {} {}", username, e.getMessage());
        }
        return sysUser;
    }

    @Override
    public SysUser getCreateUser(SysUserListResult userListResult) {
        String passwordPlaintext = userListResult.getPassword();
        String password = passwordEncoder.encode(passwordPlaintext);
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userListResult.getUsername());
        sysUser.setRealName(userListResult.getRealName());
        sysUser.setPassword(password);
        sysUser.init();
        return sysUser;
    }
}
