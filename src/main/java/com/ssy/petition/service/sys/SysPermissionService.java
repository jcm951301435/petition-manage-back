package com.ssy.petition.service.sys;


import com.ssy.petition.dto.sys.params.SysPermissionParams;
import com.ssy.petition.dto.sys.params.SysRolePermissionAddParams;
import com.ssy.petition.dto.sys.result.SysPermissionResult;
import com.ssy.petition.dto.sys.result.SysRolePermissionResult;
import com.ssy.petition.entity.sys.SysPermission;

import java.util.List;

/**
 * 权限业务层
 *
 * @author jcm
 */
public interface SysPermissionService {

    List<SysPermissionResult> getPermissionList(SysPermissionParams params);

    List<SysPermissionResult> getPermissionTree(SysPermissionParams params);

    int create(SysPermission sysPermission);

    int update(SysPermission sysPermission);

    int delete(Long id);

    int disable(Long id);

    SysRolePermissionResult getPermissionByRole(Long roleId);

    int roleAddPermissions(SysRolePermissionAddParams params);

}
