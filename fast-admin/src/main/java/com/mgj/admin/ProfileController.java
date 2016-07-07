package com.mgj.admin;

import com.mgj.admin.base.BaseController;
import com.mgj.base.Constants;
import com.mgj.base.Result;
import com.mgj.base.socialinsurance.Profile;
import com.mgj.core.user.ProfileService;
import com.mgj.util.Util;
import net.bull.javamelody.MonitoredWithSpring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by yanqu on 2016/6/29.
 */
@RestController
@RequestMapping("profile")
@MonitoredWithSpring
public class ProfileController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private ProfileService profileService;

    @RequestMapping("")
    public ModelAndView insuredPerson(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("profile");
        String username = request.getRemoteUser();
        List<Profile> profiles = profileService.findByUsername(username);
        Profile profile = null;
        if (profiles.isEmpty()) {
            profile = new Profile();
            profile.setUsername(username);
        } else {
            profile = profiles.get(0);
        }
        mv.addObject("profile", profile);
        return mv;
    }

    @RequestMapping("update")
    public Result editProfile(HttpServletRequest request, Profile profile) {
        // TODO 更新须谨慎

        // 验证手机号码
        if (Util.hasText(profile.getMobile()) && !Util.isPhoneNumber(profile.getMobile())) {
            return Result.fail(String.format("%s不是合法的手机号码", profile.getMobile()), Constants.EMPTY);
        }
        if (Util.hasText(profile.getEmail()) && !Util.isEmail(profile.getEmail())) {
            return Result.fail(String.format("%s不是合法的邮箱", profile.getEmail()), Constants.EMPTY);
        }
        if (profile.getId() <= 0) {
            profileService.create(profile);
            return Result.ok();
        }
        Profile dbProfile = profileService.findOne(profile.getId());
        dbProfile.setNickName(profile.getNickName());
        dbProfile.setRealName(profile.getRealName());
        dbProfile.setAddress(profile.getAddress());
        dbProfile.setEmail(profile.getEmail());
        dbProfile.setMobile(profile.getMobile());
        dbProfile.setGender(profile.getGender());
        profileService.create(dbProfile);

        return Result.ok();
    }

    @RequestMapping("detail")
    public Result editProfile(HttpServletRequest request, Long id) {
        Profile profile = profileService.findOne(id);
        return Result.ok(Constants.EMPTY, Util.singleList(profile));
    }

}
