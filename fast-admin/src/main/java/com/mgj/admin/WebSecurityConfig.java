package com.mgj.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/bootstrap-dist/**", "/css/**", "/email_templates/**", "/font-awesome/**",
                "/frontend_theme/**", "/img/**", "/js/**", "**.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO
        http
            .authorizeRequests()
                .antMatchers("/", "/index", "/passport/**/*", "/autoconfig", "/health")
                .permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // TODO
        jdbcUserDetailsManager();
        auth.jdbcAuthentication().dataSource(dataSource).getUserDetailsService().setEnableGroups(true);
    }

    @Bean
    public CustomJdbcUserDetailsManager jdbcUserDetailsManager() {
        CustomJdbcUserDetailsManager jdbcUserDetailsManager = new CustomJdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        jdbcUserDetailsManager.setEnableGroups(true);
        return jdbcUserDetailsManager;
    }
}
