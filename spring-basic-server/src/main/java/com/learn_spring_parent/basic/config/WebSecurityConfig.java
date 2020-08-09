package com.learn_spring_parent.basic.config;

import com.learn_spring_parent.basic.filter.JwtVerifyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RsaKeyProperty prop;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests().anyRequest().authenticated();
        //http.addFilter(new JwtVerifyFilter(super.authenticationManager(), prop))
         //       .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
