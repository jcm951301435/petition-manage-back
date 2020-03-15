package com.ssy.petition.service.sys.impl;

import com.ssy.petition.dao.sys.SysPermissionMapper;
import com.ssy.petition.dao.sys.SysRolePermissionRelationMapper;
import com.ssy.petition.dto.sys.params.SysPermissionParams;
import com.ssy.petition.dto.sys.params.SysRolePermissionAddParams;
import com.ssy.petition.dto.sys.result.SysPermissionResult;
import com.ssy.petition.dto.sys.result.SysRolePermissionResult;
import com.ssy.petition.entity.sys.SysPermission;
import com.ssy.petition.entity.sys.SysRolePermissionRelation;
import com.ssy.petition.service.sys.SysPermissionService;
import com.ssy.petition.util.EntityUtils;
import com.ssy.petition.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private final SysPermissionMapper mapper;

    private final SysRolePermissionRelationMapper relationMapper;

    @Autowired
    public SysPermissionServiceImpl(SysPermissionMapper mapper, SysRolePermissionRelationMapper relationMapper) {
        this.mapper = mapper;
        this.relationMapper = relationMapper;
    }

    @Override
    public List<SysPermissionResult> getPermissionList(SysPermissionParams params) {
        return mapper.getPermissionList(params);
    }

    @Override
    public List<SysPermissionResult> getPermissionTree(SysPermissionParams params) {
        List<SysPermissionResult> list = getPermissionList(params);
        List<SysPermissionResult> treeList = TreeUtils.getTreeList(list);
        List<SysPermissionResult> result = treeList.stream().sorted((result1, result2) -> {
            String sort1 = result1.getSort();
            String sort2 = result2.getSort();
            if (sort1 == null) {
                return 1;
            } else if (sort2 == null) {
                return -1;
            }
            return sort1.compareTo(sort2);
        }).collect(Collectors.toList());
        return result;
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

    @Override
    public SysRolePermissionResult getPermissionByRole(Long roleId) {
        SysPermissionParams params = new SysPermissionParams();
        List<SysPermissionResult> allList = getPermissionTree(params);
        params.setRoleId(roleId);
        List<SysPermissionResult> permissionList = getPermissionList(params);
        List idList = permissionList.stream().map(permission -> permission.getId()).collect(Collectors.toList());
        SysRolePermissionResult result = new SysRolePermissionResult();
        result.setAllList(allList);
        result.setCheckId(idList);
        return result;
    }

    @Override
    public int roleAddPermissions(SysRolePermissionAddParams params) {
        Long roleId = params.getRoleId();
        List<Long> checkId = params.getCheckId();
        /* 先全部删除，后添加，此处逻辑应可以优化，暂如下处理 */
        int result = relationMapper.deleteRolePermissionRelationNotIn(roleId, new ArrayList<>());
        if (checkId.size() > 0) {
            List<SysRolePermissionRelation> relationList = new ArrayList<>();
            for (Long permissionId : checkId) {
                SysRolePermissionRelation relation = new SysRolePermissionRelation();
                relation.setRoleId(roleId);
                relation.setPermissionId(permissionId);
                EntityUtils.initInsertEntity(relation);
                relationList.add(relation);
            }
            result = relationMapper.insertList(relationList);
        }
        return result;
    }
}
