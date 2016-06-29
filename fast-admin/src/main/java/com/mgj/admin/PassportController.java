package com.mgj.admin;

import com.mgj.base.Constants;
import com.mgj.util.LuosimaoSendUtil;
import com.mgj.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yanqu on 2016/6/29.
 */
@RestController
@RequestMapping("passport")
public class PassportController {

    private Logger logger = LoggerFactory.getLogger(PassportController.class);

    @Autowired
    private CustomJdbcUserDetailsManager userDetailsManager;
    private static final Map<String, String> VERIFY_CODE_MAP = new ConcurrentHashMap<>();

    @RequestMapping("verifyCode/send")
    public Result sendVerifyCode(String phone) {
        String validateCode;
        if (VERIFY_CODE_MAP.containsKey(phone)) {
            validateCode = VERIFY_CODE_MAP.get(phone);
        } else {
            validateCode = Util.generateValidateCode();
        }
        VERIFY_CODE_MAP.put(phone, validateCode);

        logger.info("给手机{}发送验证码{}", phone, validateCode);
        String template = "忘记密码，验证码：%s";
        // FIXME uncomment when deploy
        // LuosimaoSendUtil.send(phone, String.format(template, validateCode), "民工加");
        return Result.ok();
    }

    @RequestMapping("verifyCode/validate")
    public Result validateVerifyCode(String phone, String verifyCode) {
        if (verifyCode.equals(VERIFY_CODE_MAP.get(phone))) {
            return Result.ok();
        }
        return Result.fail();
    }

    @RequestMapping("password/reset")
    public Result resetPassword(String phone, String verifyCode, String password) {
        if (verifyCode.equals(VERIFY_CODE_MAP.get(phone))) {
            VERIFY_CODE_MAP.remove(phone);
            userDetailsManager.resetPassword(phone, password);
            return Result.ok();
        }
        return Result.fail();
    }

    @RequestMapping("register")
    public Result register(String username, String password) {
        if (userDetailsManager.userExists(username)) {
            return Result.fail(String.format("用户名【%s】已被使用", username), Constants.EMPTY);
        }
        User user = new User(username, password, true, true, true, true, new ArrayList<>());
        userDetailsManager.createUser(user);
        // FIXME just add to basic groups
        for (String group : userDetailsManager.findAllGroups()) {
            userDetailsManager.addUserToGroup(username, group);
        }
        return Result.ok();
    }

}
