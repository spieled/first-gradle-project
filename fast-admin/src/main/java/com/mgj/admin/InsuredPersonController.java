package com.mgj.admin;

import com.alibaba.fastjson.JSON;
import com.mgj.admin.base.BaseController;
import com.mgj.base.Constants;
import com.mgj.base.socialinsurance.InsuredPerson;
import com.mgj.core.insured.InsuredService;
import com.mgj.util.IdCardUtil;
import com.mgj.util.Region;
import com.mgj.util.Util;
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
@RequestMapping("persons")
public class InsuredPersonController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(InsuredPersonController.class);

    @Autowired
    private InsuredService insuredService;

    @RequestMapping("")
    public ModelAndView insuredPerson(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("insured-person");
        String username = request.getRemoteUser();
        Page<InsuredPerson> insuredPersons = insuredService.findByUsername(username, getPageable(request));
        logger.info(JSON.toJSONString(insuredPersons));
        mv.addObject("insuredPersons", insuredPersons);
        return mv;
    }

    @RequestMapping("findAll")
    public Result findAllInsuredPerson(HttpServletRequest request) {
        Iterable<InsuredPerson> insuredPersons = insuredService.findAll();
        return Result.ok(Constants.EMPTY, insuredPersons);
    }

    @RequestMapping("list")
    public Result listInsuredPerson(HttpServletRequest request) {
        String username = request.getRemoteUser();
        List<InsuredPerson> persons = insuredService.findByUsername(username);
        return Result.ok(Constants.EMPTY, persons);
    }

    @RequestMapping("create")
    public Result createInsuredPerson(HttpServletRequest request, InsuredPerson person) {
        String username = request.getRemoteUser();
        person.setUsername(username);
        String idNumber = person.getIdNumber();
        if (!IdCardUtil.isIDCard(idNumber)) {
            return Result.fail(String.format("%s不是合法的身份证号码", idNumber), Constants.EMPTY);
        }
        Region region = IdCardUtil.resolveRegionCode(idNumber);
        person.setCity(region.getCityCode());
        person.setCityName(region.getCityName());
        person.setBirthYmd(IdCardUtil.resolveBirthday(idNumber));
        person.setGender(IdCardUtil.resolveGender(idNumber));
        insuredService.create(person);
        return Result.ok();
    }

    @RequestMapping("update")
    public Result editInsuredPerson(HttpServletRequest request, InsuredPerson person) {
        // TODO 更新须谨慎
        String idNumber = person.getIdNumber();
        if (!IdCardUtil.isIDCard(idNumber)) {
            return Result.fail(String.format("%s不是合法的身份证号码", idNumber), Constants.EMPTY);
        }
        Region region = IdCardUtil.resolveRegionCode(idNumber);
        person.setCity(region.getCityCode());
        person.setCityName(region.getCityName());
        person.setBirthYmd(IdCardUtil.resolveBirthday(idNumber));
        person.setGender(IdCardUtil.resolveGender(idNumber));

        InsuredPerson dbPerson = insuredService.findOne(person.getId());
        dbPerson.setName(person.getName());
        dbPerson.setIdNumber(person.getIdNumber());
        dbPerson.setType(person.getType());
        dbPerson.setCity(person.getCity());
        dbPerson.setCityName(person.getCityName());
        dbPerson.setCompanyId(person.getCompanyId());
        dbPerson.setInsured(person.isInsured());
        dbPerson.setOnStation(person.isOnStation());
        dbPerson.setUpdateTime(new Date());
        dbPerson.setBirthYmd(person.getBirthYmd());
        dbPerson.setGender(person.getGender());
        insuredService.create(dbPerson);

        return Result.ok();
    }

    @RequestMapping("detail")
    public Result editInsuredPerson(HttpServletRequest request, Long id) {
        InsuredPerson person = insuredService.findOne(id);
        return Result.ok(Constants.EMPTY, Util.singleList(person));
    }

    @RequestMapping("delete")
    public Result deleteInsuredPerson(HttpServletRequest request, Long id) {
        insuredService.delete(id);
        return Result.ok();
    }

}
