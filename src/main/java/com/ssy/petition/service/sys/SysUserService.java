package com.ssy.petition.service.sys;

import com.ssy.petition.entity.sys.SysPermission;
import com.ssy.petition.entity.sys.SysUser;

import java.util.List;

/**
 * 用户业务层
 *
 * @author jcm
 */
public interface SysUserService {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户
     */
    SysUser getUserByUserName(String username);

    /**
     * 根据用户 id 获取对应权限列表
     *
     * @param userId 用户 id
     * @return 权限列表
     */
    List<SysPermission> getPermissionListByUserId(Long userId);

}
