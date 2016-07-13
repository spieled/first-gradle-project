package com.mgj.admin.directive;

import com.mgj.base.socialinsurance.InsuredPerson;
import com.mgj.core.insured.InsuredService;
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
public class PersonDirective implements TemplateDirectiveModel {
    private static final Logger logger = LoggerFactory.getLogger(PersonDirective.class);

    @Autowired
    private InsuredService insuredService;
    private ObjectWrapper wrapper = new DefaultObjectWrapperBuilder(new Version(2, 3, 23)).build();

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String username = ((SimpleScalar) params.get("username")).getAsString();
        List<InsuredPerson> persons = insuredService.findByUsername(username);
        env.setVariable("personList", wrapper.wrap(persons));
        body.render(env.getOut());
    }
}
