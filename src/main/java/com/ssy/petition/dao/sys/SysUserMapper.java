package com.ssy.petition.dao.sys;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.entity.sys.SysPermission;
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
     * @param userId 用户 id
     * @return 权限列表
     */
    List<SysPermission> getPermissionListById(@Param("userId")Long userId);

}