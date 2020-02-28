package com.ssy.petition.service.sys;


import com.ssy.petition.dto.sys.params.SysListListParams;
import com.ssy.petition.dto.sys.result.SysListListResult;
import com.ssy.petition.entity.sys.SysList;

import java.util.List;

/**
 * 权限业务层
 *
 * @author jcm
 */
public interface SysListService {

    List<SysListListResult> list(SysListListParams params, Integer pageNum, Integer pageSize);

    int create(SysList list);

    int update(SysList list);

    int delete(Long id);

    int disable(Long id);

}
