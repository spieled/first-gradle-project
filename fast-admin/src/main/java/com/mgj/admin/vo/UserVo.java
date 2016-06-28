package com.mgj.admin.vo;

import java.util.*;

/**
 * Created by yanqu on 2016/6/28.
 */
public class UserVo {
    private String username;
    private Collection<String> groups = new ArrayList<>();
    private Collection<String> authorities = new HashSet<>();
    private boolean enabled = false;

    public UserVo() {
    }

    public UserVo(String username, boolean enabled, Collection<String> groups, Collection<String> authorities) {
        this.username = username;
        this.enabled = enabled;
        if (groups != null) {
            this.groups = groups;
        }
        if (authorities != null) {
            this.authorities = authorities;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<String> getGroups() {
        return groups;
    }

    public void setGroups(Collection<String> groups) {
        this.groups = groups;
    }

    public Collection<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<String> authorities) {
        this.authorities = authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
