package com.ssy.petition.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.ssy.petition.dao.sys.SysRoleMapper;
import com.ssy.petition.dao.sys.SysUserMapper;
import com.ssy.petition.dto.sys.params.SysUserListParams;
import com.ssy.petition.dto.sys.result.SysUserListResult;
import com.ssy.petition.entity.base.example.BaseExample;
import com.ssy.petition.entity.sys.SysPermission;
import com.ssy.petition.entity.sys.SysRole;
import com.ssy.petition.entity.sys.SysUser;
import com.ssy.petition.service.sys.SysUserService;
import com.ssy.petition.util.CollectionUtils;
import com.ssy.petition.util.EntityUtils;
import com.ssy.petition.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务实现
 *
 * @author jcm
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    private final SysUserMapper userMapper;

    private final SysRoleMapper roleMapper;

    @Autowired
    public SysUserServiceImpl(SysUserMapper userMapper, SysRoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public SysUser getUserByUserName(String username) {
        BaseExample example = new BaseExample();
        example.createCriteria().andFieldEqualTo("username", username);
        List<SysUser> userList = userMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(userList)) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        return userMapper.getRoleByUserId(userId);
    }

    @Override
    public SysUser getUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<SysPermission> getPermissionListByUserId(Long userId) {
        return userMapper.getPermissionListById(userId);
    }

    @Override
    public List<SysUserListResult> getUserList(SysUserListParams params, Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
        params.setCompanyId(SecurityUtil.getCheckedCurrentCompanyId());
        return userMapper.getUserList(params);
    }

    @Override
    public int create(SysUser sysUser) {
        SysUser userFind = getUserByUserName(sysUser.getUsername());
        if (userFind == null) {
            return userMapper.insert(sysUser);
        }
        return -2;
    }

    @Override
    public int update(SysUser sysUser) {
        return userMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int disable(Long id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        EntityUtils.initDeleteEntity(sysUser);
        return userMapper.updateByPrimaryKeySelective(sysUser);
    }
}
