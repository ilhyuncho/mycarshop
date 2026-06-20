package com.carshop.mycarshop.service.reference;

import com.carshop.mycarshop.domain.reference.RefCarSample;

import java.util.List;

public interface RefCarSampleService {
    RefCarSample findMyCar(String carNumber);

    List<RefCarSample> getAllRefCarSamples();
}