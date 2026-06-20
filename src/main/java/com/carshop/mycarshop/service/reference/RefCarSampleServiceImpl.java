package com.carshop.mycarshop.service.reference;

import com.carshop.mycarshop.config.RedisCacheNames;
import com.carshop.mycarshop.domain.reference.RefCarSample;
import com.carshop.mycarshop.domain.reference.RefCarSampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Log4j2
@Service
@RequiredArgsConstructor
public class RefCarSampleServiceImpl implements RefCarSampleService {

    private final RefCarSampleRepository refCarSampleRepository;
    private final ObjectProvider<RefCarSampleService> refCarSampleServiceProvider;

    @Override
    public RefCarSample findMyCar(String carNumber) {

        if (carNumber.isEmpty()) {
            // 같은 Service 클래스 안에서 @Cacheable 메서드를 부를 때, Spring 프록시를 거치게 하려고 ObjectProvider.getObject()로 다시 호출한 것
            List<RefCarSample> listCarSample = refCarSampleServiceProvider.getObject().getAllRefCarSamples();

            if (listCarSample.size() > 0) {
                int skipIndex = new Random().nextInt(listCarSample.size() - 1);
                return listCarSample.stream().skip(skipIndex).findFirst().get();
            } else {
                return null;
            }
        } else {
            Optional<RefCarSample> byCarNumber = refCarSampleRepository.findByCarNumber(carNumber);
            return byCarNumber.orElse(null);
        }
    }

    @Override
    @Cacheable(value = RedisCacheNames.REF_CAR_SAMPLES, key = "'all'")
    public List<RefCarSample> getAllRefCarSamples() {
        return refCarSampleRepository.findAll();
    }
}
