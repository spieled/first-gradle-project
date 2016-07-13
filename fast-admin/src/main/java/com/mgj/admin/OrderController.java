package com.mgj.admin;

import com.mgj.admin.base.BaseController;
import com.mgj.base.Result;
import com.mgj.base.socialinsurance.Order;
import com.mgj.core.company.CompanyService;
import com.mgj.core.insured.InsuredService;
import com.mgj.core.order.OrderService;
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
@RequestMapping("orders")
@MonitoredWithSpring
public class OrderController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private CompanyService companyService;
    @Autowired
    private InsuredService insuredService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("")
    public ModelAndView list(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("order/orders");
        Page<Order> pageData = orderService.findOrdersByUsername(request.getRemoteUser(), getPageable(request));
        mv.addObject("pageData", pageData);
        return mv;
    }
    @RequestMapping("create")
    public Result createOrder(HttpServletRequest request) {

        return Result.ok();
    }




}
