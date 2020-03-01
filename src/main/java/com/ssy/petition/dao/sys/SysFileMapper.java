package com.ssy.petition.dao.sys;

import com.ssy.petition.dao.base.BaseMapper;
import com.ssy.petition.entity.sys.SysFile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysFileMapper extends BaseMapper<SysFile> {

    List<SysFile> getListByContradictionId(Long contradictionId);

}