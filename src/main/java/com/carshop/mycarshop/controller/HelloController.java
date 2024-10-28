package com.carshop.mycarshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@Log4j2
@RequiredArgsConstructor
public class HelloController {

    //private final NotificationService notificationService;

    @GetMapping("/")
    public String mainPage(HttpServletRequest request, Model model){

        // 자동으로 템플릿으로 session값 전달
//       String temp = session.getAttribute("greeting").toString();

        // 임시로
        boolean isItEventTime = LocalDateTime.now()
                .query(time -> {
                    var currTime = LocalTime.from(time);
                    return currTime.getHour() >= 13;
                });
        log.error("isItEventTime : " + isItEventTime);

        //NotiEventResDTO eventDTO = notificationService.getRandomPopupEventInfo();

        //model.addAttribute("eventDTO", eventDTO);

        // 메인 페이지에서 출력
        HttpSession session = request.getSession();
        session.setAttribute("greeting", "환영합니다");

        return "/index";
    }
    @GetMapping("/hello")
    public void hello(Model model){

        // hello.html 으로 이동
        model.addAttribute("msg", "HELLO CIH!!");
    }
}