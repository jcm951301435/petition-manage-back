package com.ssy.petition.service.sys;


import com.ssy.petition.dto.sys.params.SysPlacardListParams;
import com.ssy.petition.dto.sys.result.SysPlacardListResult;
import com.ssy.petition.entity.sys.SysPlacard;

import java.util.List;

/**
 * 权限业务层
 *
 * @author jcm
 */
public interface SysPlacardService {

    List<SysPlacardListResult> list(SysPlacardListParams params, Integer pageNum, Integer pageSize);

    int create(SysPlacard sysPlacard);

    int update(SysPlacard sysPlacard);

    int delete(Long id);

    int disable(Long id);

    SysPlacardListResult getFirst();

}
