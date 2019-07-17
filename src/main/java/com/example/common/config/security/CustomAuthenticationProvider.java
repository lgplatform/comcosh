package com.example.common.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Authentication 구현 부분
 * - LDAP을 통한 인증 or 자체 안증 서비스에 의한 인증 등의 로직이 작성되는 부분
 *
 * @author mins
 */
//@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    /**
     * 인증 관련 로직
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // for test
        String userId = authentication.getName(); // svcCd:empId 형태로 전달 받음
        String userPw = (String) authentication.getCredentials();
        //UserDetails userDetails = new User(userId, userPw, new HashSet<>(this.makeGrantedAuthority()));
        if ("minssogi".equals(userId) && "minssogi!123".equals(userPw)) {

            return new UsernamePasswordAuthenticationToken(userId, userPw, new HashSet<>(this.makeGrantedAuthority()));
        }


        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    private List<GrantedAuthority> makeGrantedAuthority() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        return authorities;
    }
}
