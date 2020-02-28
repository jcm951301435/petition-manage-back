package com.ssy.petition.dao.sys;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.dto.sys.params.SysListListParams;
import com.ssy.petition.dto.sys.result.SysListListResult;
import com.ssy.petition.entity.sys.SysList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色持久化层
 *
 * @author jcm
 */
@Repository
public interface SysListMapper extends BaseMapper<SysList> {

    List<SysListListResult> getList(@Param("queryParam") SysListListParams params);

}