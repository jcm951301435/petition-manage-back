package com.ssy.petition.config.entity;

import com.ssy.petition.entity.sys.SysPermission;
import com.ssy.petition.entity.sys.SysUser;
import com.ssy.petition.util.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户详情，用于 Spring Security
 *
 * @author jcm
 */
public class SysUserDetails implements UserDetails {

    private SysUser user;

    private List<SysPermission> permissionList;

    public SysUserDetails(SysUser user, List<SysPermission> permissionList) {
        this.user = user;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (SysPermission permission : permissionList) {
            String value = permission.getValue();
            if (StringUtils.isNotEmpty(value)) {
                grantedAuthorityList.add(new SimpleGrantedAuthority(value));
            }
        }
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !user.getDeleteFlag();
    }
}
