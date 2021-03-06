package com.ssy.petition.service.sys;

import com.ssy.petition.dto.sys.params.SysUserListParams;
import com.ssy.petition.dto.sys.result.SysUserListResult;
import com.ssy.petition.entity.sys.SysPermission;
import com.ssy.petition.entity.sys.SysRole;
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
     * 根据用户id获取用户信息
     *
     * @param userId 用户id
     * @return 用户
     */
    SysUser getUserByUserId(Long userId);

    /**
     * 根据用户id获取角色
     *
     * @param userId 用户id
     * @return 角色
     */
    List<SysRole> getRoleByUserId(Long userId);

    /**
     * 根据用户 id 获取对应权限列表
     *
     * @param userId 用户 id
     * @return 权限列表
     */
    List<SysPermission> getPermissionListByUserId(Long userId);

    /**
     * 根据查询条件获取用户列表
     *
     * @param params   查询条件
     * @param pageNum  页码
     * @param pageSize 每页条目
     * @return 用户列表
     */
    List<SysUserListResult> getUserList(SysUserListParams params, Integer pageNum, Integer pageSize);

    /**
     * 新增用户
     *
     * @param sysUser 参数
     * @return 新增结果
     */
    int create(SysUser sysUser);

    /**
     * 修改用户
     *
     * @param sysUser 参数
     * @return 修改结果
     */
    int update(SysUser sysUser);

    /**
     * 删除用户
     *
     * @param id 参数
     * @return 删除结果
     */
    int delete(Long id);

    /**
     * 禁用用户
     *
     * @param id 参数
     * @return 禁用结果
     */
    int disable(Long id);

}
