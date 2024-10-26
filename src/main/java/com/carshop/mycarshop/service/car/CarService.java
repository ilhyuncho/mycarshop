package com.carshop.mycarshop.service.car;

import com.carshop.mycarshop.domain.car.Car;
import com.carshop.mycarshop.dto.car.CarViewResDTO;

public interface CarService {
    Car getCarInfo(Long carId);
    CarViewResDTO readOne(Long carId);
}
