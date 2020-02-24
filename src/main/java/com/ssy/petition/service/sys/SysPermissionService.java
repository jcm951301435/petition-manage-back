package com.ssy.petition.service.sys;


import com.ssy.petition.dto.sys.params.SysPermissionParams;
import com.ssy.petition.dto.sys.result.SysPermissionResult;

import java.util.List;

/**
 * 权限业务层
 *
 * @author jcm
 */
public interface SysPermissionService {

    List<SysPermissionResult> getPermissionList(SysPermissionParams params);

    List<SysPermissionResult> getPermissionTree(SysPermissionParams params);

}
