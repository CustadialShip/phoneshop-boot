package com.expertsoft.phoneshop.security.config;

import com.expertsoft.phoneshop.security.PhoneShopOAuth2UserService;
import com.expertsoft.phoneshop.service.PhoneShopUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private PhoneShopUserService phoneShopUserService;
    @Resource
    private PhoneShopOAuth2UserService phoneShopOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(ROOT_PATH, PHONES_PATH, LOGIN_PATH).permitAll()
                    .antMatchers(PHONES_ADD_PATH).authenticated()
                    .antMatchers(ADMIN_PATH).hasRole(ADMIN).and()
                .oauth2Login()
                    .userInfoEndpoint()
                    .userService(phoneShopOAuth2UserService).and()
                    .loginPage(LOGIN_PATH).and()
                .formLogin()
                    .loginPage(LOGIN_PATH)
                    .failureUrl(LOGIN_FAILURE_PATH).and()
                .logout()
                    .logoutUrl(LOGOUT_PATH)
                    .logoutSuccessUrl(ROOT_PATH);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(phoneShopUserService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
