package com.ssy.petition.dao.sys;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.dto.sys.params.SysUserListParams;
import com.ssy.petition.dto.sys.result.SysUserListResult;
import com.ssy.petition.entity.sys.SysPermission;
import com.ssy.petition.entity.sys.SysRole;
import com.ssy.petition.entity.sys.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户持久化层
 *
 * @author jcm
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户 id 获取权限列表
     *
     * @param userId 用户 id
     * @return 权限列表
     */
    List<SysPermission> getPermissionListById(@Param("userId") Long userId);

    /**
     * 根据用户 id 获取角色
     *
     * @param userId 用户 id
     * @return 角色列表
     */
    List<SysRole> getRoleByUserId(Long userId);

    /**
     * 根据查询条件获取用户列表
     *
     * @param params 查询条件
     * @return 用户列表
     */
    List<SysUserListResult> getUserList(@Param("queryParam") SysUserListParams params);

}