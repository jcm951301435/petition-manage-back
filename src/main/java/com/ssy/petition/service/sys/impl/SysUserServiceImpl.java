package com.ssy.petition.service.sys.impl;

import com.ssy.petition.dao.sys.SysUserMapper;
import com.ssy.petition.entity.base.example.BaseExample;
import com.ssy.petition.entity.sys.SysPermission;
import com.ssy.petition.entity.sys.SysUser;
import com.ssy.petition.service.sys.SysUserService;
import com.ssy.petition.util.CollectionUtils;
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

    @Autowired
    public SysUserServiceImpl(SysUserMapper userMapper) {
        this.userMapper = userMapper;
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
    public List<SysPermission> getPermissionListByUserId(Long userId) {
        return userMapper.getPermissionListById(userId);
    }

}
