package com.mgj.admin;

import com.mgj.admin.base.BaseController;
import com.mgj.base.Constants;
import com.mgj.base.Result;
import com.mgj.base.socialinsurance.InsurePolicy;
import com.mgj.base.socialinsurance.OrderItem;
import com.mgj.util.Util;
import net.bull.javamelody.MonitoredWithSpring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Created by yanqu on 2016/6/29.
 */
@RestController
@RequestMapping("common")
@MonitoredWithSpring
public class CommonController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);


    @RequestMapping("calc")
    public Result calc(HttpServletRequest request) {
        try {
            BigDecimal insureBase = Util.parseBigDecimal(request, "insureBase", BigDecimal.ZERO);
            boolean sick = Util.parseBoolean(request, "sick", false);
            String type = Util.parseString(request, "type", Constants.EMPTY);
            InsurePolicy policy = InsurePolicy.getPolicy2016(sick, "外地农村".equals(type));
            OrderItem item = OrderItem.calc(insureBase, policy);
            return Result.ok(Constants.EMPTY, item);
        } catch (Exception e) {
            logger.error("计算社保出差", e);
            return Result.fail("计算社保出差", Constants.EMPTY);
        }
    }

}
