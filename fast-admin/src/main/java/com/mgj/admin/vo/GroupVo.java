package com.mgj.admin.vo;

import com.mgj.base.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanqu on 2016/6/27.
 */
public class GroupVo {
    private String groupName = Constants.EMPTY;
    private List<String> authorities = new ArrayList<>();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
