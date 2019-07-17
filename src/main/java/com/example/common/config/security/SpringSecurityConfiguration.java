package com.example.common.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    private final String LOGIN_PAGE_URI = "/v1/basic/login";

    private String[] IGNORE_SECURITY_FILTER_URL_PATTERNS = {
        "/v1/api/**",
        "/v1/basic/login"
    };

    /**
     * 인증 설정 관련.
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider); // 인증 관련 서비스를 implements 해서 별도로 인증 서비스를 지원
    }

    /**
     * security filter와 연결하기 위한 설정 관련.
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(IGNORE_SECURITY_FILTER_URL_PATTERNS);// 해당 패턴은 security filter 제외
    }

    /**
     * request를 안전하게 보호하기 위한 설정 관련.
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            /*.authorizeRequests().anyRequest().authenticated().and().httpBasic()*/

            //.and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl(LOGIN_PAGE_URI)

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.NEVER) //새로운 세션을 생성하지 않음.
            .maximumSessions(1)
            .expiredUrl(LOGIN_PAGE_URI);
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }
}
