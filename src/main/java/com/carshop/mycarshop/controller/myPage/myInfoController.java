package com.carshop.mycarshop.controller.myPage;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myInfo")
@RequiredArgsConstructor
@Log4j2
public class myInfoController {

    @GetMapping("/info")
    public String getMyInfo(){

        return "/myPage/myInfo";
    }

    @GetMapping("/carDetail")
    public String getcarDetail(){

        return "/myPage/carDetail";
    }

}
