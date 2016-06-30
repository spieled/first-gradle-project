package com.mgj.admin;

import com.mgj.base.Constants;
import com.mgj.base.socialinsurance.InsuredPerson;
import com.mgj.core.insured.InsuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanqu on 2016/6/29.
 */
@RestController
@RequestMapping("insured")
public class InsuredPersonController {

    @Autowired
    private InsuredService insuredService;

    @RequestMapping("/insured-person")
    public ModelAndView insuredPerson(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("insured-person");
        String username = request.getRemoteUser();
        List<InsuredPerson> insuredPersons = insuredService.findByUsername(username);
        mv.addObject("insuredPersons", insuredPersons);
        return mv;
    }

    @RequestMapping("findAll")
    public Result findAllInsuredPerson(HttpServletRequest request) {

        // TODO 获取所有参保人列表
        List<Object> persons = new ArrayList<>();
        return Result.ok(Constants.EMPTY, persons);
    }

    @RequestMapping("list")
    public Result listInsuredPerson(HttpServletRequest request) {
        String username = request.getRemoteUser();

        // TODO 获取该用户添加的参保人列表

        List<Object> persons = new ArrayList<>();
        return Result.ok(Constants.EMPTY, persons);
    }

    @RequestMapping("create")
    public Result createInsuredPerson(HttpServletRequest request, InsuredPerson person) {
        String username = request.getRemoteUser();

        // TODO 保存到数据库

        return Result.ok();
    }

    @RequestMapping("edit")
    public Result editInsuredPerson(HttpServletRequest request, InsuredPerson person) {
        String username = request.getRemoteUser();

        // TODO 保存到数据库

        return Result.ok();
    }

    @RequestMapping("detail")
    public Result editInsuredPerson(HttpServletRequest request, Long id) {
        String username = request.getRemoteUser();

        // TODO

        return Result.ok();
    }

    @RequestMapping("delete")
    public Result deleteInsuredPerson(HttpServletRequest request, Long id) {
        String username = request.getRemoteUser();

        // TODO

        return Result.ok();
    }

}
