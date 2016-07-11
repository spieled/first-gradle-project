package com.mgj.admin.directive;

import com.mgj.base.Constants;
import com.mgj.base.Gender;
import com.mgj.base.socialinsurance.*;
import freemarker.core.Environment;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yanqu on 2016/7/7.
 */
@Component
public class EnumDirective implements TemplateDirectiveModel {
    private static final Logger logger = LoggerFactory.getLogger(EnumDirective.class);

    private ObjectWrapper wrapper = new DefaultObjectWrapperBuilder(new Version(2, 3, 23)).build();

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String type = ((SimpleScalar) params.get("type")).getAsString();
        String name = Constants.EMPTY;
        List<EnumVo> list = new ArrayList<>();
        try {
            switch (type) {
                case "gender":
                    for (Gender gender : Gender.values()) {
                        list.add(new EnumVo(gender.name(), gender.display()));
                    }
                    break;
                case "account.type":
                    for (Account.Type accountType : Account.Type.values()) {
                        list.add(new EnumVo(accountType.name(), accountType.display()));
                    }
                    break;
                case "accountRecord.type":
                    for (AccountRecord.Type accountRecordType : AccountRecord.Type.values()) {
                        list.add(new EnumVo(accountRecordType.name(), accountRecordType.display()));
                    }
                    break;
                case "company.status":
                    for (Company.Status companyStatus : Company.Status.values()) {
                        list.add(new EnumVo(companyStatus.name(), companyStatus.display()));
                    }
                    break;
                case "person.status":
                    for (InsuredPerson.Status personStatus : InsuredPerson.Status.values()) {
                        list.add(new EnumVo(personStatus.name(), personStatus.display()));
                    }
                    break;
                case "person.type":
                    for (InsuredPerson.Type personType : InsuredPerson.Type.values()) {
                        list.add(new EnumVo(personType.name(), personType.display()));
                    }
                    break;
                case "offlinePayRecord.status":
                    for (OfflinePayRecord.Status offlinePayRecordStatus : OfflinePayRecord.Status.values()) {
                        list.add(new EnumVo(offlinePayRecordStatus.name(), offlinePayRecordStatus.display()));
                    }
                    break;
                case "order.status":
                    for (Order.Status orderStatus : Order.Status.values()) {
                        list.add(new EnumVo(orderStatus.name(), orderStatus.display()));
                    }
                    break;
                case "withdrawRecord.status":
                    for (WithdrawRecord.Status withdrawStatus : WithdrawRecord.Status.values()) {
                        list.add(new EnumVo(withdrawStatus.name(), withdrawStatus.display()));
                    }
                    break;
                default:
                    name = Constants.EMPTY;
                    break;
            }
        } catch (Exception e) {
            logger.error("translate enum name to display failed", e);
        }
        env.setVariable("name", wrapper.wrap(name));
        body.render(env.getOut());
    }
}
