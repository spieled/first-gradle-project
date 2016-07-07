package com.mgj.admin.directive;

import com.mgj.base.socialinsurance.Company;
import com.mgj.core.company.CompanyService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by yanqu on 2016/7/7.
 */
@Component
public class CompanyDirective implements TemplateDirectiveModel {
    private static final Logger logger = LoggerFactory.getLogger(CompanyDirective.class);

    @Autowired
    private CompanyService companyService;
    private ObjectWrapper wrapper = new DefaultObjectWrapperBuilder(new Version(2, 3, 23)).build();

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        if (params.containsKey("id")) {
            String idStr = ((SimpleScalar) params.get("id")).getAsString();
            try {
                long id = Long.parseLong(idStr);
                String companyName = companyService.findOne(id).getName();
                env.setVariable("name", wrapper.wrap(companyName));
            } catch (Exception e) {
                logger.error("get company name failed", e);
                env.setVariable("name", wrapper.wrap("无"));
            }
        } else {
            String username = ((SimpleScalar) params.get("username")).getAsString();
            List<Company> companies = companyService.findByUsername(username);
            env.setVariable("companies", wrapper.wrap(companies));
        }
        body.render(env.getOut());
    }
}
