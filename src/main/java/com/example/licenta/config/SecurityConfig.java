package com.example.licenta.config;

import com.example.licenta.controller.LogoutController;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.http.HttpMethod.*;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LogoutController logoutController;

    public SecurityConfig(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .mvcMatchers("/", "/user").permitAll()
                .antMatchers(POST, "/*").authenticated()
                .antMatchers(DELETE, "/*").authenticated()
                .antMatchers(PUT, "/*").authenticated()
                .antMatchers(GET, "/*").authenticated()
                .antMatchers(DELETE, "/user/*").permitAll()
                .anyRequest().permitAll()
                .and().oauth2Login()
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .addLogoutHandler(logoutController);
    }

}