package com.mgj.admin.config;

import com.mgj.admin.directive.CompanyDirective;
import com.mgj.admin.directive.EnumDirective;
import com.mgj.admin.directive.NameDirective;
import com.mgj.admin.directive.PersonDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanqu on 2016/7/7.
 */
@Configuration
public class FreemarkerConfiguration extends FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration {

    @Autowired
    private CompanyDirective companyDirective;
    @Autowired
    private EnumDirective enumDirective;
    @Autowired
    private NameDirective nameDirective;
    @Autowired
    private PersonDirective personDirective;

    private static final Map<String, Object> VARIABLES = new HashMap<>();

    private Map<String, Object> getVariables() {
        if (VARIABLES.isEmpty()) {
            initVariables();
        }
        return VARIABLES;
    }
    private void initVariables() {
        VARIABLES.put("companyDirective", companyDirective);
        VARIABLES.put("name", nameDirective);
        VARIABLES.put("enum", enumDirective);
        VARIABLES.put("persons", personDirective);
    }

//    @Bean
    @Override
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        applyProperties(configurer);
        configurer.setTemplateLoaderPath("classpath:/templates/");
        configurer.setFreemarkerVariables(getVariables());
        return configurer;
    }
}