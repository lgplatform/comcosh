package com.example.common.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Authentication 구현 부분
 * - LDAP을 통한 인증, 자체 안증 서비스에 의한 인증 등의 로직이 작성되는 부분
 *
 * @author mins
 */
@Component
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

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
