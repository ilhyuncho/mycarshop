package com.carshop.mycarshop.service.carConsumable;

import com.carshop.mycarshop.domain.carConsumable.ConsumableType;
import com.carshop.mycarshop.domain.reference.RefCarConsumable;
import com.carshop.mycarshop.dto.car.CarConsumableDetailResDTO;
import com.carshop.mycarshop.dto.car.CarConsumableRegDTO;
import com.carshop.mycarshop.dto.car.CarConsumableResDTO;
import com.carshop.mycarshop.dto.history.HistoryCarResDTO;

import java.util.List;

public interface CarConsumableService {
    CarConsumableDetailResDTO getConsumableInfo(Long consumableId);
    List<CarConsumableDetailResDTO> getConsumableDetail(Long carId, Long refConsumableId);
    void registerConsumable(CarConsumableRegDTO carConsumableRegDTO);
    void modifyConsumable(CarConsumableRegDTO carConsumableRegDTO);
    void removeConsumable(Long consumableId);

    RefCarConsumable getRefCarConsumableInfo(Long refConsumableId);
    List<CarConsumableResDTO> getListConsumableInfo(Long carId);
    List<HistoryCarResDTO> getListHistory(Long carId, List<ConsumableType> listConsumableType);
//    List<HistoryCarResDTO> getListHistory(Long carId, ConsumableType consumableType);

}
