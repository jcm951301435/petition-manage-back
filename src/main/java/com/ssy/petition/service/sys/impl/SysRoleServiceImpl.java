package com.ssy.petition.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.ssy.petition.dao.sys.SysRoleMapper;
import com.ssy.petition.dto.sys.params.SysRoleListParams;
import com.ssy.petition.dto.sys.result.SysRoleListResult;
import com.ssy.petition.entity.sys.SysRole;
import com.ssy.petition.service.sys.SysRoleService;
import com.ssy.petition.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper mapper;

    @Autowired
    public SysRoleServiceImpl(SysRoleMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<SysRoleListResult> getRoleList(SysRoleListParams params, Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        return mapper.getRoleList(params);
    }

    @Override
    public int create(SysRole sysRole) {
        EntityUtils.initInsertEntity(sysRole);
        return mapper.insertSelective(sysRole);
    }

    @Override
    public int update(SysRole sysRole) {
        EntityUtils.initUpdateEntity(sysRole);
        return mapper.updateByPrimaryKeySelective(sysRole);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int disable(Long id) {
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        EntityUtils.initDeleteEntity(sysRole);
        return mapper.updateByPrimaryKeySelective(sysRole);
    }
}
