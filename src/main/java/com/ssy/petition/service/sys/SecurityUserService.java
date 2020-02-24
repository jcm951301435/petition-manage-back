package com.ssy.petition.service.sys;

import com.ssy.petition.dto.sys.result.SysUserListResult;
import com.ssy.petition.entity.sys.SysUser;

/**
 * 处理 Security 用户业务
 *
 * @author jcm
 */
public interface SecurityUserService {

    /**
     * 登录
     * 此方法若在 SysUserService 层中 会导致 Authentication 依赖死循环
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    SysUser login(String username, String password);

    /**
     * 根据 SysUserListResult 生成待添加用户
     *
     * @param userListResult 参数
     * @return 待添加用户
     */
    SysUser getCreateUser(SysUserListResult userListResult);

    /**
     * 根据 SysUserListResult 生成待修改用户
     *
     * @param userListResult 参数
     * @return 待修改用户
     */
    SysUser getUpdateUser(SysUserListResult userListResult);

    /**
     * 检测密码是否正确
     * @param userId 用户id
     * @param password 密码
     * @return 检测结果
     */
    boolean checkPassword(Long userId, String password);

}
