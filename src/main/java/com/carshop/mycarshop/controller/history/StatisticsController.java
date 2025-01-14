package com.carshop.mycarshop.controller.history;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('USER')")
public class StatisticsController {

    private final UserService userService;

    @ApiOperation(value = "내차 관리 통계 화면", notes = "")
    @GetMapping
    public String statistics(@ModelAttribute("carId") Long carId, String memberId){

        userService.findUser(memberId);

        return "myPage/carStatistics";
    }


}