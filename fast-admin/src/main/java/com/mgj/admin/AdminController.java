package com.mgj.admin;

import com.mgj.admin.base.BaseController;
import com.mgj.base.Constants;
import com.mgj.base.Result;
import com.mgj.base.socialinsurance.Company;
import com.mgj.base.socialinsurance.InsuredPerson;
import com.mgj.base.socialinsurance.OfflinePayRecord;
import com.mgj.core.company.CompanyService;
import com.mgj.core.insured.InsuredService;
import com.mgj.core.user.UserService;
import net.bull.javamelody.MonitoredWithSpring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yanqu on 2016/6/29.
 */
@RestController
@RequestMapping("admin")
@MonitoredWithSpring
public class AdminController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private CompanyService companyService;
    @Autowired
    private InsuredService insuredService;
    @Autowired
    private UserService userService;

    @RequestMapping("persons")
    public ModelAndView persons(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("admin/persons");
        Page<InsuredPerson> pageData = insuredService.findAll(getPageable(request));
        mv.addObject("pageData", pageData);
        return mv;
    }

    @RequestMapping("companies")
    public ModelAndView companies(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("admin/companies");
        Page<Company> pageData = companyService.findAll(getPageable(request));
        mv.addObject("pageData", pageData);
        return mv;
    }

    @RequestMapping("companies/check")
    public Result checkCompany(HttpServletRequest request, long id, Company.Status status) {
        try {
            companyService.updateStatus(id, status);
        } catch (Exception e) {
            logger.error("更新企业审核状态失败", e);
            return Result.fail("更新企业审核状态失败", Constants.EMPTY);
        }
        return Result.ok();
    }

    @RequestMapping("persons/check")
    public Result checkPerson(HttpServletRequest request, long id, InsuredPerson.Status status) {
        try {
            insuredService.updateStatus(id, status);
        } catch (Exception e) {
            logger.error("更新参保人审核状态失败", e);
            return Result.fail("更新参保人审核状态失败", Constants.EMPTY);
        }
        return Result.ok();
    }

    @RequestMapping("offlinePayRecord/check")
    public Result checkOfflinePayRecord(HttpServletRequest request, long id, OfflinePayRecord.Status status) {
        try {
            userService.updateOfflinePayrecordStatus(id, status);
        } catch (Exception e) {
            logger.error("更新线下充值记录审核状态失败", e);
            return Result.fail("更新线下充值记录审核状态失败", Constants.EMPTY);
        }
        return Result.ok();
    }

    @RequestMapping("tickets")
    public ModelAndView tickets(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("admin/tickets");
        Page<OfflinePayRecord> pageData = userService.findOfflinePayRecords(getPageable(request));
        mv.addObject("pageData", pageData);
        return mv;
    }


}
