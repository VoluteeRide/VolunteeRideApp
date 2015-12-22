package com.volunteeride;

import com.volunteeride.security.handler.CustomHttpStatusReturningLogoutSuccessHandler;
import com.volunteeride.security.provider.VolunteerideAuthenticationProvider;
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
    private VolunteerideAuthenticationProvider volunteerideAuthenticationProvider;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(volunteerideAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/volunteeride/centers").permitAll()
                .antMatchers("/volunteeride/users").permitAll()
                .antMatchers("/volunteeride/**").hasAnyAuthority("VOLUNTEER", "RIDE_SEEKER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/volunteeride/users/logout")
                .logoutSuccessHandler(new CustomHttpStatusReturningLogoutSuccessHandler())
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

}
