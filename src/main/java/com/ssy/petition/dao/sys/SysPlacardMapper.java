package com.ssy.petition.dao.sys;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.dto.sys.params.SysPlacardListParams;
import com.ssy.petition.dto.sys.result.SysPlacardListResult;
import com.ssy.petition.entity.sys.SysPlacard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPlacardMapper extends BaseMapper<SysPlacard> {

    List<SysPlacardListResult> getList(@Param("queryParam")SysPlacardListParams params);

    SysPlacardListResult getFirst();

}