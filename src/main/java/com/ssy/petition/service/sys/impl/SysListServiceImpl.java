package com.ssy.petition.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.ssy.petition.dao.sys.SysListMapper;
import com.ssy.petition.dto.sys.params.SysListListParams;
import com.ssy.petition.dto.sys.result.SysListListResult;
import com.ssy.petition.entity.sys.SysList;
import com.ssy.petition.service.sys.SysListService;
import com.ssy.petition.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysListServiceImpl implements SysListService {

    private final SysListMapper mapper;

    @Autowired
    public SysListServiceImpl(SysListMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<SysListListResult> list(SysListListParams params, Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        return mapper.getList(params);
    }

    @Override
    public int create(SysList list) {
        EntityUtils.initInsertEntity(list);
        return mapper.insertSelective(list);
    }

    @Override
    public int update(SysList list) {
        EntityUtils.initUpdateEntity(list);
        return mapper.updateByPrimaryKeySelective(list);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int disable(Long id) {
        SysList list = new SysList();
        list.setId(id);
        EntityUtils.initDeleteEntity(list);
        return mapper.updateByPrimaryKeySelective(list);
    }
}
