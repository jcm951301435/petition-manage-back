package com.ssy.petition.util;

import com.ssy.petition.config.entity.SysUserDetails;
import com.ssy.petition.entity.sys.SysRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static SysUserDetails getSysUserDetails() {
        Authentication authentication = getAuthentication();
        return (SysUserDetails) authentication.getPrincipal();
    }

    public static Long getCurrentUserId() {
        SysUserDetails sysUserDetails = getSysUserDetails();
        return sysUserDetails.getUser().getId();
    }

    public static Long getCurrentCompanyId() {
        SysUserDetails sysUserDetails = getSysUserDetails();
        return sysUserDetails.getUser().getCompanyId();
    }

    public static Long getCheckedCurrentCompanyId() {
        SysUserDetails sysUserDetails = getSysUserDetails();
        SysRole sysRole = sysUserDetails.getRole();
        if (sysRole != null) {
            Boolean filterData = sysRole.getFilterData();
            if (filterData != null && filterData) {
                return sysUserDetails.getUser().getCompanyId();
            }
        }
        return null;
    }

}
