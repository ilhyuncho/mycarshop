package com.carshop.mycarshop.service.carConsumable;

import com.carshop.mycarshop.dto.statistics.StatisticsReqDTO;
import com.carshop.mycarshop.dto.statistics.StatisticsResDTO;
import com.carshop.mycarshop.dto.statistics.StatisticsTotalResDTO;

import java.util.List;

public interface CarStatisticsService {

    List<StatisticsResDTO> getStatisticsConsume(StatisticsReqDTO statisticsReqDTO);
    List<StatisticsResDTO> getStatisticsFuelAmount(StatisticsReqDTO statisticsReqDTO);
    List<StatisticsResDTO> getStatisticsDistance(StatisticsReqDTO statisticsReqDTO);
    StatisticsTotalResDTO getStatisticsTotal(StatisticsReqDTO statisticsReqDTO);

}
