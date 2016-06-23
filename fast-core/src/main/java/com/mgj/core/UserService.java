package com.mgj.core;

import com.mgj.base.socialinsurance.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yanqu on 2016/6/23.
 */
@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createUser(User user) {
        jdbcTemplate.update("INSERT INTO user (`username`, `password`) VALUES (?, ?)", new Object[] {
                user.getUsername(), user.getPassword()
        });
    }

    @Transactional(readOnly = true)
    public void fakeCreateUser(User user) {
        jdbcTemplate.update("INSERT INTO user (`username`, `password`) VALUES (?, ?)", new Object[] {
                user.getUsername(), user.getPassword()
        });
    }

}
