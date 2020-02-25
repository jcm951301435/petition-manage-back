package com.ssy.petition.service.sys;


import com.ssy.petition.dto.sys.params.SysRoleListParams;
import com.ssy.petition.dto.sys.result.SysRoleListResult;
import com.ssy.petition.entity.sys.SysRole;

import java.util.List;

/**
 * 权限业务层
 *
 * @author jcm
 */
public interface SysRoleService {

    List<SysRoleListResult> getRoleList(SysRoleListParams params, Integer pageNum, Integer pageSize);

    int create(SysRole sysRole);

    int update(SysRole sysRole);

    int delete(Long id);

    int disable(Long id);

}
