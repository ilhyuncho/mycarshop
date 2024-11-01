package com.carshop.mycarshop.domain.carConsumable.search;

import com.carshop.mycarshop.dto.statistics.StatisticsReqDTO;
import com.carshop.mycarshop.dto.statistics.StatisticsResDTO;
import com.carshop.mycarshop.dto.statistics.StatisticsTotalResDTO;

import java.util.List;

public interface CarConsumableSearch {
    List<StatisticsResDTO> statisticsConsume(StatisticsReqDTO statisticsReqDTO);
    List<StatisticsResDTO> statisticsFuelAmount(StatisticsReqDTO statisticsReqDTO);
    List<StatisticsResDTO> statisticsDistance(StatisticsReqDTO statisticsReqDTO);
    StatisticsTotalResDTO statisticsTotal(StatisticsReqDTO statisticsReqDTO);

}