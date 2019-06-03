package com.example.common.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

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
        web.ignoring().mvcMatchers(IGNORE_SECURITY_FILTER_URL_PATTERNS);// 해당 패턴은 security filter 제외
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
            .formLogin()
            .loginPage("/v1/basic/login")
            .defaultSuccessUrl("/v1/basic/main")
            .loginProcessingUrl("/v1/basic/login/req")
            .usernameParameter("userId")
            .usernameParameter("userPw")

            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/v1/basic/login")

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.NEVER) //새로운 세션을 생성하지 않음.
            .maximumSessions(1)
            .expiredUrl("/svc/tree/login");
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }
}
