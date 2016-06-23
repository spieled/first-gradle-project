package com.mgj.core;

import com.mgj.base.socialinsurance.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by yanqu on 2016/6/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SampleApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void createUser() throws Exception {
        User user = new User();
        user.setUsername("scoot");
        user.setPassword("123456");
        userService.createUser(user);
        Assert.assertTrue(true);

    }

}