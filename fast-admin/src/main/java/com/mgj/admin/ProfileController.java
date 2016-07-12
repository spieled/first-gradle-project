package com.mgj.admin;

import com.mgj.admin.base.BaseController;
import com.mgj.admin.config.CustomJdbcUserDetailsManager;
import com.mgj.base.Constants;
import com.mgj.base.Result;
import com.mgj.base.socialinsurance.Account;
import com.mgj.base.socialinsurance.AccountRecord;
import com.mgj.base.socialinsurance.OfflinePayRecord;
import com.mgj.base.socialinsurance.Profile;
import com.mgj.core.user.UserService;
import com.mgj.util.Util;
import net.bull.javamelody.MonitoredWithSpring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    private UserService userService;
    @Autowired
    private CustomJdbcUserDetailsManager userDetailsManager;

    @RequestMapping("")
    public ModelAndView insuredPerson(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("profile/profile");
        String username = request.getRemoteUser();
        Profile profile = userService.findProfileByUsername(username);
        mv.addObject("profile", profile);
        return mv;
    }

    /**
     * 头像
     * @param request
     * @param mv
     * @return
     */
    @RequestMapping("avatar")
    public ModelAndView avatar(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("profile/avatar");
        String username = request.getRemoteUser();
        Profile profile = userService.findProfileByUsername(username);
        mv.addObject("profile", profile);
        return mv;
    }

    /**
     * 实名认证
     * @param request
     * @param mv
     * @return
     */
    @RequestMapping("auth")
    public ModelAndView auth(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("profile/auth");
        String username = request.getRemoteUser();
        Profile profile = userService.findProfileByUsername(username);
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
            userService.updateProfile(profile);
            return Result.ok();
        }
        Profile dbProfile = userService.findProfileById(profile.getId());
        dbProfile.setNickName(profile.getNickName());
        dbProfile.setRealName(profile.getRealName());
        dbProfile.setAddress(profile.getAddress());
        dbProfile.setEmail(profile.getEmail());
        dbProfile.setMobile(profile.getMobile());
        dbProfile.setGender(profile.getGender());
        userService.updateProfile(dbProfile);

        return Result.ok();
    }

    @RequestMapping("detail")
    public Result editProfile(HttpServletRequest request, Long id) {
        Profile profile = userService.findProfileById(id);
        return Result.ok(Constants.EMPTY, Util.singleList(profile));
    }

    @RequestMapping("accounts")
    public ModelAndView accounts(HttpServletRequest request, ModelAndView mv) {
        String action = Util.parseString(request, "action", "accounts");
        switch (action) {
            case "accounts":
                mv.setViewName("accounts");
                List<Account> accounts = userService.findAccountByUsername(request.getRemoteUser());
                mv.addObject("accounts", accounts);
                break;
            case "offlines":
                mv.setViewName("offlines");
                long accountId =  Util.parseLong(Util.parseString(request, "id", Constants.EMPTY), 0L);
                Account account = userService.findAccountById(accountId);
                Page<OfflinePayRecord> pageData = userService.findOfflinePayRecords(accountId, getPageable(request));
                mv.addObject("account", account);
                mv.addObject("pageData", pageData);
                break;
            case "records":
                mv.setViewName("records");
                long accountId2 =  Util.parseLong(Util.parseString(request, "id", Constants.EMPTY), 0L);
                Account account2 = userService.findAccountById(accountId2);
                Page<AccountRecord> pageData2 = userService.findAccountRecords(accountId2, getPageable(request));
                mv.addObject("account", account2);
                mv.addObject("pageData", pageData2);
                break;
            default:
                break;
        }

        return mv;
    }

    @RequestMapping("uploadTicket")
    public Result uploadTicket(HttpServletRequest request, OfflinePayRecord payRecord) {
        payRecord.setUsername(request.getRemoteUser());
        userService.createOfflinePayRecord(payRecord);
        return Result.ok();
    }

    @RequestMapping("updateTicket")
    public Result updateTicket(HttpServletRequest request, OfflinePayRecord payRecord) {
        OfflinePayRecord dbRecord = userService.findOfflinePayRecordById(payRecord.getId());
        dbRecord.setBank(payRecord.getBank());
        dbRecord.setAmount(payRecord.getAmount());
        dbRecord.setCardNumber(payRecord.getCardNumber());
        dbRecord.setSerialNumber(payRecord.getSerialNumber());
        dbRecord.setTicket(payRecord.getTicket());
        dbRecord.setUpdateTime(new Date());
        dbRecord.setStatus(OfflinePayRecord.Status.NEW);
        userService.createOfflinePayRecord(dbRecord);
        return Result.ok();
    }

    @RequestMapping("deleteTicket")
    public Result deleteTicket(HttpServletRequest request, long id) {
        userService.deleteOfflinePayRecord(id);
        return Result.ok();
    }

    @RequestMapping("avatar/update")
    public Result updateAvatar(HttpServletRequest request) {
        String avatar = Util.parseString(request, "avatar", "");
        userService.updateAvatar(request.getRemoteUser(), avatar);
        return Result.ok();
    }

    @RequestMapping("password/update")
    public Result updatePassword(HttpServletRequest request, String oldPassword, String newPassword) {
        userDetailsManager.changePassword(oldPassword, newPassword);
        return Result.ok();
    }

    @RequestMapping("idPic/update")
    public Result updateIdPic(HttpServletRequest request, String idPositive, String idNegtive) {
        userService.updateProfileIdPic(request.getRemoteUser(), idPositive, idNegtive);
        return Result.ok();
    }



}
