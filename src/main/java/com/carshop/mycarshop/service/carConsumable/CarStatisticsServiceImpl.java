package com.carshop.mycarshop.service.carConsumable;

import com.carshop.mycarshop.domain.car.Car;
import com.carshop.mycarshop.domain.carConsumable.CarConsumableRepository;
import com.carshop.mycarshop.dto.statistics.StatisticsReqDTO;
import com.carshop.mycarshop.dto.statistics.StatisticsResDTO;
import com.carshop.mycarshop.dto.statistics.StatisticsTotalResDTO;
import com.carshop.mycarshop.service.car.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CarStatisticsServiceImpl implements CarStatisticsService {

    private final CarService carService;
    private final CarConsumableRepository carConsumableRepository;


    @Override
    public List<StatisticsResDTO> getStatisticsConsume(StatisticsReqDTO statisticsReqDTO) {

        Car car = carService.getCarInfo(statisticsReqDTO.getCarId());

        return carConsumableRepository.statisticsConsume(statisticsReqDTO);
    }

    @Override
    public List<StatisticsResDTO> getStatisticsFuelAmount(StatisticsReqDTO statisticsReqDTO) {

        Car car = carService.getCarInfo(statisticsReqDTO.getCarId());

        return carConsumableRepository.statisticsFuelAmount(statisticsReqDTO);
    }

    @Override
    public List<StatisticsResDTO> getStatisticsDistance(StatisticsReqDTO statisticsReqDTO) {

        Car car = carService.getCarInfo(statisticsReqDTO.getCarId());

        return carConsumableRepository.statisticsDistance(statisticsReqDTO);
    }

    @Override
    public StatisticsTotalResDTO getStatisticsTotal(StatisticsReqDTO statisticsReqDTO) {

        Car car = carService.getCarInfo(statisticsReqDTO.getCarId());

        return carConsumableRepository.statisticsTotal(statisticsReqDTO);
    }

}
