package com.ssy.petition.dao.sys;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.dto.sys.params.SysRoleListParams;
import com.ssy.petition.dto.sys.result.SysRoleListResult;
import com.ssy.petition.entity.sys.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色持久化层
 *
 * @author jcm
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRoleListResult> getRoleList(@Param("queryParam") SysRoleListParams params);

}