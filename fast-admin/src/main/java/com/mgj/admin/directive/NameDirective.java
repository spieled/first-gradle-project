package com.mgj.admin.directive;

import com.mgj.base.Constants;
import com.mgj.base.Gender;
import com.mgj.base.socialinsurance.*;
import com.mgj.core.company.CompanyService;
import com.mgj.core.insured.InsuredService;
import com.mgj.core.user.UserService;
import com.mgj.util.Util;
import freemarker.core.Environment;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yanqu on 2016/7/7.
 */
@Component
public class NameDirective implements TemplateDirectiveModel {
    private static final Logger logger = LoggerFactory.getLogger(NameDirective.class);

    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;
    @Autowired
    private InsuredService insuredService;

    private ObjectWrapper wrapper = new DefaultObjectWrapperBuilder(new Version(2, 3, 23)).build();

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String type = ((SimpleScalar) params.get("type")).getAsString();
        String idStr = ((SimpleScalar) params.get("id")).getAsString();
        String name = Constants.EMPTY;
        try {
            switch (type) {
                case "company":
                    name = companyService.findOne(Long.parseLong(idStr)).getName();
                    break;
                case "person":
                    name = insuredService.findOne(Long.parseLong(idStr)).getName();
                    break;
                case "user":
                    name = userService.findProfileByUsername(idStr).getNickName();
                    break;
                case "gender":
                    name = Util.parseEnumByName(Gender.class, idStr).display();
                    break;
                case "account.type":
                    name = Util.parseEnumByName(Account.Type.class, idStr).display();
                    break;
                case "accountRecord.type":
                    name = Util.parseEnumByName(AccountRecord.Type.class, idStr).display();
                    break;
                case "company.status":
                    name = Util.parseEnumByName(Company.Status.class, idStr).display();
                    break;
                case "person.status":
                    name = Util.parseEnumByName(InsuredPerson.Status.class, idStr).display();
                    break;
                case "person.type":
                    name = Util.parseEnumByName(InsuredPerson.Type.class, idStr).display();
                    break;
                case "offlinePayRecord.status":
                    name = Util.parseEnumByName(OfflinePayRecord.Status.class, idStr).display();
                    break;
                case "order.status":
                    name = Util.parseEnumByName(Order.Status.class, idStr).display();
                    break;
                case "withdrawRecord.status":
                    name = Util.parseEnumByName(WithdrawRecord.Status.class, idStr).display();
                    break;
                case "account":
                    name = userService.findAccountById(Long.parseLong(idStr)).getName();
                    break;
                default:
                    name = Constants.EMPTY;
                    break;
            }
        } catch (Exception e) {
            logger.error("translate id to name failed", e);
        }
        env.setVariable("display", wrapper.wrap(name));
        body.render(env.getOut());
    }
}
