package com.ssy.petition.dao.sys;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.dto.sys.params.SysPermissionParams;
import com.ssy.petition.dto.sys.result.SysPermissionResult;
import com.ssy.petition.entity.sys.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限持久化层
 *
 * @author jcm
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermissionResult> getPermissionList(@Param("queryParam")SysPermissionParams params);

}