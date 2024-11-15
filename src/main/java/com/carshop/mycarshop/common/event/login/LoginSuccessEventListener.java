package com.carshop.mycarshop.common.event.login;

import com.carshop.mycarshop.service.member.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Log4j2
@RequiredArgsConstructor
public class LoginSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final LoginAttemptService loginAttemptService;
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String memberId = event.getAuthentication().getPrincipal().toString();

        log.error("LoginSuccessEventListener-onApplicationEvent() memberId : " + memberId);

        loginAttemptService.loginSuccess(memberId);
    }
}
