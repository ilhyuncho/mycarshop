package com.carshop.mycarshop.common.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Log4j2
public class SessionLoggingFilter extends OncePerRequestFilter {

    // log 제외 url 추가
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.startsWith("/css/")
                || uri.startsWith("/js/")
                || uri.startsWith("/images/")
                || uri.startsWith("/webjars/")
                || uri.startsWith("/.well-known/")
                || uri.equals("/favicon.ico");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isAuthenticatedUser(authentication)) {
            log.info("[SESSION] authenticated sessionId={}, memberId={}, authorities={}, uri={}",
                    session != null ? session.getId() : "none",
                    authentication.getName(),
                    authentication.getAuthorities(),
                    request.getRequestURI());
        }
//        else if (log.isDebugEnabled()) {  // 로그 레벨이 debug
        else{
            log.info("[SESSION] anonymous sessionId={}, uri={}",
                    session != null ? session.getId() : "none",
                    request.getRequestURI());
        }

        filterChain.doFilter(request, response);
    }

    private boolean isAuthenticatedUser(Authentication authentication) {
        return authentication != null
                && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getPrincipal());
    }
}
