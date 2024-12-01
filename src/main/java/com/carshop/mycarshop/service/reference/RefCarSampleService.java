package com.carshop.mycarshop.service.reference;

import com.carshop.mycarshop.dto.reference.RefCarSampleDTO;

public interface RefCarSampleService {
    RefCarSampleDTO findMyCar(String carNumber);
}