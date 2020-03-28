package com.ssy.petition.config.service;

import com.ssy.petition.config.entity.SysUserDetails;
import com.ssy.petition.entity.sys.SysPermission;
import com.ssy.petition.entity.sys.SysRole;
import com.ssy.petition.entity.sys.SysUser;
import com.ssy.petition.service.sys.SysUserService;
import com.ssy.petition.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Spring Security userDetails 提供业务
 *
 * @author jcm
 */
@Service
public class SelfUserDetailsServiceImpl implements UserDetailsService {

    private final SysUserService sysUserService;

    @Autowired
    public SelfUserDetailsServiceImpl(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getUserByUserName(username);
        /* 此处若存在异常，spring security 会捕获异常，交由 ResultAuthenticationEntryPoint 处理(返回前端) */
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        List<SysRole> roleList = sysUserService.getRoleByUserId(sysUser.getId());
        SysRole sysRole = null;
        if (CollectionUtils.isNotEmpty(roleList)) {
            sysRole = roleList.get(0);
        }
        List<SysPermission> permissionList = sysUserService.getPermissionListByUserId(sysUser.getId());
        return new SysUserDetails(sysUser, permissionList, sysRole);
    }

}
