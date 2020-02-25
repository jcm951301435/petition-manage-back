package com.ssy.petition.service.sys.impl;

import com.ssy.petition.dao.sys.SysUserRoleRelationMapper;
import com.ssy.petition.entity.base.example.BaseExample;
import com.ssy.petition.entity.sys.SysUserRoleRelation;
import com.ssy.petition.service.sys.SysUserRoleRelationService;
import com.ssy.petition.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleRelationServiceImpl implements SysUserRoleRelationService {

    private final SysUserRoleRelationMapper mapper;

    @Autowired
    public SysUserRoleRelationServiceImpl(SysUserRoleRelationMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int insertOrUpdate(Long userId, Long roleId) {
        /*此处若人员仅存在一个角色，则人员=角色关系表不必单独维护，以下代码仅从现有表结构结合以上情况处理，此处可优化处理*/
        BaseExample example = new BaseExample();
        example.createCriteria().andFieldEqualTo("user_id", userId);
        List<SysUserRoleRelation> relationList = mapper.selectByExample(example);
        SysUserRoleRelation relation;
        if (relationList.size() > 0) {
            relation = relationList.get(0);
            if (!relation.getRoleId().equals(roleId)) {
                relation.setRoleId(roleId);
                EntityUtils.initUpdateEntity(relation);
                return mapper.updateByPrimaryKey(relation);
            }
            return 1;
        } else {
            relation = new SysUserRoleRelation();
            relation.setUserId(userId);
            relation.setRoleId(roleId);
            EntityUtils.initInsertEntity(relation);
            return mapper.insert(relation);
        }
    }
}
