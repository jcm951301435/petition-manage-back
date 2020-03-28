package com.ssy.petition.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.ssy.petition.dao.sys.SysPlacardMapper;
import com.ssy.petition.dto.sys.params.SysPlacardListParams;
import com.ssy.petition.dto.sys.result.SysPlacardListResult;
import com.ssy.petition.entity.sys.SysPlacard;
import com.ssy.petition.service.sys.SysPlacardService;
import com.ssy.petition.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPlacardServiceImpl implements SysPlacardService {

    private final SysPlacardMapper mapper;

    public SysPlacardServiceImpl(SysPlacardMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<SysPlacardListResult> list(SysPlacardListParams params, Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        return mapper.getList(params);
    }

    @Override
    public int create(SysPlacard sysPlacard) {
        EntityUtils.initInsertEntity(sysPlacard);
        return mapper.insertSelective(sysPlacard);
    }

    @Override
    public int update(SysPlacard sysPlacard) {
        EntityUtils.initUpdateEntity(sysPlacard);
        return mapper.updateByPrimaryKeySelective(sysPlacard);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int disable(Long id) {
        SysPlacard sysPlacard = new SysPlacard();
        sysPlacard.setId(id);
        EntityUtils.initDeleteEntity(sysPlacard);
        return mapper.updateByPrimaryKeySelective(sysPlacard);
    }

    @Override
    public SysPlacardListResult getFirst() {
        return mapper.getFirst();
    }
}
