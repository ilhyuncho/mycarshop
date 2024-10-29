package com.carshop.mycarshop.service.reference;

import com.carshop.mycarshop.domain.reference.RefCarSample;

public interface RefCarSampleService {
    RefCarSample findMyCar(String carNumber);
}