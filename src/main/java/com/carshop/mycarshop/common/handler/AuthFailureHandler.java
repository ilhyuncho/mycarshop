package com.carshop.mycarshop.common.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j2
public class AuthFailureHandler implements AuthenticationFailureHandler {

    private final DefaultRedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        if(exception instanceof DisabledException){
            // DisabledException - 계정 비활성화, 이메일 인증 전
            log.error("AuthFailureHandler-onAuthenticationFailure() : DisabledException");

            defaultRedirectStrategy.sendRedirect(request, response, "/auth/loginDisabled");
            return;
        }
        if(exception.getCause() instanceof LockedException){
            // 비밀번호 3회 틀림
            log.error("AuthFailureHandler-onAuthenticationFailure() : LockedException");

            defaultRedirectStrategy.sendRedirect(request, response, "/auth/loginLocked");
            return;
        }


        log.error("AuthFailureHandler-onAuthenticationFailure() : not DisabledException");
        defaultRedirectStrategy.sendRedirect(request, response, "/auth/loginError");
    }
}
