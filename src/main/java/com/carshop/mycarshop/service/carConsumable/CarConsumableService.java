package com.carshop.mycarshop.service.carConsumable;

import com.carshop.mycarshop.domain.car.Car;
import com.carshop.mycarshop.domain.carConsumable.CarConsumable;
import com.carshop.mycarshop.domain.carConsumable.ConsumableType;
import com.carshop.mycarshop.domain.reference.RefCarConsumable;
import com.carshop.mycarshop.dto.car.CarConsumableDetailResDTO;
import com.carshop.mycarshop.dto.car.CarConsumableRegDTO;
import com.carshop.mycarshop.dto.car.CarConsumableResDTO;
import com.carshop.mycarshop.dto.history.HistoryCarResDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CarConsumableService {
    CarConsumable getCarConsumableInfo(Long consumableId);
    CarConsumableDetailResDTO getConsumableInfo(Long consumableId);
    RefCarConsumable getRefCarConsumableInfo(Long refConsumableId);
    Map<RefCarConsumable, Optional<CarConsumable>> getMapRecentConsumableInfo(Car car);
    List<RefCarConsumable> getAllRefCarConsumableInfo();
    List<CarConsumableResDTO> getListConsumableInfo(Long carId);


    List<CarConsumableDetailResDTO> getConsumableDetail(Long carId, Long refConsumableId);
    List<HistoryCarResDTO> getListHistory(Long carId, List<ConsumableType> listConsumableType);

    void registerConsumable(CarConsumableRegDTO carConsumableRegDTO);
    void modifyConsumable(CarConsumableRegDTO carConsumableRegDTO);
    void removeConsumable(Long consumableId);
}
