package com.carshop.mycarshop.controller.history;

import com.carshop.mycarshop.dto.statistics.StatisticsReqDTO;
import com.carshop.mycarshop.dto.statistics.StatisticsResDTO;
import com.carshop.mycarshop.dto.statistics.StatisticsTotalResDTO;
import com.carshop.mycarshop.service.car.CarService;
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
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasRole('USER')")
public class StatisticsRestController {

    private final UserService userService;
    private final CarService carService;
    private final CarStatisticsService carStatisticsService;

    @ApiOperation(value = "내차 통계 내역 조회", notes = "")
    @GetMapping("/get")
    public List<StatisticsResDTO> getCarStatistics(@Valid StatisticsReqDTO statisticsReqDTO,
                                      BindingResult bindingResult, Principal principal){

        userService.findUser(principal.getName());
        carService.getCarInfo(statisticsReqDTO.getCarId());

        // 함수형 인터페이스 를 활용 Function - apply
        Map<String, Function<StatisticsReqDTO, List<StatisticsResDTO>>> statisticsFunctionMap =
                Map.of(
                    "#consume", carStatisticsService::getStatisticsConsume,
                    "#fuelAmount", carStatisticsService::getStatisticsFuelAmount,
                    "#distance", carStatisticsService::getStatisticsDistance
            );

        return statisticsFunctionMap
                .getOrDefault(statisticsReqDTO.getTargetId(), req -> new ArrayList<>())
                .apply(statisticsReqDTO);
    }

    @ApiOperation(value = "내차 통계 총 내역 조회", notes = "")
    @GetMapping("/total")
    public StatisticsTotalResDTO getTotalCarStatistics(@Valid StatisticsReqDTO statisticsReqDTO,
                                       BindingResult bindingResult, Principal principal){

        userService.findUser(principal.getName());
        carService.getCarInfo(statisticsReqDTO.getCarId());

        return carStatisticsService.getStatisticsTotal(statisticsReqDTO);
    }
}
