package com.carshop.mycarshop.config.security;


import com.carshop.mycarshop.common.handler.AuthFailureHandler;
import com.carshop.mycarshop.common.handler.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 어노테이션으로 권한을 설정
// prePostEnabled = true -> @PreAuthorize를 이용해서 권한 체크
public class SecurityConfig {

    private final DataSource dataSource;
    private final CustomUserDetailsService userDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final AuthFailureHandler authFailureHandler;
    //private final JWTCheckFilter jwtCheckFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        log.info("----------configure------------");

        // 로그인 화면에서 로그인 진행
        http.formLogin().loginPage("/auth/login")
            .successHandler(loginSuccessHandler)
            //.failureUrl("/auth/loginError");
            .failureHandler(authFailureHandler);

        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/","/auth/**").permitAll()
                .antMatchers("/sellingCar/{carId:^[0-9]+$}", "/sellingCar/list","/sellingCar/recommend","/sellingCar/view/**").permitAll()
                .antMatchers("/sellingCar/listSearch").permitAll()
                .antMatchers("/view/**").permitAll()
                .antMatchers("/buyingCar/list").permitAll()
                .antMatchers("/shop/**").permitAll()
                .antMatchers("/notification/**").permitAll()
                .antMatchers("/review/**").permitAll()
                .antMatchers("/verify/email").permitAll()
                .antMatchers("/reference/listRefCarType").permitAll()

                // aws 에 올린 서버에서 http://13.124.166.39:8080/swagger-ui/index.html 접속 했을때 오류 처리
                // message: Refused to apply style from 'http://13.124.166.39:8080/auth/login' because its MIME type ('text/html') is not a supported stylesheet MIME type, and strict MIME checking is enabled.
                // 해결 못함



                //.antMatchers( "/swagger-ui/index.html**").hasRole("ADMIN")
//                .antMatchers("/myPage/**").permitAll()
                .anyRequest().authenticated();


        // .authorizeRequests().anyRequest().permitAll().and() // 인증 없이 요청 가능
        // .authorizeRequests().anyRequest().authenticated().and() // 모든 요청에 인증 필요


        // CSRF 토큰 비활성화
        http.csrf().disable();

        http.rememberMe().key("12345678")
                .key("remember-me-key")
                .rememberMeCookieName("mycarshop-remember-me")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService)
                //.tokenValiditySeconds(60*60*24*30); // 30일?
                .tokenValiditySeconds(60); // 30일?

        // 일단, swagger-ui 접근이 안되어서 일단 보류
        // jwtCheckFilter를 UsernamePasswordAuthenticationFilter 앞에 두기
        // http.addFilterBefore(jwtCheckFilter, UsernamePasswordAuthenticationFilter.class);

        // 모든 경로에 접근 가능
        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        // 정적 자원들의 시큐리티 적용 제외
        log.error("-------------web configure----------------");

        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                //.antMatchers("/view/**")
                //.antMatchers("/mycarshopViewInfo/**", "/css/**", "/js/**", "/assets/**", "item/**")
                ;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        log.error(repo.getDataSource());
        return repo;
    }

}
