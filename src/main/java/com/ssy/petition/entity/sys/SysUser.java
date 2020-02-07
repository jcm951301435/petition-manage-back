package com.ssy.petition.entity.sys;

import com.ssy.petition.entity.base.BaseEntity;

import java.io.Serializable;

/**
 * 用户
 *
 * @author jcm
 */
public class SysUser extends BaseEntity implements Serializable {
    private String username;

    private String password;

    private String status;

    private static final long serialVersionUID = 1L;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

}