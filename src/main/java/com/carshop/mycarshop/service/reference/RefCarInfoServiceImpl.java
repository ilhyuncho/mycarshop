package com.carshop.mycarshop.service.reference;

import com.carshop.mycarshop.domain.reference.RefCarInfo;
import com.carshop.mycarshop.domain.reference.RefCarInfoRepository;
import com.carshop.mycarshop.domain.reference.RefCarSample;
import com.carshop.mycarshop.domain.reference.RefCarSampleRepository;
import com.carshop.mycarshop.dto.reference.RefCarSampleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Log4j2
@Service
@RequiredArgsConstructor
public class RefCarInfoServiceImpl implements RefCarInfoService {

    private final RefCarInfoRepository refCarInfoRepository;


    @Override
    public RefCarInfo getRefCarInfo(Long refCarInfoId) {

        return refCarInfoRepository.findById(refCarInfoId).orElse(null);
    }
}
