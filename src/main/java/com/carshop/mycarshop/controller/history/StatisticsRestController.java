package com.carshop.mycarshop.controller.history;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.statistics.StatisticsReqDTO;
import com.carshop.mycarshop.dto.statistics.StatisticsResDTO;
import com.carshop.mycarshop.dto.statistics.StatisticsTotalResDTO;
import com.carshop.mycarshop.service.carConsumable.CarStatisticsService;
import com.carshop.mycarshop.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('USER')")
public class StatisticsRestController {

    private final UserService userService;
    private final CarStatisticsService carStatisticsService;

    @ApiOperation(value = "내차 통계 내역 조회", notes = "")
    @GetMapping("/get")
    public List<StatisticsResDTO> get(@Valid StatisticsReqDTO satisticsReqDTO,
                                      BindingResult bindingResult,
                                      Principal principal){

        User user = userService.findUser(principal.getName());

        List<StatisticsResDTO> listDto = new ArrayList<>();

        if("#consume".equals(satisticsReqDTO.getTargetId())){
            listDto = carStatisticsService.getStatisticsConsume(satisticsReqDTO);
        }
        else if("#fuelAmount".equals(satisticsReqDTO.getTargetId())){
            listDto = carStatisticsService.getStatisticsFuelAmount(satisticsReqDTO);
        }
        else if("#distance".equals(satisticsReqDTO.getTargetId())){
            listDto = carStatisticsService.getStatisticsDistance(satisticsReqDTO);
        }

        return listDto;
    }

    @ApiOperation(value = "내차 통계 총 내역 조회", notes = "")
    @GetMapping("/total")
    public StatisticsTotalResDTO total(@Valid StatisticsReqDTO satisticsReqDTO,
                                       BindingResult bindingResult,
                                       Principal principal){

        User user = userService.findUser(principal.getName());

        return carStatisticsService.getStatisticsTotal(satisticsReqDTO);
    }
}