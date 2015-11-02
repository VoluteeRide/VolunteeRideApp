package com.volunteeride;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.inject.Inject;

/**
 * Created by ayazlakdawala on 10/30/2015.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("ayaz").password("password").roles("RIDE_SEEKER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll();
                /*.antMatchers("/volunteeride*//**").hasAnyRole("VOLUNTEER", "RIDE_SEEKER")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .logout().logoutUrl("/volunteeride/logout").logoutSuccessHandler(new CustomHttpStatusReturningLogoutSuccessHandler())
                .and()
                .csrf().disable();*/

    }

}
