package com.ssy.petition.service.sys.impl;

import com.ssy.petition.common.MapperOperateType;
import com.ssy.petition.config.entity.SysUserDetails;
import com.ssy.petition.dto.sys.result.SysUserListResult;
import com.ssy.petition.entity.sys.SysUser;
import com.ssy.petition.service.sys.SecurityUserService;
import com.ssy.petition.service.sys.SysUserService;
import com.ssy.petition.util.EntityUtils;
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

    private final SysUserService userService;

    @Autowired
    public SecurityUserServiceImpl(AuthenticationManager authenticationManager,
                                   PasswordEncoder passwordEncoder, SysUserService userService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
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
        SysUser sysUser = getUserByUserResult(userListResult, MapperOperateType.INSERT);
        EntityUtils.initInsertEntity(sysUser);
        return sysUser;
    }

    @Override
    public SysUser getUpdateUser(SysUserListResult userListResult) {
        SysUser sysUser = getUserByUserResult(userListResult, MapperOperateType.UPDATE);
        EntityUtils.initUpdateEntity(sysUser);
        return sysUser;
    }

    /**
     * 根据 SysUserListResult 获取 SysUser
     *
     * @param userListResult 参数
     * @param type 操作类型
     * @return SysUser
     */
    private SysUser getUserByUserResult(SysUserListResult userListResult, MapperOperateType type) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userListResult.getUsername());
        sysUser.setRealName(userListResult.getRealName());
        boolean encodePassword = false;
        boolean useOldId = false;
        switch (type) {
            case INSERT:
                encodePassword = true;
                break;
            case UPDATE:
                encodePassword = userListResult.isEditPassword();
                useOldId = true;
                break;
            case DELETE:
                break;
            default:
                break;
        }
        if (encodePassword) {
            String passwordPlaintext = userListResult.getPassword();
            String password = passwordEncoder.encode(passwordPlaintext);
            sysUser.setPassword(password);
        }
        if (useOldId) {
            sysUser.setId(userListResult.getId());
        }
        return sysUser;
    }

    @Override
    public boolean checkPassword(Long userId, String passwordPlaintext) {
        SysUser user = userService.getUserByUserId(userId);
        String passwordCheck = passwordEncoder.encode(passwordPlaintext);
        String password = user.getPassword();
        return passwordCheck.equals(password);
    }

}
