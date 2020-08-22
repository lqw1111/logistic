package com.logistic.project.configure;

import com.logistic.project.dto.JsonResponse;
import com.logistic.project.dto.UserInfoDTO;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserInfoService userInfoService;

       @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/home","/register", "/orderhistory/findAll/info").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {
                        User user = (User) authentication.getPrincipal();
                        UserInfoDTO userInfo = null;
                        try {
                            userInfo = userInfoService.getUserInfo(user.getUsername());
                        } catch (LogisticException e) {
                            e.printStackTrace();
                        }

                        JsonResponse tmp = new JsonResponse()
                                .code(HttpStatus.OK.value())
                                .message("login successfully")
                                .obj(userInfo == null ? user : userInfo);

                        res.setContentType("application/json;charset=utf-8");
                        try(PrintWriter out = res.getWriter()) {
                            out.write(tmp.json());
                            out.flush();
                        }
                    }
                })
                .failureHandler((req, res, err) -> {
                    JsonResponse tmp = new JsonResponse()
                            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("fail to login");

                    res.setContentType("application/json;charset=utf-8");
                    try(PrintWriter out = res.getWriter()) {
                        out.write(tmp.json());
                        out.flush();
                    }
                })
                //指定登录页是"/login"
                .loginPage("/login")
                .permitAll()
                //登录成功后默认跳转到
                .and()
                .rememberMe()
                // 即登录页面的记住登录按钮的参数名
                .rememberMeParameter("remember-me")
                // 过期时间
                .tokenValiditySeconds(1800)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {
                        JsonResponse tmp = new JsonResponse()
                                .code(HttpStatus.OK.value())
                                .message("logout successfully");

                        res.setContentType("application/json;charset=utf-8");
                        try(PrintWriter out = res.getWriter()) {
                            out.write(tmp.json());
                            out.flush();
                        }
                    }
                })
                .deleteCookies("remember-me")
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(getAccessDeniedHandler());
    }

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return (req, res, err) -> {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.setCharacterEncoding("UTF-8");
            try(PrintWriter out = res.getWriter()) {
                out.write("You don't have authorization to do so, please contact the administrator.");
                out.flush();
            }
        };
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
