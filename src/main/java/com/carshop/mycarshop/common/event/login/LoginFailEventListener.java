package com.carshop.mycarshop.common.event.login;

import com.carshop.mycarshop.service.member.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class LoginFailEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    // 로그인 실패 시 호출
    private final LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {

        String memberId = event.getAuthentication().getPrincipal().toString();

        log.error("LoginFailEventListener-onApplicationEvent() memberId : " + memberId);
        // 로그인 시도 횟수 캐시에 저장
        loginAttemptService.loginFailed(memberId);
    }
}
