package com.mgj.base.socialinsurance;

import com.mgj.base.BaseEntity;

/**
 * 用户
 * Created by yanqu on 2016/6/15.
 */
public class User extends BaseEntity {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
