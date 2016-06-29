package com.mgj.admin;

import com.mgj.admin.vo.UserVo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yanqu on 2016/6/28.
 */
public class CustomJdbcUserDetailsManager extends JdbcUserDetailsManager {
    private static final String DEF_SELECT_ALL_USERS = "select username, enabled from users order by username";
    private static final String DEF_SELECT_USER_GROUPS = "select g.group_name from groups g , group_members gm where gm.username=? and g.id=gm.group_id";
    private static final String DEF_RESET_PASSWORD = "update users set password=? where username=?";

    public List<UserVo> findAllUser() {
        List<UserVo> users = getJdbcTemplate().query(DEF_SELECT_ALL_USERS, new Object[]{}, new RowMapper<UserVo>() {
            @Override
            public UserVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserVo user = new UserVo();
                user.setUsername(rs.getString(1));
                user.setEnabled(rs.getBoolean(2));
                return user;
            }
        });
        for (UserVo user : users) {
            List<String> groups = getJdbcTemplate().query(DEF_SELECT_USER_GROUPS, new Object[]{user.getUsername()}, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString(1);
                }
            });
            user.getGroups().addAll(groups);
            for (String group : groups) {
                super.findGroupAuthorities(group).forEach(s -> user.getAuthorities().add(s.getAuthority()));
            }
        }
        return users;
    }

    public void resetPassword(String username, String password) {
        getJdbcTemplate().update(DEF_RESET_PASSWORD, new Object[] {password, username});
    }
}
