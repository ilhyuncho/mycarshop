package com.carshop.mycarshop.controller.myPage;


import com.carshop.mycarshop.dto.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myInfo")
@RequiredArgsConstructor
@Log4j2
public class myInfoController {

    @GetMapping("/info")
    public String getMyInfo(){

        log.error("myinfo/info - call");
        return "myPage/myInfo";
    }

    @GetMapping("/carDetail")
    public String getcarDetail(){

        return "myPage/carDetail";
    }
    @GetMapping("/carList")
    public String list(@ModelAttribute("pageRequestDto") PageRequestDTO pageRequestDTO, Model model){

        log.error("myinfo/carList - call");

        return "myPage/carDetail";
    }
}
