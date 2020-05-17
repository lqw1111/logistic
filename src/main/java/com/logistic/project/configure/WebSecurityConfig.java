package com.logistic.project.configure;

import com.logistic.project.dto.JsonResponse;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.DigestUtils;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/home","/register").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {
                        JsonResponse tmp = new JsonResponse()
                                .code(HttpStatus.OK.value())
                                .message("login successfully")
                                .obj(authentication.getPrincipal());

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
                //登录成功后默认跳转到
                .permitAll()
                .and()
                .logout()
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
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
            }

            /**
             * @param charSequence 明文
             * @param s 密文
             * @return
             */
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }

}
