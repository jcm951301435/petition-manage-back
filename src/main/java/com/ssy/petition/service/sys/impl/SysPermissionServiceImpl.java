package com.ssy.petition.service.sys.impl;

import com.ssy.petition.dao.sys.SysPermissionMapper;
import com.ssy.petition.dto.sys.params.SysPermissionParams;
import com.ssy.petition.dto.sys.result.SysPermissionResult;
import com.ssy.petition.entity.sys.SysPermission;
import com.ssy.petition.service.sys.SysPermissionService;
import com.ssy.petition.util.EntityUtils;
import com.ssy.petition.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private final SysPermissionMapper mapper;

    @Autowired
    public SysPermissionServiceImpl(SysPermissionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<SysPermissionResult> getPermissionList(SysPermissionParams params) {
        return mapper.getPermissionList(params);
    }

    @Override
    public List<SysPermissionResult> getPermissionTree(SysPermissionParams params) {
        List<SysPermissionResult> list = getPermissionList(params);
        return TreeUtils.getTreeList(list);
    }

    @Override
    public int create(SysPermission sysPermission) {
        EntityUtils.initInsertEntity(sysPermission);
        return mapper.insertSelective(sysPermission);
    }

    @Override
    public int update(SysPermission sysPermission) {
        EntityUtils.initUpdateEntity(sysPermission);
        return mapper.updateByPrimaryKeySelective(sysPermission);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int disable(Long id) {
        SysPermission sysPermission = new SysPermission();
        sysPermission.setId(id);
        EntityUtils.initDeleteEntity(sysPermission);
        return mapper.updateByPrimaryKeySelective(sysPermission);
    }
}
