package com.mgj.admin;

import com.mgj.admin.config.CustomJdbcUserDetailsManager;
import com.mgj.admin.vo.GroupVo;
import com.mgj.admin.vo.UserVo;
import com.mgj.base.Constants;
import com.mgj.base.Result;
import com.mgj.util.Util;
import net.bull.javamelody.MonitoredWithSpring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanqu on 2016/6/26.
 */
@RestController
@MonitoredWithSpring
public class AuthController {

    public static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private CustomJdbcUserDetailsManager userDetailsManager;

    @RequestMapping("groups")
    public ModelAndView group(ModelAndView mv) {
        mv.setViewName("groups");
        List<GroupVo> groupVoList = new ArrayList<>();
        List<String> groupNames = userDetailsManager.findAllGroups();
        for (String groupName : groupNames) {
            GroupVo groupVo = new GroupVo();
            groupVo.setGroupName(groupName);
            List<GrantedAuthority> authorities = userDetailsManager.findGroupAuthorities(groupName);
            authorities.forEach(s -> groupVo.getAuthorities().add(s.getAuthority()));
            groupVoList.add(groupVo);
        }
        mv.addObject("groups", groupVoList);
        return mv;
    }

    @RequestMapping("users")
    public ModelAndView users(ModelAndView mv) {
        mv.setViewName("users");
        List<UserVo> users = userDetailsManager.findAllUser();
        mv.addObject("groups", userDetailsManager.findAllGroups());
        mv.addObject("users", users);
        return mv;
    }



    @RequestMapping("user/create")
    public Result createUser(HttpServletRequest request, String username, String password, Boolean enabled) {
        String[] groups = request.getParameterValues("group");
        User user = new User(username, password, enabled, true, true, true, new ArrayList<>());
        userDetailsManager.createUser(user);
        if (groups != null) {
            for (String group : groups) {
                userDetailsManager.addUserToGroup(username, group);
            }
        }
        return Result.ok();
    }

    @RequestMapping("user/password/reset")
    public Result createUser(HttpServletRequest request, String username, String password) {
        userDetailsManager.resetPassword(username, password);
        return Result.ok();
    }

    @RequestMapping("user/update")
    public Result updateUser(HttpServletRequest request, String username, Boolean enabled) {
        String[] groups = request.getParameterValues("group");
        UserDetails user = userDetailsManager.loadUserByUsername(username);
        if (user == null) {
            return Result.fail("用户不存在", Constants.EMPTY);
        }
        int targetStatus = user.isEnabled() ? 0 : 1;
        userDetailsManager.getJdbcTemplate().update("update users set enabled=?", targetStatus);
        // FIXME this should be much simpler, on-change no-action
        for (String group : userDetailsManager.findAllGroups()) {
            userDetailsManager.removeUserFromGroup(username, group);
        }
        for (String group : groups) {
            userDetailsManager.addUserToGroup(username, group);
        }
        return Result.ok();
    }

    @RequestMapping("user/enabled/toggle")
    public Result toggleUserEnabled(String username) {
        UserDetails user = userDetailsManager.loadUserByUsername(username);
        if (user == null) {
            return Result.fail("用户不存在", Constants.EMPTY);
        }
        int targetStatus = user.isEnabled() ? 0 : 1;
        userDetailsManager.getJdbcTemplate().update("update users set enabled=? where username=?", targetStatus, username);
        return Result.ok();
    }

    @RequestMapping("user/delete")
    public Result deleteUser(String username) {
        userDetailsManager.deleteUser(username);
        List<String> groups = userDetailsManager.findAllGroups();
        for (String group : groups) {
            userDetailsManager.removeUserFromGroup(username, group);
        }
        return Result.ok();
    }

    @RequestMapping("user/get")
    public Result getUser(String username) {
        UserDetails user = userDetailsManager.loadUserByUsername(username);
        if (user == null) {
            return Result.ok(Constants.EMPTY, new ArrayList<>());
        }
        return Result.ok(Constants.EMPTY, Util.singleList(user));
    }

    @RequestMapping("user/exist")
    public Result existUser(String username) {
        boolean exists = userDetailsManager.userExists(username);
        return Result.ok(Constants.EMPTY, exists);
    }

    @RequestMapping("user/list")
    public Result listUser() {
        // TODO
        return Result.ok();
    }

    @RequestMapping("group/create")
    public Result createGroup(String groupName, String authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (!StringUtils.isEmpty(authorities)) {
            for (String auth : authorities.split(Constants.COMMA)) {
                grantedAuthorities.add(new SimpleGrantedAuthority(auth));
            }
        }
        userDetailsManager.createGroup(groupName, grantedAuthorities);
        return Result.ok();
    }

    @RequestMapping("group/rename")
    public Result renameGroup(String groupName, String newName) {
        userDetailsManager.renameGroup(groupName, newName);
        return Result.ok();
    }

    @RequestMapping("group/delete")
    public Result deleteGroup(String groupName) {
        userDetailsManager.deleteGroup(groupName);
        return Result.ok();
    }

    @RequestMapping("group/list")
    public Result listGroups() {
        List<String> groups = userDetailsManager.findAllGroups();
        return Result.ok(Constants.EMPTY, groups);
    }

    @RequestMapping("group/authority/list")
    public Result listGroupAuthority(String groupName) {
        List<GrantedAuthority> authorities = userDetailsManager.findGroupAuthorities(groupName);
        return Result.ok(Constants.EMPTY, authorities);
    }

    @RequestMapping("group/authority/update")
    public Result updateGroupAuthority(String groupName, String authorities) {
        List<GrantedAuthority> oldAuthorities = userDetailsManager.findGroupAuthorities(groupName);
        if (!oldAuthorities.isEmpty()) {
            for (GrantedAuthority oldAuth : oldAuthorities) {
                userDetailsManager.removeGroupAuthority(groupName, oldAuth);
            }
        }
        if (!authorities.isEmpty()) {
            for (String auth : authorities.split(Constants.COMMA)) {
                userDetailsManager.addGroupAuthority(groupName, new SimpleGrantedAuthority(auth));
            }
        }
        return Result.ok();
    }

    @RequestMapping("group/authority/add")
    public Result addGroupAuthority(String groupName, String authority) {
        userDetailsManager.addGroupAuthority(groupName, new SimpleGrantedAuthority(authority));
        return Result.ok();
    }

    @RequestMapping("group/authority/remove")
    public Result removeGroupAuthority(String groupName, String authority) {
        userDetailsManager.removeGroupAuthority(groupName, new SimpleGrantedAuthority(authority));
        return Result.ok();
    }

    @RequestMapping("group/user/add")
    public Result addGroupUser(String groupName, String username) {
        userDetailsManager.addUserToGroup(username, groupName);
        return Result.ok();
    }

    @RequestMapping("group/user/remove")
    public Result removeGroupUser(String groupName, String username) {
        userDetailsManager.removeUserFromGroup(username, groupName);
        return Result.ok();
    }


    @RequestMapping("group/list-user")
    public Result listGroupUser(String groupName) {
        List<String> users = userDetailsManager.findUsersInGroup(groupName);
        return Result.ok(Constants.EMPTY, users);
    }

}
