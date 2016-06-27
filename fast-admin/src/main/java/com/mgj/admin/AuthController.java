package com.mgj.admin;

import com.mgj.base.Constants;
import com.mgj.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by yanqu on 2016/6/26.
 */
@RestController
public class AuthController {

    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    @RequestMapping("groups")
    public ModelAndView group(ModelAndView mv) {
        mv.setViewName("groups");
        mv.addObject("groups", userDetailsManager.findAllGroups());
        return mv;
    }

    @RequestMapping("users")
    public ModelAndView users(ModelAndView mv) {
        mv.setViewName("users");
        Set<String> users = new HashSet<>();
        for (String groupName : userDetailsManager.findAllGroups()) {
            List<String> usersInGroup = userDetailsManager.findUsersInGroup(groupName);
            users.addAll(usersInGroup);
        }
        mv.addObject("users", users);
        return mv;
    }



    @RequestMapping("user/create")
    public Result createUser(HttpServletRequest request, User user) {
        userDetailsManager.createUser(user);
        return Result.ok();
    }

    @RequestMapping("user/update")
    public Result updateUser(User user) {
        userDetailsManager.updateUser(user);
        return Result.ok();
    }

    @RequestMapping("user/delete")
    public Result deleteUser(String username) {
        userDetailsManager.deleteUser(username);
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
        for (String auth : authorities.split(Constants.COMMA)) {
            grantedAuthorities.add(new SimpleGrantedAuthority(auth));
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
