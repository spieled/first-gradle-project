package com.mgj.admin;

import com.mgj.admin.base.BaseController;
import com.mgj.admin.vo.OrderVo;
import com.mgj.base.Result;
import com.mgj.base.socialinsurance.InsurePolicy;
import com.mgj.base.socialinsurance.Order;
import com.mgj.base.socialinsurance.OrderItem;
import com.mgj.core.company.CompanyService;
import com.mgj.core.insured.InsuredService;
import com.mgj.core.order.OrderService;
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
import java.util.Arrays;

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
    public Result createOrder(HttpServletRequest request, OrderVo orderVo) {
        String username = request.getRemoteUser();

        Order order = new Order();
        order.setUsername(username);
        order.setItemNumber(1);
        order.setTitle("缴社保");
        order.setSerialNumber(Util.generateSerialNumber());

        OrderItem item = OrderItem.calc(orderVo.getInsureBase(), InsurePolicy.getPolicy2016(orderVo.isSick(), "外地农村".equals(orderVo.getType())));
        item.setInsurePersonId(orderVo.getPersonId());
        item.setOrderId(order.getId());
        item.setUsername(username);
        item.setInsureCity(orderVo.getCity());
        item.setInsureBase(orderVo.getInsureBase());
        item.setInsureYm(orderVo.getInsureYm());

        order.setTotalPrice(item.getTotal());

        orderService.createOrder(order, Arrays.asList(item));

        return Result.ok();
    }




}
