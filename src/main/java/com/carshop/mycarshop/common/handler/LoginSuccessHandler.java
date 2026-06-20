package com.carshop.mycarshop.common.handler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@AllArgsConstructor
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    //private final UserPointHistoryService userPointHistoryService;

    //private final MessageHandler messageHandler;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("계정 인증 성공 onAuthenticationSuccess~~~ : " + authentication.getName());

        HttpSession session = request.getSession();

        String message = "환영 합니다 임시..";
        session.setAttribute("greeting", message);

        log.info("[SESSION] login success sessionId={}, memberId={}, authorities={}, greeting={}",
                session.getId(),
                authentication.getName(),
                authentication.getAuthorities(),
                message);

        response.sendRedirect("/");
    }
}