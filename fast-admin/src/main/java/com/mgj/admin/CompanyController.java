package com.mgj.admin;

import com.mgj.admin.base.BaseController;
import com.mgj.base.Constants;
import com.mgj.base.Result;
import com.mgj.base.socialinsurance.Company;
import com.mgj.core.company.CompanyService;
import com.mgj.util.IdCardUtil;
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
import java.util.List;

/**
 * Created by yanqu on 2016/6/29.
 */
@RestController
@RequestMapping("companies")
@MonitoredWithSpring
public class CompanyController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @RequestMapping("")
    public ModelAndView insuredPerson(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("companies");
        String username = request.getRemoteUser();
        Page<Company> pageData = companyService.findByUsername(username, getPageable(request));
        mv.addObject("pageData", pageData);
        return mv;
    }

    @RequestMapping("findAll")
    public Result findAllCompany(HttpServletRequest request) {
        Iterable<Company> insuredPersons = companyService.findAll();
        return Result.ok(Constants.EMPTY, insuredPersons);
    }

    @RequestMapping("list")
    public Result listCompany(HttpServletRequest request) {
        String username = request.getRemoteUser();
        List<Company> companys = companyService.findByUsername(username);
        return Result.ok(Constants.EMPTY, companys);
    }

    @RequestMapping("create")
    public Result createCompany(HttpServletRequest request, Company company) {
        // 验证身份证号码
        if (!IdCardUtil.isIDCard(company.getLegalPersonIdNumber())) {
            return Result.fail(String.format("%s不是合法的身份证号码", company.getLegalPersonIdNumber()), Constants.EMPTY);
        }
        // 验证手机号码
        if (!Util.isPhoneNumber(company.getMobile())) {
            return Result.fail(String.format("%s不是合法的手机号码", company.getMobile()), Constants.EMPTY);
        }
        String username = request.getRemoteUser();
        company.setUsername(username);
        companyService.create(company);
        return Result.ok();
    }

    @RequestMapping("update")
    public Result editCompany(HttpServletRequest request, Company company) {
        // TODO 更新须谨慎

        // 验证身份证号码
        if (!IdCardUtil.isIDCard(company.getLegalPersonIdNumber())) {
            return Result.fail(String.format("%s不是合法的身份证号码", company.getLegalPersonIdNumber()), Constants.EMPTY);
        }
        // 验证手机号码
        if (!Util.isPhoneNumber(company.getMobile())) {
            return Result.fail(String.format("%s不是合法的手机号码", company.getMobile()), Constants.EMPTY);
        }
        Company dbCompany = companyService.findOne(company.getId());
        dbCompany.setName(company.getName());
        dbCompany.setAddress(company.getAddress());
        dbCompany.setLegalPerson(company.getLegalPerson());
        dbCompany.setLegalPersonIdNumber(company.getLegalPersonIdNumber());
        dbCompany.setLicense(company.getLicense());
        dbCompany.setOrgCode(company.getOrgCode());
        dbCompany.setTaxCode(company.getTaxCode());
        dbCompany.setContactPerson(company.getContactPerson());
        dbCompany.setMobile(company.getMobile());

        companyService.create(dbCompany);

        return Result.ok();
    }

    @RequestMapping("detail")
    public Result editCompany(HttpServletRequest request, Long id) {
        Company company = companyService.findOne(id);
        return Result.ok(Constants.EMPTY, Util.singleList(company));
    }

    @RequestMapping("delete")
    public Result deleteCompany(HttpServletRequest request, Long id) {
        companyService.delete(id);
        return Result.ok();
    }

}
