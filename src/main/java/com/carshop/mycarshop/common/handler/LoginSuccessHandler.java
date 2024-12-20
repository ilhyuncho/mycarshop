package com.carshop.mycarshop.common.handler;

import com.carshop.mycarshop.common.message.MessageCode;
import com.carshop.mycarshop.common.message.MessageHandler;
import com.carshop.mycarshop.domain.member.MemberRole;
import com.carshop.mycarshop.domain.user.UserActionType;
import com.carshop.mycarshop.service.user.UserPointHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@AllArgsConstructor
@Component      // 스테레오 타입 애너테이션
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserPointHistoryService userPointHistoryService;

    private final MessageHandler messageHandler;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.error("LoginSuccessHandler-onAuthenticationSuccess()~~~ : " + authentication.getName());

        // 테스트
        Collection<? extends GrantedAuthority> authorities =
                authentication.getAuthorities();

        Optional<? extends GrantedAuthority> read = authorities
                .stream()
                .filter(a -> a.getAuthority().equals("ROLE_USER")).findFirst();
        if(read.isPresent()){
            log.error("User 권한이 있음");
        }
//        List<? extends GrantedAuthority> collect = authorities.stream().collect(Collectors.toList());
//        collect.forEach(log::error);



        // 포인트 획득 처리
        userPointHistoryService.gainUserPoint(authentication.getName(), UserActionType.ACTION_LOGIN);

        // 메인 페이지에서 출력
        HttpSession session = request.getSession();

        // Locale 메시지 정보 가져오기
        List<String> listArgs = new ArrayList<>();
        listArgs.add(authentication.getName());
        String message = messageHandler.getMessage(MessageCode.WELCOME_GREETING, listArgs);

        //String message = "환영 합니다...";

        session.setAttribute("greeting", message);
        session.setAttribute("memberName", authentication.getName());
        response.sendRedirect("/");
    }
}