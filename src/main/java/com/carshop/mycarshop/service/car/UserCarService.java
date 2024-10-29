package com.carshop.mycarshop.service.car;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.car.CarInfoReqDTO;
import com.carshop.mycarshop.dto.car.CarKmUpdateReqDTO;
import com.carshop.mycarshop.dto.car.CarViewResDTO;

import java.util.List;

public interface UserCarService {

    List<CarViewResDTO> readMyCarList(User user);
    CarViewResDTO readMyCarDetailInfo(User user, Long carId);

    Long registerMyCar(User user, String carNumber);
    void modifyMyCar(CarInfoReqDTO carInfoReqDTO);
    void modifyMyCarKm(CarKmUpdateReqDTO carKmUpdateReqDTO);
    void deleteMyCar(Long carId);
}