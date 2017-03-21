package com.projects.bs.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/error", "/assets/**", "/signup").permitAll()
                .antMatchers("/profile/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/application/**").hasAuthority("USER")
                .antMatchers("/faculty/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()

                .and()

                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=loginFailed")
                .usernameParameter("login")
                .passwordParameter("password")
                .permitAll()

                .and()

                .logout()
                .logoutUrl("/logout")
                .deleteCookies("remember-me")
                .logoutSuccessUrl("/")
                .permitAll()

                .and()

                .rememberMe()
                .key("rem-me-key")
                .rememberMeParameter("remember-me-param")
                .rememberMeCookieName("my-remember-me")
                .tokenValiditySeconds(172800);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}